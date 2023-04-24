package com.sda.java129;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatabaseTest {
    private Database database;

    /* Test scenarios:
    - addUser:
        1/ success - correct data
        2/ fail - user with this login already exists
        3/ fail - missing data (login or password)
    - findAll:
        1/ find data
    - findByLogin:
        1/ success - correct data
        2/ fail - person with such login doesn't exist
        3/ fail - provided login is null
    - removeByLogin:
        1/ success - correct data
        2/ fail - person with such login doesn't exist
        3/ fail - provided login is null
     */

    @BeforeEach
    public void setup() {
        database = new Database(Arrays.asList(
                new User("Joanna123", "12345"),
                new User("Adi55", "abcde")
        ));
    }

    @Test
    void addUserSuccess() throws DatabaseException {
        //Given
        User newUser = new User("Adam1", "xyz123");
        int sizeBefore = database.getUsers().size();

        //When
        database.addUser(newUser);

        //Then
        assertThat(database.getUsers().size()).isEqualTo(sizeBefore + 1);
        assertThat(database.getUsers().get(newUser.getLogin()))
                .isNotNull()
                .extracting(User::getPassword)
                .isNotNull()
                .isEqualTo(newUser.getPassword());
    }

    @Test
    void addUserFailUserAlreadyFound() throws DatabaseException {
        //Given
        User newUser = new User("Joanna123", "xyz123");
        int sizeBefore = database.getUsers().size();

        //When
        DatabaseException exception = assertThrows(DatabaseException.class, () -> database.addUser(newUser));

        //Then
        assertThat(database.getUsers().size()).isEqualTo(sizeBefore);
        assertThat(database.getUsers().get(newUser.getLogin()))
                .isNotNull()
                .extracting(User::getPassword)
                .isNotEqualTo(newUser.getPassword());
        assertThat(exception)
                .hasMessage("Cannot add user! User with this login already exists!");
    }

    @Test
    void findAll() {
        //When
        List<User> result = database.findAll();

        //Then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findByLogin() {
    }

    @Test
    void removeByLogin() {
        database.removeByLogin("Joanna123");

        assertThat(database.getUsers().get("Joanna123"))
                .isNull();
    }
}
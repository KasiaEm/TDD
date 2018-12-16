package com.sda.db;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseTest {

    private Database database;
    private List<User> dbUsers;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        dbUsers = Arrays.asList(
                new User("u1", "pass"),
                new User("u2", "pass"),
                new User("u3", "pass"));
        database = new Database(dbUsers);
    }

    @Test
    public void testAddUser() {
        // given
        int dbPreviousSize = database.findAll().size();
        User user = new User("u4", "pass");
        // when
        database.addUser(user);
        //then
        assertThat(database.findAll().size()).isEqualTo(dbPreviousSize + 1);
        assertThat(database.findUser("u4")).isEqualToComparingFieldByFieldRecursively(user);
    }


    @Test
    public void testFindUser() {
        // given
        User expected = new User("u1", "pass");
        // when
        User actual = database.findUser("u1");
        // then
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    public void testRemoveUser() {
        // given
        int dbPreviousSize = database.findAll().size();
        // when
        database.removeUser("u1");
        // then
        assertThat(database.findUser("u1")).isNull();
        assertThat(database.findAll().size()).isEqualTo(dbPreviousSize - 1);
    }

    @Test
    public void testAddUserNullPointerException() {
        thrown.expect(NullPointerException.class);
        database.addUser(null);
    }

    @Test
    public void testAddUserUserExistsException() {
        thrown.expect(UserExistsException.class);
        thrown.expectMessage("User already exists.");
        database.addUser(new User("u1", "pass"));
    }

    @Test
    public void testFindAll() {
        assertThat(database.findAll()).isNotNull().containsAll(dbUsers);
        assertThat(database.findAll().size()).isEqualTo(dbUsers.size());
    }

}
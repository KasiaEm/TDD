package com.sda.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsManagerTest {

    private StatisticsManager statisticsManager;

    @Mock
    private Database database;

    @BeforeEach
    public void setup(){
        statisticsManager = new StatisticsManager(database);

        when(database.findAll())
                .thenReturn(Arrays.asList(
                new User("Adam123", "a123", 123),
                new User("Anna888", "12345", 230)
        ));

        when(database.findUser(anyString()))
                .thenReturn(
                        new User("XXX","123")
                );
        when(database.findUser("Kowalski"))
                .thenReturn(
                        new User("Kowalski", "123")
                );

    }

    @Test
    public void findUserWithMostExperience(){
        User userWithMostExperience = statisticsManager.findUserWithMostExperience();

        assertThat(userWithMostExperience).isNotNull()
                .extracting(User::getExpPoints)
                .isEqualTo(230);
    }

    @Test
    public void sumUsersExperience(){
        int sum = statisticsManager.sumUsersExperience();

        assertThat(sum).isEqualTo(353);
    }
}
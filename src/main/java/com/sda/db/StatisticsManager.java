package com.sda.db;

import java.util.Comparator;
import java.util.Optional;

public class StatisticsManager {
    private Database database;

    public StatisticsManager(Database database) {
        this.database = database;
    }

    public User findUserWithMostExperience(){
        return database.findAll()
                .stream()
                .max(Comparator.comparingInt(User::getExpPoints))
                .orElseThrow(() -> new StatisticsException("Data not found!"));
    }

    public int sumUsersExperience(){
        return database.findAll()
                .stream()
                .map(User::getExpPoints)
                .reduce(0, Integer::sum);
    }
}

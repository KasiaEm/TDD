package com.sda.java129;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Database {
    private Map<String, User> users;

    public Database() {
    }

    public Database(List<User> users) {
        this.users = users.stream()
                .collect(Collectors.toMap(User::getLogin, Function.identity()));
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public void addUser(User newUser) throws DatabaseException {
        if (newUser.getLogin() != null && !newUser.getLogin().isEmpty() && newUser.getPassword() != null && !newUser.getPassword().isEmpty()){
            if(users.containsKey(newUser.getLogin())){
                throw new DatabaseException("Cannot add user! User with this login already exists!");
            } else {
                users.put(newUser.getLogin(), newUser);
            }
        } else {
            throw new DatabaseException("Cannot add user! Data missing!");
        }
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findByLogin(String login) {
        return users.get(login);
    }

    public User removeByLogin(String login) {
        return users.remove(login);
    }


}

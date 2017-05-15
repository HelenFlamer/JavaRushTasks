package com.javarush.task.task36.task3608.model;


import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elena.slinkova on 01.03.2017.
 */
public class FakeModel implements Model {
     private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> us = new ArrayList<>();
        us.add(new User("name1", 1, 1));
        us.add(new User("name2", 2, 2));
        modelData.setUsers(us);
    }

    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();

    }

    @Override
    public void loadUserById(long userId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id) {
        throw  new UnsupportedOperationException();

    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw  new UnsupportedOperationException();

    }
}

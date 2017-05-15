package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by elena.slinkova on 01.03.2017.
 * . Выполни рефакторинг класса MainModel. Теперь, когда есть удаленные пользователи, часть методов стала работать неправильно.
 * Почти во всех методах, где требуется список пользователей, нужно работать только с активными(живыми) пользователями.
 * Вынеси в отдельный приватный метод List<User> getAllUsers() получение списка всех пользователей.
 * Фильтрация активных пользователей у тебя уже есть — метод List<User> filterOnlyActiveUsers(List<User> allUsers).
 * Отрефактори все методы, которые используют список пользователей. Они должны использовать список живых пользователей.
 */
public class MainModel implements Model {

    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(getAllUsers());

        modelData.setDisplayDeletedUserList(false);
    }

    public void loadDeletedUsers() {
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(true);
    }

    @Override
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    public void deleteUserById(long id) {
        User user = userService.deleteUser(id);
        modelData.setUsers(getAllUsers());

    }

    private List<User> getAllUsers() {
        List<User> allUsers = userService.getUsersBetweenLevels(1,100);
        return userService.filterOnlyActiveUsers(allUsers);
    }

    public void changeUserData(String name, long id, int level){
        userService.createOrUpdateUser(name, id, level);
        modelData.setUsers(getAllUsers());
    }




}

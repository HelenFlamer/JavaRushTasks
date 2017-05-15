package com.javarush.task.task30.task3008.client;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by elena.slinkova on 15.05.2017.
 * Давай напишем его:
 1) Создай класс ClientGuiModel в пакете client. Все классы клиента должны быть созданы в этом пакете.
 2) Добавь в него множество(set) строк в качестве final поля allUserNames. В нем будет храниться список всех участников чата. Проинициализируй его.
 3) Добавь поле String newMessage, в котором будет храниться новое сообщение, которое получил клиент.
 4) Добавь геттер для allUserNames, запретив модифицировать возвращенное множество. Разберись, как это можно сделать
 с помощью метода класса Collections.
 5) Добавь сеттер и геттер для поля newMessage.
 6) Добавь метод void addUser(String newUserName), который должен добавлять имя участника во множество, хранящее всех участников.
 7) Добавь метод void deleteUser(String userName), который будет удалять имя участника из множества.
 */
public class ClientGuiModel {
    private final Set<String> allUserNames = new HashSet<>();
    private String newMessage;

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public Set<String> getAllUserNames() {
        return Collections.unmodifiableSet(allUserNames);

    }

    public void addUser(String newUserName){
    allUserNames.add(newUserName);}

    public void deleteUser(String userName){
        allUserNames.remove(userName);
    }
}

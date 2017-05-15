package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;


/**
 * Последний, но самый главный метод класса SocketThread – это метод void run().
 * Добавь его. Его реализация с учетом уже созданных методов выглядит очень просто.
 * <p>
 * Давай напишем ее:
 * 1) Запроси адрес и порт сервера с помощью методов getServerAddress() и getServerPort().
 * 2) Создай новый объект класса java.net.Socket, используя данные, полученные в предыдущем пункте.
 * 3) Создай объект класса Connection, используя сокет из п.17.2.
 * 4) Вызови метод, реализующий «рукопожатие» клиента с сервером (clientHandshake()).
 * 5) Вызови метод, реализующий основной цикл обработки сообщений сервера.
 * 6) При возникновении исключений IOException или ClassNotFoundException сообщи главному потоку о проблеме,
 * используя notifyConnectionStatusChanged и false в качестве параметра.
 * <p>
 * Клиент готов, можешь запустить сервер, несколько клиентов и проверить как все работает.
 * <p>
 * <p>
 * Требования:
 * 1. В методе run должно быть установлено и сохранено в поле connection соединение с сервером (для получения адреса сервера и порта используй методы getServerAddress и getServerPort).
 * 2. В методе run должен быть вызван метод clientHandshake.
 * 3. В методе run должен быть вызван метод clientMainLoop.
 * 4. При возникновении исключений IOException или ClassNotFoundException в процессе работы метода run, должен быть вызван метод notifyConnectionStatusChanged с параметром false.
 * 5. Сигнатура метода run должна соответствовать условию задачи.
 */
public class Client {
    public class SocketThread extends Thread {
        public void run() {
            int port = getServerPort();
            String host = getServerAddress();
            try {
                Socket socket = new Socket(host, port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("User " + userName + " joined the chat");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("User " + userName + " left the chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == (MessageType.NAME_REQUEST)) {
                    String userName = getUserName();
                    Message newmessage = new Message(MessageType.USER_NAME, userName);
                    connection.send(newmessage);
                } else if (message.getType() == (MessageType.NAME_ACCEPTED)) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == (MessageType.TEXT)) {
                    processIncomingMessage(message.getData());
                } else if (message.getType() == (MessageType.USER_ADDED)) {
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == (MessageType.USER_REMOVED)) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }

        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run() {

        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Ошибка");
            return;
        }

        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
            while (clientConnected) {
                String message = ConsoleHelper.readString();
                if (message.equals("exit"))
                    break;
                if (shouldSendTextFromConsole())
                    sendTextMessage(message);
            }
        } else
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");


    }

    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Can not send a message!");
            clientConnected = false;
        }
    }


}

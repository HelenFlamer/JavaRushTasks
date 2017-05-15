package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализуем метод void run() в классе Handler.
 * <p>
 * Он должен:
 * 1. Выводить сообщение, что установлено новое соединение с удаленным адресом, который можно получить с помощью метода getRemoteSocketAddress.
 * 2. Создавать Connection, используя поле socket.
 * 3. Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента.
 * 4. Рассылать всем участникам чата информацию об имени присоединившегося участника (сообщение с типом USER_ADDED). Подумай, какой метод подойдет для этого лучше всего.
 *
 * 5. Сообщать новому участнику о существующих участниках.
 * 6. Запускать главный цикл обработки сообщений сервером.
 *
 * 7. Обеспечить закрытие соединения при возникновении исключения.
 * 8. Отловить все исключения типа IOException и ClassNotFoundException, вывести в консоль информацию, что произошла ошибка при обмене данными с удаленным адресом.
 * 9. После того как все исключения обработаны, если п.11.3 отработал и возвратил нам имя, мы должны удалить запись для этого имени из connectionMap и разослать всем остальным участникам сообщение с типом USER_REMOVED и сохраненным именем.
 * 10. Последнее, что нужно сделать в методе run() – вывести сообщение, информирующее что соединение с удаленным адресом закрыто.
 * <p>
 * Наш сервер полностью готов. Попробуй его запустить.
 * <p>
 * <p>
 * Требования:
 * 1. Метод run должен выводить на экран сообщение о том, что было установлено соединение с удаленным адресом (используя метод getRemoteSocketAddress).
 * 2. Метод run должен создавать новое соединение (connection) используя в качестве параметра поле socket.
 * 3. Метод run должен вызывать метод serverHandshake используя в качестве параметра только что созданное соединение; результатом будет имя пользователя (userName).
 * 4. Метод run должен вызывать метод sendBroadcastMessage используя в качестве параметра новое сообщение (MessageType.USER_ADDED, userName).
 * 5. Метод run должен вызывать метод sendListOfUsers используя в качестве параметров connection и userName.
 * 6. Метод run должен вызывать метод serverMainLoop используя в качестве параметров connection и userName.
 * 7. Прежде чем завершиться, метод run должен удалять из connectionMap запись соответствующую userName, и отправлять всем участникам чата сообщение о том, что текущий пользователь был удален.
 * 8. Метод run должен корректно обрабатывать исключения IOException и ClassNotFoundException.
 */
public class Server {
    private static class Handler extends Thread {
        private Socket socket;

        public void run() {
            ConsoleHelper.writeMessage("Established new connection with remote address " + socket.getRemoteSocketAddress());
            String clientName = null;

            try (Connection connection = new Connection(socket))
            {
                ConsoleHelper.writeMessage("Connection with port " + connection.getRemoteSocketAddress());
                clientName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                sendListOfUsers(connection, clientName);
                serverMainLoop(connection, clientName);

            }
            catch (IOException | ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("An error occurred while communicating with the remote address");
            }
            finally {
                if (clientName != null){
                connectionMap.remove(clientName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
                ConsoleHelper.writeMessage("Connection closed");}
            }


        }

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == (MessageType.TEXT)) {
                    String newMessage = (userName + ": " + message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, newMessage));
                } else {
                    ConsoleHelper.writeMessage("Error type");
                }
            }

        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            String name;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType().equals(MessageType.USER_NAME)) {
                    name = message.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
                String name = connectionEntry.getKey();
                if (!name.equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, name));
            }

        }
    }

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
            try {
                connectionEntry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Could not send a message");
            }
        }
    }


    public static void main(String[] args) throws IOException {
        int port = ConsoleHelper.readInt();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                Handler handler = new Handler(serverSocket.accept());
                handler.start();
                continue;
            }
        } catch (Exception e) {
            serverSocket.close();
            ConsoleHelper.writeMessage(e.getMessage());
        }


    }
}

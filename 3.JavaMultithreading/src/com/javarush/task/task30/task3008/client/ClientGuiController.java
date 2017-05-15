package com.javarush.task.task30.task3008.client;

/**
 * Created by elena.slinkova on 15.05.2017.
 * 5. Переопредели методы в классе ClientGuiController:
 * а) SocketThread getSocketThread() – должен создавать и возвращать объект типа GuiSocketThread.
 * б) void run() – должен получать объект SocketThread через метод getSocketThread() и вызывать у него метод run(). Разберись, почему нет необходимости вызывать метод run в отдельном потоке, как мы это делали для консольного клиента.
 * в) getServerAddress(), getServerPort(), getUserName(). Они должны вызывать одноименные методы из представления (view).
 * 6. Реализуй метод ClientGuiModel getModel(), который должен возвращать модель.
 * 7. Реализуй метод main(), который должен создавать новый объект ClientGuiController и вызывать у него метод run().
 * Запусти клиента с графическим окном, нескольких консольных клиентов и убедись, что
 * все работает корректно.
 */
public class ClientGuiController extends Client {

    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);
    public ClientGuiModel getModel(){
        return model;
    }
    public static void main(String[] args){
        new ClientGuiController().run();
    }


    public class GuiSocketThread extends Client.SocketThread {
        @Override
        protected void processIncomingMessage(String message) {
            model.setNewMessage(message);
            view.refreshMessages();
        }

        @Override
        protected void informAboutAddingNewUser(String userName) {
            model.addUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void informAboutDeletingNewUser(String userName) {
            model.deleteUser(userName);
            view.refreshUsers();
        }

        @Override
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            view.notifyConnectionStatusChanged(clientConnected);
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }

    @Override
    public void run() {
        getSocketThread().run();
    }

    @Override
    protected String getServerAddress() {
        return view.getServerAddress();
    }

    @Override
    protected int getServerPort() {
        return view.getServerPort();
    }

    @Override
    protected String getUserName() {
        return view.getUserName();
    }
}

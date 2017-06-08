package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by elena.slinkova on 05.06.2017.
 * 11.5. Добавь в представление методы:
 * 11.5.1. void undo() — отменяет последнее действие. Реализуй его используя undoManager.
 * Метод не должен кидать исключений, логируй их.
 * 11.5.2. void redo() — возвращает ранее отмененное действие. Реализуй его по аналогии с предыдущим пунктом.
 * 11.5.3. Реализуй методы boolean canUndo() и boolean canRedo() используя undoManager.
 * 11.5.4. Реализуй геттер для undoListener.
 * 11.5.5. Добавь и реализуй метод void resetUndo(), который должен сбрасывать все правки в менеджере undoManager.
 */
public class View extends JFrame implements ActionListener {
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel("View");
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    //инициализация меню
    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    //инициализация панелей редактора
    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPane);
        JScrollPane jScrollPane2 = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPane2);
        tabbedPane.setPreferredSize(new Dimension(800, 600));
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    //инициализация графического редактора
    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);

    }

    public void exit() {
        controller.exit();
    }

    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(this.getContentPane(), "Test HTML editor", "О программе",  JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     Реализуем метод actionPerformed(ActionEvent actionEvent) у представления,
     этот метод наследуется от интерфейса ActionListener и будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий.
     19.1. Получи из события команду с помощью метода getActionCommand(). Это будет обычная строка. По этой строке ты можешь понять какой пункт меню создал данное событие.
     19.2. Если это команда «Новый«, вызови у контроллера метод createNewDocument(). В этом пункте и далее, если необходимого метода в контроллере еще нет — создай заглушки.
     19.3. Если это команда «Открыть«, вызови метод openDocument().
     19.4. Если «Сохранить«, то вызови saveDocument().
     19.5. Если «Сохранить как…» — saveDocumentAs().
     19.6. Если «Выход» — exit().
     19.7. Если «О программе«, то вызови метод showAbout() у представления.
     Проверь, что заработали пункты меню Выход и О программе.
     */

    public void selectedTabChanged(){
        int index = tabbedPane.getSelectedIndex();
        if (index == 0){
            controller.setPlainText(plainTextPane.getText());
        }
        else if (index == 1)
            plainTextPane.setText(controller.getPlainText());
        this.resetUndo();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        String command = actionEvent.getActionCommand();
        switch (command){
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                this.showAbout();
                break;
        }
    }
}

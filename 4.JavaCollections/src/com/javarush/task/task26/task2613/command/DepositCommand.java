package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String[] moneyAndAmount = ConsoleHelper.getValidTwoDigits(currencyCode);
        try
        {
            int k = Integer.parseInt(moneyAndAmount[0]);
            int l = Integer.parseInt(moneyAndAmount[1]);
            currencyManipulator.addAmount(k, l);
        } catch (NumberFormatException e)
        {
            ConsoleHelper.writeMessage("invalid.data");
        }

    }
}

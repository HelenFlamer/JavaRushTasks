package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    public List<String> onDataListShow() {
        return new Model().getStringDataList();
    }


}

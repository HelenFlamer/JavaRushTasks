package com.javarush.task.task38.task3810;

import java.util.List;

public @interface Revision {
    //напиши свой код
    int revision();
    Date date();
    String comment() default "";
    Author[] authors() default {};
}

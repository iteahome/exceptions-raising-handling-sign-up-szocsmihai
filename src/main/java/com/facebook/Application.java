package com.facebook;

import com.facebook.exception.FacebookException;
import com.facebook.ui.MainUI;

public class Application {

    public static void main(String[] args) throws FacebookException {
        new MainUI().start();
    }
}
package com.facebook.ui;

import com.facebook.exception.FacebookException;
import com.facebook.service.UserService;

import java.util.Scanner;

public class MainUI {

    private static final String OPTION_LOGIN = "1";
    private static final String OPTION_SIGN_UP = "2";
    private static final String OPTION_LEAVE = "0";

    private LoginUI loginUI = new LoginUI();
    private SignUpUI signUpUI = new SignUpUI();

    public void start() throws FacebookException {

        String userInput;

        do {
            System.out.println("\nMAIN MENU | CHOOSE AN ACTION BY ITS NUMBER:");
            System.out.println("1 : Log In | 2 : Sign Up | 0 : Leave\n");

            Scanner keyboardScanner = new Scanner(System.in);
            userInput = keyboardScanner.nextLine();
            switch (userInput) {
                case OPTION_LOGIN:
                    loginUI.displayLogin();
                    break;
                case OPTION_SIGN_UP:
                    signUpUI.displaySignUp();
                    break;
                case OPTION_LEAVE:
                    System.out.println("Come again soon!");
                    break;
                default:
                    System.out.println("Please enter valid input.");
            }
        } while (!OPTION_LEAVE.equals(userInput) && !UserService.isUserPseudoLoggedIn);
    }
}

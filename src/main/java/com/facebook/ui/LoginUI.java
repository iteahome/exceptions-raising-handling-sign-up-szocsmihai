package com.facebook.ui;

import com.facebook.exception.business.FbWrongCredentialsException;
import com.facebook.exception.technical.FbFileNotFoundException;
import com.facebook.service.UserService;

import java.util.Scanner;

public class LoginUI {

    private static final String OPTION_MAIN_MENU = "0";

    private UserService userService = new UserService();

    public void displayLogin() {

        System.out.println("\nLOGIN MENU | PLEASE PROVIDE THE FOLLOWING:\n");

        Scanner keyboardScanner = new Scanner(System.in);
        String email;
        String password;

        do {
            System.out.println("Enter your email (or input \"0\" to return to main menu):");
            email = keyboardScanner.nextLine();
            if (OPTION_MAIN_MENU.equals(email)) {
                break;
            }
            try {
                if (!userService.doesEmailExist(email)) {
                    System.out.println("User not found.");
                    continue;
                }
            } catch (FbFileNotFoundException e) {
                System.out.println("Cannot verify if user already exists: user database not found.");
            }

            System.out.println("Enter password (or input \"0\" to return to main menu):");
            password = keyboardScanner.nextLine();
            if (OPTION_MAIN_MENU.equals(password)) {
                break;
            }

            try {
                userService.login(email, password);
                System.out.println("Login status: " + UserService.isUserPseudoLoggedIn + ".");
                break;

            } catch (FbFileNotFoundException e) {
                System.out.println("Cannot check credentials: user database not found.");
            } catch (FbWrongCredentialsException e) {
                System.out.println("Wrong credentials.");
            }

        } while (true);
    }
}

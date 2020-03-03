package com.facebook.ui;

import com.facebook.exception.business.FbUserAlreadyExistsException;
import com.facebook.exception.business.FbWrongCredentialsException;
import com.facebook.exception.technical.FbFileNotFoundException;
import com.facebook.service.UserService;
import com.facebook.ui.validator.UserCredentialsValidator;

import java.util.Scanner;

public class SignUpUI {

    private static final String OPTION_MAIN_MENU = "0";

    private UserService userService = new UserService();
    private UserCredentialsValidator userCredentialsValidator = new UserCredentialsValidator();

    public void displaySignUp() {

        System.out.println("\nSIGN UP MENU | PLEASE PROVIDE THE FOLLOWING:\n");

        Scanner keyboardScanner = new Scanner(System.in);
        String email;
        String password;

        do {
            System.out.println("Enter a valid email address (or input \"0\" to return to main menu):");
            email = keyboardScanner.nextLine();
            if (OPTION_MAIN_MENU.equals(email)) {
                break;
            }
            if (!userCredentialsValidator.isEmailValid(email)) {
                System.out.println("Invalid email address.");
                continue;
            }
            try {
                if (userService.doesEmailExist(email)) {
                    System.out.println("User already exists.");
                    continue;
                }
            } catch (FbFileNotFoundException e) {
                System.out.println("Cannot add new user: user database not found.");
            }

            System.out.println("Enter a valid password (or input \"0\" to return to main menu):");
            password = keyboardScanner.nextLine();
            if (OPTION_MAIN_MENU.equals(password)) {
                break;
            }
            if (!userCredentialsValidator.isPasswordValid(password)) {
                System.out.println("Password is too weak. Please retry sign up.");
                continue;
            }

            try {
                userService.signUp(email, password);

            } catch (FbUserAlreadyExistsException e) {
                System.out.println("User already exists.");
            } catch (FbFileNotFoundException e) {
                System.out.println("Cannot assign new user password: user database not found.");
            }

            try {
                userService.login(email, password);
                System.out.println("User successfully created.");
                System.out.println("Login status: " + UserService.isUserPseudoLoggedIn + ".");
            } catch (FbWrongCredentialsException e) {
                System.out.println("Wrong credentials.");
            } catch (FbFileNotFoundException e) {
                System.out.println("Cannot automatically login new user: user database not found.");
            }

            break;

        } while (true);
    }
}
package com.facebook.ui.validator;

import com.facebook.exception.business.FbUserAlreadyExistsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCredentialsValidator {

    public boolean isEmailValid(String email) {

        String regex = ".+@.+?\\..+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password) {

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean areCredentialsValid(String email, String password) throws FbUserAlreadyExistsException {

        boolean areCredentialsValid = false;
        if (isEmailValid(email) && isPasswordValid(password)) {
            areCredentialsValid = true;
        }
        return areCredentialsValid;
    }
}

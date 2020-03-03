package com.facebook.service;

import com.facebook.dao.UserDAO;
import com.facebook.exception.business.FbUserAlreadyExistsException;
import com.facebook.exception.business.FbWrongCredentialsException;
import com.facebook.exception.technical.FbFileNotFoundException;
import com.facebook.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();
    public static boolean isUserPseudoLoggedIn = false;

    public User login(String email, String password) throws FbFileNotFoundException, FbWrongCredentialsException {

        try {
            for (User user : userDAO.readAllUsers()) {
                if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                    isUserPseudoLoggedIn = true;
                    return user;
                }
            }
        } catch (FbFileNotFoundException e) {
            throw new FbFileNotFoundException();
        }
        throw new FbWrongCredentialsException();
    }

    public void signUp(String email, String password) throws FbUserAlreadyExistsException, FbFileNotFoundException {

        try {
            if (doesEmailExist(email)) {
                throw new FbUserAlreadyExistsException();
            } else {
                User user = new User(email, password);
                String userData = user.getEmail() + ";" + user.getPassword();
                try {
                    userDAO.addUser(userData);
                } catch (FbFileNotFoundException e) {
                    throw new FbFileNotFoundException();
                }
            }
        } catch (FbFileNotFoundException e) {
            throw new FbFileNotFoundException();
        }
    }

    public boolean doesEmailExist(String email) throws FbFileNotFoundException {
        boolean doesEmailExist = false;
        try {
            for (User user : userDAO.readAllUsers()) {
                if (email.equals(user.getEmail())) {
                    doesEmailExist = true;
                    break;
                }
            }
        } catch (FbFileNotFoundException e) {
            throw new FbFileNotFoundException();
        }
        return doesEmailExist;
    }
}
package com.facebook.dao;

import com.facebook.exception.technical.FbFileNotFoundException;
import com.facebook.exception.technical.FbTechnicalException;
import com.facebook.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO {

    private static final String USERS_FILE_PATH = "src/main/resources/users.txt";

    FileEdit fileEdit = new FileEdit();

    public List<User> readAllUsers() throws FbFileNotFoundException {
        List<User> userList = new ArrayList<>();
        File usersFile = new File(USERS_FILE_PATH);
        try (Scanner userScanner = new Scanner(usersFile)) {
            while (userScanner.hasNextLine()) {
                String userLine = userScanner.nextLine();
                String[] userValues = userLine.split(";");
                userList.add(new User(userValues[0], userValues[1]));
            }
        } catch (FileNotFoundException e) {
            throw new FbFileNotFoundException();
        }
        return userList;
    }

    public void addUser(String userData) throws FbFileNotFoundException {
        try {
            fileEdit.write("users.txt", userData);
        } catch (FbFileNotFoundException e) {
            throw new FbFileNotFoundException();
        }
    }
}

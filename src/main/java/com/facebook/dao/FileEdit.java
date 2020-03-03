package com.facebook.dao;

import com.facebook.exception.technical.FbFileNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileEdit {

    void write(String fileName, String newContent) throws FbFileNotFoundException {
        String resourceFile = "src/main/resources/" + fileName;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resourceFile, true));
            bufferedWriter.newLine();
            bufferedWriter.write(newContent);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new FbFileNotFoundException();
        }
    }
}
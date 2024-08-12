package com.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifiedDate {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\del\\Desktop\\Apsar\\SmDzmUpdate\\jdj;\\resources\\application.properties";
        String contentToWrite = "This is the new content of the file.";
        String dateToCompare = "2024-08-01"; // yyyy-MM-dd format
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date specificDate = dateFormat.parse(dateToCompare);

            File file = new File(filePath);
            long lastModified = file.lastModified();
            Date fileLastModifiedDate = new Date(lastModified);

            if (fileLastModifiedDate.before(specificDate)) {
               // overwriteFile(filePath, contentToWrite);
                updateFileLastModifiedDate(file);
                System.out.println("File has been overwritten and the last modified date has been updated.");
            } else {
                System.out.println("File is not old enough to be overwritten.");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    

    //private static void overwriteFile(String filePath, String content) {
        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
        	String line;
            if( ((line = bf.readLine()) != null)) {
                System.out.println(line);
            }
        	bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateFileLastModifiedDate(File file) {
        long currentTime = System.currentTimeMillis();
        if (file.setLastModified(currentTime)) {
            System.out.println("File's last modified date updated to the current date.");
        } else {
            System.out.println("Failed to update the file's last modified date.");
        }
    }
}


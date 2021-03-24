package edu.bsu.cs222.model;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class APIKey {
    private String publicKey;
    private String hashKey;

    public APIKey() {
        File apiFile = new File
                (Objects.requireNonNull(getClass().getClassLoader().getResource("apiInformation.txt")).getFile());
        Alert badApi = new Alert(Alert.AlertType.ERROR);
        badApi.setContentText("Bad API file");
        if (apiFile.exists()) {
            try {
                Scanner fileReader = new Scanner(apiFile);
                publicKey = fileReader.nextLine();
                hashKey = fileReader.nextLine();
            } catch (FileNotFoundException e) {
                badApi.showAndWait();
                e.printStackTrace();
            }
        } else {
            badApi.showAndWait();
        }
    }

    public String getHashKey() {
        return hashKey;
    }

    public String getPublicKey() {
        return publicKey;
    }
}

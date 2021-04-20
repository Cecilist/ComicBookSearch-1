/*
 *  Program to search for comics and creators that Marvel has available information on.
 *  Copyright (C) 2021  Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see https://www.gnu.org/licenses/.
 */

package edu.bsu.cs222.model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class APIKey {
    private String publicKey;
    private String hashKey;

    public APIKey() {
        Alert badApi = new Alert(Alert.AlertType.ERROR);
        badApi.setContentText("Bad API file");
        try {
            File apiFile = new File
                    (Objects.requireNonNull(getClass().getClassLoader().getResource("apiInformation.txt")).getFile());
            Scanner fileReader = new Scanner(apiFile);
            publicKey = fileReader.nextLine();
            hashKey = fileReader.nextLine();
        } catch (Exception e) {
            badApi.showAndWait();
            Platform.exit();
        }
    }

    public String getHashKey() {
        return hashKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

}

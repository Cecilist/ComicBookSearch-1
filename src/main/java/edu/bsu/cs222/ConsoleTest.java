package edu.bsu.cs222;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class ConsoleTest {
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter a Marvel superhero: ");
        String marvelHeroName = URLEncoder.encode(userInput.nextLine(), StandardCharsets.UTF_8.toString());
        Character newCharacter = new Character();
        newCharacter = newCharacter.createCharacter(marvelHeroName);
        ComicBook newComicBook = new ComicBook();
        List<ComicBook> comicBooks = newComicBook.getComicBookData(newCharacter.getId());
        System.out.println("Character Name: " + newCharacter.getName());
        System.out.println("Character Id: " + newCharacter.getId());
        System.out.println("Character Description: " + newCharacter.getDescription());
        System.out.println("Character Thumbnail: " + newCharacter.getThumbnailURL());
        for (ComicBook comicbook : comicBooks) {
            System.out.println("********************************");
            System.out.println("Comic Book Title: " + comicbook.getTitle());
            System.out.println("Comic Book Description: " + comicbook.getDescription());
            System.out.println("Comic Book On Sale Date: " + comicbook.getOnsaleDate());
            System.out.println("Comic Book Thumbnail: " + comicbook.getThumbnailURL());
            System.out.println("********************************");
        }


    }


}

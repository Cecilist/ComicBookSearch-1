package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.ComicBook;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ComicBookTest {
    private List<ComicBook> comicBooks;

    @BeforeEach
    public void setup() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spider.json");
        JSONArray comicData = null;
        try {
            comicData = JsonPath.read(inputStream, "*");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ComicBook comic = new ComicBook();
        comicBooks = comic.createComicBooks(comicData);
    }

    @Test
    public void getTitle_TheAmazingSpiderMan_ReturnTheAmazingSpiderMan() {

        String title = comicBooks.get(2).getTitle();
        Assertions.assertEquals("The Amazing Spider-Man (1963) #1", title);
    }

    @Test
    public void getDescription_TheAmazingSpiderMan_ReturnDescription() {
        String description = comicBooks.get(2).getDescription();
        Assertions.assertEquals("Spider-Man, in one of his earliest adventures " +
                "following Uncle Ben's death, must save a crew of astronauts aboard a malfunctioning space ship!", description);
    }

    @Test
    public void getOnSaleDate_TheAmazingSpiderMan_ReturnMarch11963() {
        String saleDate = comicBooks.get(2).getFormattedSaleDate();
        Assertions.assertEquals("MARCH 1, 1963", saleDate);
    }

    @Test
    public void getOnSaleDate_WhatIfClassicVol42007_ReturnNull() {
        String saleDate = comicBooks.get(0).getFormattedSaleDate();
        Assertions.assertNull(saleDate);
    }

    @Test
    public void getCreatorName_TheAmazingSpiderMan_ReturnSolBrodsky() {
        String name = comicBooks.get(2).getCreators().get(0).getName();
        Assertions.assertEquals("Sol Brodsky", name);
    }


    @Test
    public void getCreatorRole_TheAmazingSpiderMan_ReturnInker() {
        String role = comicBooks.get(2).getCreators().get(0).getRole();
        Assertions.assertEquals("inker", role);
    }

}

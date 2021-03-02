package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ComicBookTest {

    @Test
    public void comicBookCreatorTest() throws IOException {
        ComicBook newComics = new ComicBook();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spider.json");
        List<ComicBook> comicBooks = newComics.createComicBooks(JsonPath.read(inputStream, "*"));
        System.out.println(comicBooks.toString());
    }
}

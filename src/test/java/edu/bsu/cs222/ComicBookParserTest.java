package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.ComicBook;
import edu.bsu.cs222.model.MarvelComicBookDataParser;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

public class ComicBookParserTest {
    ComicBook comicBook;

    @BeforeEach
    public void setup() {
        List<ComicBook> comicBooks = null;
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ComicBookData.json");
        JSONArray comicData = null;
        try {
            comicData = JsonPath.read(inputStream, "*");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MarvelComicBookDataParser comicBookDataParser = new MarvelComicBookDataParser();
        comicBookDataParser.setMarvelData(comicData);
        try {
            comicBooks = comicBookDataParser.createComicBooks();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert comicBooks != null;
        comicBook = comicBooks.get(2);
    }

    @Test
    public void getTitle_TheAmazingSpiderMan_ReturnTheAmazingSpiderMan() {
        Assertions.assertEquals("The Amazing Spider-Man (1963) #1", comicBook.getTitle());
    }

    @Test
    public void getDescription_TheAmazingSpiderMan_ReturnDescription() {
        Assertions.assertEquals('S', comicBook.getDescription().charAt(0));
    }

    @Test
    public void getOnSaleDate_TheAmazingSpiderMan_ReturnMarch11963() {
        Assertions.assertEquals("MARCH 1, 1963", comicBook.getFormattedSaleDate());
    }

    @Test
    public void getCreatorName_TheAmazingSpiderMan_ReturnSolBrodsky() {
        Assertions.assertEquals("Sol Brodsky", comicBook.getCreators().get(0).getName());
    }


    @Test
    public void getCreatorRole_TheAmazingSpiderMan_ReturnInker() {
        Assertions.assertEquals("inker", comicBook.getCreators().get(0).getRole());
    }

}

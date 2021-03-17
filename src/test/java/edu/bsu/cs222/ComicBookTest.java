package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.ComicBook;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ComicBookTest {
    public static List<ComicBook> comicBooks;
    @BeforeAll
    public static void testSetup(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("spider.json");
        JSONArray comicData = null;
            try {
                comicData = JsonPath.read(inputStream, "*");
            } catch (IOException e) {
                e.printStackTrace();
            }
        ComicBook comic = new ComicBook();
        comicBooks= comic.createComicBooks(comicData);
    }

    @Test
    public void getTitleTest() {

        String title = comicBooks.get(2).getTitle();
        Assertions.assertEquals("The Amazing Spider-Man (1963) #1", title);
    }

    @Test
    public void getDescriptTest(){
        String descript = comicBooks.get(2).getDescription();
        Assertions.assertEquals("Spider-Man, in one of his earliest adventures " +
                "following Uncle Ben's death, must save a crew of astronauts aboard a malfunctioning space ship!", descript);
    }

    @Test
    public void getsaleDateTest(){
        String saleDate = comicBooks.get(2).getOnSaleDate();
        Assertions.assertEquals("1963-03-01T00:00:00-0500", saleDate);
    }

    @Test
    public void getCreatorNameTest(){
        String name = comicBooks.get(2).getCreators().get(0).getName();
        Assertions.assertEquals("Sol Brodsky", name);
    }

    @Test
    public void getCreatorRoleTest() {
        String role = comicBooks.get(2).getCreators().get(0).getRole();
        Assertions.assertEquals("inker", role);
    }

}

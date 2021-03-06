package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ComicBookTest {


    private final ComicBook newComics = new ComicBook();
    private List<ComicBook> comicBooks;

    {
        try {
            comicBooks = newComics.createComicBooks("1009610");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String saleDate = comicBooks.get(2).getOnsaleDate();
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

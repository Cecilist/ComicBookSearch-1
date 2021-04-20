//  <Program to search for comics and creators that Marvel has available information on.>
//  Copyright (C) <2021>  <Lloyd Rowe, Jacob Cecil, Christopher Willis, Christopher Parrish>

//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program, SEE THE Copyright.txt FILE IN RESOURCES.  If not, see <https://www.gnu.org/licenses/>.

package edu.bsu.cs222.model;

import java.net.URL;

public class Creator implements MarvelObject {
    private String name;


    private String id;
    private String role;
    private URL thumbnailURL;
    private int comicsTotal;
    private int seriesTotal;
    private int storiesTotal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(URL thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getComicsTotal() {
        return comicsTotal;
    }

    public void setComicsTotal(int comicsTotal) {
        this.comicsTotal = comicsTotal;
    }

    public boolean hasComics() {
        return comicsTotal > 0;
    }

    public int getSeriesTotal() {
        return seriesTotal;
    }

    public void setSeriesTotal(int seriesTotal) {
        this.seriesTotal = seriesTotal;
    }

    public int getStoriesTotal() {
        return storiesTotal;
    }

    public void setStoriesTotal(int storiesTotal) {
        this.storiesTotal = storiesTotal;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreators() {
        return getRole() + ": " + getName() + "\n";
    }
}

/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

public class NicerButSlowerFilmListId implements Serializable {

    private Short fid;
    private String title;
    private String description;
    private String category;
    private Float price;
    private Short length;
    private String rating;
    private String actors;

    public Short getFid() {
        return this.fid;
    }

    public void setFid(Short fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getLength() {
        return this.length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NicerButSlowerFilmList)) return false;
        final NicerButSlowerFilmList nicerButSlowerFilmList = (NicerButSlowerFilmList) o;
        return Objects.equals(getFid(), nicerButSlowerFilmList.getFid()) &&
                Objects.equals(getTitle(), nicerButSlowerFilmList.getTitle()) &&
                Objects.equals(getDescription(), nicerButSlowerFilmList.getDescription()) &&
                Objects.equals(getCategory(), nicerButSlowerFilmList.getCategory()) &&
                Objects.equals(getPrice(), nicerButSlowerFilmList.getPrice()) &&
                Objects.equals(getLength(), nicerButSlowerFilmList.getLength()) &&
                Objects.equals(getRating(), nicerButSlowerFilmList.getRating()) &&
                Objects.equals(getActors(), nicerButSlowerFilmList.getActors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFid(),
                getTitle(),
                getDescription(),
                getCategory(),
                getPrice(),
                getLength(),
                getRating(),
                getActors());
    }
}
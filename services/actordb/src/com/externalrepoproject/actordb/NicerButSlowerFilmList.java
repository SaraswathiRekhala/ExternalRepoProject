/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * NicerButSlowerFilmList generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`nicer_but_slower_film_list`")
@IdClass(NicerButSlowerFilmListId.class)
public class NicerButSlowerFilmList implements Serializable {

    private Short fid = 0;
    private String title;
    private String description;
    private String category;
    private Float price = 4.99F;
    private Short length;
    private String rating = "G";
    private String actors;

    @Id
    @Column(name = "`FID`", nullable = true, scale = 0, precision = 5)
    public Short getFid() {
        return this.fid;
    }

    public void setFid(Short fid) {
        this.fid = fid;
    }

    @Id
    @Column(name = "`title`", nullable = true, length = 255)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "`description`", nullable = true, length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column(name = "`category`", nullable = false, length = 25)
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Id
    @Column(name = "`price`", nullable = true, scale = 2, precision = 4)
    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Id
    @Column(name = "`length`", nullable = true, scale = 0, precision = 5)
    public Short getLength() {
        return this.length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    @Id
    @Column(name = "`rating`", nullable = true, length = 5)
    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Id
    @Column(name = "`actors`", nullable = true, length = 65535)
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
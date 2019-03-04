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
 * ActorInfo generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`actor_info`")
@IdClass(ActorInfoId.class)
public class ActorInfo implements Serializable {

    private Short actorId = 0;
    private String firstName;
    private String lastName;
    private String filmInfo;

    @Id
    @Column(name = "`actor_id`", nullable = false, scale = 0, precision = 5)
    public Short getActorId() {
        return this.actorId;
    }

    public void setActorId(Short actorId) {
        this.actorId = actorId;
    }

    @Id
    @Column(name = "`first_name`", nullable = false, length = 45)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Id
    @Column(name = "`last_name`", nullable = false, length = 45)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Id
    @Column(name = "`film_info`", nullable = true, length = 65535)
    public String getFilmInfo() {
        return this.filmInfo;
    }

    public void setFilmInfo(String filmInfo) {
        this.filmInfo = filmInfo;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActorInfo)) return false;
        final ActorInfo actorInfo = (ActorInfo) o;
        return Objects.equals(getActorId(), actorInfo.getActorId()) &&
                Objects.equals(getFirstName(), actorInfo.getFirstName()) &&
                Objects.equals(getLastName(), actorInfo.getLastName()) &&
                Objects.equals(getFilmInfo(), actorInfo.getFilmInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActorId(),
                getFirstName(),
                getLastName(),
                getFilmInfo());
    }
}
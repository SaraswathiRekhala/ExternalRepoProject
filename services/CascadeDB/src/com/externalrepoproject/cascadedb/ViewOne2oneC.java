/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.cascadedb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * ViewOne2oneC generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`VIEW_ONE2ONE_C`")
@IdClass(ViewOne2oneCId.class)
public class ViewOne2oneC implements Serializable {

    private Integer childId;
    private String childColumn1;
    private String childColumn2;

    @Id
    @Column(name = "`CHILD_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getChildId() {
        return this.childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    @Id
    @Column(name = "`CHILD_COLUMN1`", nullable = true, length = 255)
    public String getChildColumn1() {
        return this.childColumn1;
    }

    public void setChildColumn1(String childColumn1) {
        this.childColumn1 = childColumn1;
    }

    @Id
    @Column(name = "`CHILD_COLUMN2`", nullable = true, length = 255)
    public String getChildColumn2() {
        return this.childColumn2;
    }

    public void setChildColumn2(String childColumn2) {
        this.childColumn2 = childColumn2;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewOne2oneC)) return false;
        final ViewOne2oneC viewOne2oneC = (ViewOne2oneC) o;
        return Objects.equals(getChildId(), viewOne2oneC.getChildId()) &&
                Objects.equals(getChildColumn1(), viewOne2oneC.getChildColumn1()) &&
                Objects.equals(getChildColumn2(), viewOne2oneC.getChildColumn2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChildId(),
                getChildColumn1(),
                getChildColumn2());
    }
}
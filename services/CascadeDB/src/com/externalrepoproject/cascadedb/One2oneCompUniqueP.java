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
 * One2oneCompUniqueP generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ONE2ONE_COMP_UNIQUE_P`")
@IdClass(One2oneCompUniquePId.class)
public class One2oneCompUniqueP implements Serializable {

    private String parentStringId;
    private Integer parentIntId;
    private String parentColumn1;
    private String parentColumn2;

    @Id
    @Column(name = "`PARENT_STRING_ID`", nullable = false, length = 50)
    public String getParentStringId() {
        return this.parentStringId;
    }

    public void setParentStringId(String parentStringId) {
        this.parentStringId = parentStringId;
    }

    @Id
    @Column(name = "`PARENT_INT_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getParentIntId() {
        return this.parentIntId;
    }

    public void setParentIntId(Integer parentIntId) {
        this.parentIntId = parentIntId;
    }

    @Column(name = "`PARENT_COLUMN1`", nullable = true, length = 255)
    public String getParentColumn1() {
        return this.parentColumn1;
    }

    public void setParentColumn1(String parentColumn1) {
        this.parentColumn1 = parentColumn1;
    }

    @Column(name = "`PARENT_COLUMN2`", nullable = true, length = 255)
    public String getParentColumn2() {
        return this.parentColumn2;
    }

    public void setParentColumn2(String parentColumn2) {
        this.parentColumn2 = parentColumn2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof One2oneCompUniqueP)) return false;
        final One2oneCompUniqueP one2oneCompUniqueP = (One2oneCompUniqueP) o;
        return Objects.equals(getParentStringId(), one2oneCompUniqueP.getParentStringId()) &&
                Objects.equals(getParentIntId(), one2oneCompUniqueP.getParentIntId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParentStringId(),
                getParentIntId());
    }
}
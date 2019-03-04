/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.cascadedb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * One2oneCompUniqueC generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ONE2ONE_COMP_UNIQUE_C`", uniqueConstraints = {
            @UniqueConstraint(name = "`UK_ONE2ONE_COMP_UNIQUE_CPixc6`", columnNames = {"`CHILD_INT_ID`", "`CHILD_STRING_ID`"})})
public class One2oneCompUniqueC implements Serializable {

    private Integer childId;
    private String childStringId;
    private String childColumn1;
    private String childColumn2;
    private Integer childIntId;
    private One2oneCompUniqueP one2oneCompUniqueP;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`CHILD_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getChildId() {
        return this.childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    @Column(name = "`CHILD_STRING_ID`", nullable = true, length = 50)
    public String getChildStringId() {
        return this.childStringId;
    }

    public void setChildStringId(String childStringId) {
        this.childStringId = childStringId;
    }

    @Column(name = "`CHILD_COLUMN1`", nullable = true, length = 255)
    public String getChildColumn1() {
        return this.childColumn1;
    }

    public void setChildColumn1(String childColumn1) {
        this.childColumn1 = childColumn1;
    }

    @Column(name = "`CHILD_COLUMN2`", nullable = true, length = 255)
    public String getChildColumn2() {
        return this.childColumn2;
    }

    public void setChildColumn2(String childColumn2) {
        this.childColumn2 = childColumn2;
    }

    @Column(name = "`CHILD_INT_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getChildIntId() {
        return this.childIntId;
    }

    public void setChildIntId(Integer childIntId) {
        this.childIntId = childIntId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "`CHILD_INT_ID`", referencedColumnName = "`PARENT_INT_ID`", insertable = false, updatable = false),
            @JoinColumn(name = "`CHILD_STRING_ID`", referencedColumnName = "`PARENT_STRING_ID`", insertable = false, updatable = false)},
        foreignKey = @ForeignKey(name = "`FK_ONE2ONE_COMP_UNIQUE_C3DyZ6`"))
    @Fetch(FetchMode.JOIN)
    public One2oneCompUniqueP getOne2oneCompUniqueP() {
        return this.one2oneCompUniqueP;
    }

    public void setOne2oneCompUniqueP(One2oneCompUniqueP one2oneCompUniqueP) {
        if(one2oneCompUniqueP != null) {
            this.childIntId = one2oneCompUniqueP.getParentIntId();
            this.childStringId = one2oneCompUniqueP.getParentStringId();
        }

        this.one2oneCompUniqueP = one2oneCompUniqueP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof One2oneCompUniqueC)) return false;
        final One2oneCompUniqueC one2oneCompUniqueC = (One2oneCompUniqueC) o;
        return Objects.equals(getChildId(), one2oneCompUniqueC.getChildId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChildId());
    }
}
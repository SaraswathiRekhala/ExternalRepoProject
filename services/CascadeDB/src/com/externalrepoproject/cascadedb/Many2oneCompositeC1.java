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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Many2oneCompositeC1 generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`MANY2ONE_COMPOSITE_C1`")
public class Many2oneCompositeC1 implements Serializable {

    private Integer childId;
    private Integer childIntId;
    private String childStringId;
    private String childColumn1;
    private String childColumn2;
    private One2manyCompositeP1 one2manyCompositeP1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`CHILD_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getChildId() {
        return this.childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    @Column(name = "`CHILD_INT_ID`", nullable = true, scale = 0, precision = 10)
    public Integer getChildIntId() {
        return this.childIntId;
    }

    public void setChildIntId(Integer childIntId) {
        this.childIntId = childIntId;
    }

    @Column(name = "`CHILD_STRING_ID`", nullable = true, length = 255)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(value = {
            @JoinColumn(name = "`CHILD_INT_ID`", referencedColumnName = "`PARENT_INT_ID`", insertable = false, updatable = false),
            @JoinColumn(name = "`CHILD_STRING_ID`", referencedColumnName = "`PARENT_STRING_ID`", insertable = false, updatable = false)},
        foreignKey = @ForeignKey(name = "`FK_MANY2ONE_COMPOSITE_C1ppCQr`"))
    @Fetch(FetchMode.JOIN)
    public One2manyCompositeP1 getOne2manyCompositeP1() {
        return this.one2manyCompositeP1;
    }

    public void setOne2manyCompositeP1(One2manyCompositeP1 one2manyCompositeP1) {
        if(one2manyCompositeP1 != null) {
            this.childIntId = one2manyCompositeP1.getParentIntId();
            this.childStringId = one2manyCompositeP1.getParentStringId();
        }

        this.one2manyCompositeP1 = one2manyCompositeP1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Many2oneCompositeC1)) return false;
        final Many2oneCompositeC1 many2oneCompositeC1 = (Many2oneCompositeC1) o;
        return Objects.equals(getChildId(), many2oneCompositeC1.getChildId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChildId());
    }
}
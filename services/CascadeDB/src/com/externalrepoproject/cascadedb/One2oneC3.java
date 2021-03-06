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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * One2oneC3 generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ONE2ONE_C3`")
public class One2oneC3 implements Serializable {

    private Integer child3Id;
    private String child3Column1;
    private String child3Column2;
    private One2onePMultipleC one2onePmultipleC;

    @Id
    @Column(name = "`CHILD3_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getChild3Id() {
        return this.child3Id;
    }

    public void setChild3Id(Integer child3Id) {
        this.child3Id = child3Id;
    }

    @Column(name = "`CHILD3_COLUMN1`", nullable = true, length = 255)
    public String getChild3Column1() {
        return this.child3Column1;
    }

    public void setChild3Column1(String child3Column1) {
        this.child3Column1 = child3Column1;
    }

    @Column(name = "`CHILD3_COLUMN2`", nullable = true, length = 255)
    public String getChild3Column2() {
        return this.child3Column2;
    }

    public void setChild3Column2(String child3Column2) {
        this.child3Column2 = child3Column2;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CHILD3_ID`", referencedColumnName = "`PARENT_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_ONE2ONE_C3_TO_ONE2ONEVseFJ`"))
    @Fetch(FetchMode.JOIN)
    public One2onePMultipleC getOne2onePmultipleC() {
        return this.one2onePmultipleC;
    }

    public void setOne2onePmultipleC(One2onePMultipleC one2onePmultipleC) {
        if(one2onePmultipleC != null) {
            this.child3Id = one2onePmultipleC.getParentId();
        }

        this.one2onePmultipleC = one2onePmultipleC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof One2oneC3)) return false;
        final One2oneC3 one2oneC3 = (One2oneC3) o;
        return Objects.equals(getChild3Id(), one2oneC3.getChild3Id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChild3Id());
    }
}
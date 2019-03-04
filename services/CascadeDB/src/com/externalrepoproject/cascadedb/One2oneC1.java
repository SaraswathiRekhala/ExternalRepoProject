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
 * One2oneC1 generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ONE2ONE_C1`")
public class One2oneC1 implements Serializable {

    private Integer child1Id;
    private String child1Column1;
    private String child1Column2;
    private One2onePMultipleC one2onePmultipleC;

    @Id
    @Column(name = "`CHILD1_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getChild1Id() {
        return this.child1Id;
    }

    public void setChild1Id(Integer child1Id) {
        this.child1Id = child1Id;
    }

    @Column(name = "`CHILD1_COLUMN1`", nullable = true, length = 255)
    public String getChild1Column1() {
        return this.child1Column1;
    }

    public void setChild1Column1(String child1Column1) {
        this.child1Column1 = child1Column1;
    }

    @Column(name = "`CHILD1_COLUMN2`", nullable = true, length = 255)
    public String getChild1Column2() {
        return this.child1Column2;
    }

    public void setChild1Column2(String child1Column2) {
        this.child1Column2 = child1Column2;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CHILD1_ID`", referencedColumnName = "`PARENT_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_ONE2ONE_C1_TO_ONE2ONE2HOfc`"))
    @Fetch(FetchMode.JOIN)
    public One2onePMultipleC getOne2onePmultipleC() {
        return this.one2onePmultipleC;
    }

    public void setOne2onePmultipleC(One2onePMultipleC one2onePmultipleC) {
        if(one2onePmultipleC != null) {
            this.child1Id = one2onePmultipleC.getParentId();
        }

        this.one2onePmultipleC = one2onePmultipleC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof One2oneC1)) return false;
        final One2oneC1 one2oneC1 = (One2oneC1) o;
        return Objects.equals(getChild1Id(), one2oneC1.getChild1Id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChild1Id());
    }
}
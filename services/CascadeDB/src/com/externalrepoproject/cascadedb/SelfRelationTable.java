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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SelfRelationTable generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`SELF_RELATION_TABLE`")
public class SelfRelationTable implements Serializable {

    private Integer pkId;
    private String srColumn1;
    private String srColumn2;
    private Integer srRefid = 11;
    private SelfRelationTable selfRelationTableBySrRefid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`PK_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getPkId() {
        return this.pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    @Column(name = "`SR_COLUMN1`", nullable = true, length = 255)
    public String getSrColumn1() {
        return this.srColumn1;
    }

    public void setSrColumn1(String srColumn1) {
        this.srColumn1 = srColumn1;
    }

    @Column(name = "`SR_COLUMN2`", nullable = true, length = 255)
    public String getSrColumn2() {
        return this.srColumn2;
    }

    public void setSrColumn2(String srColumn2) {
        this.srColumn2 = srColumn2;
    }

    @Column(name = "`SR_REFID`", nullable = true, scale = 0, precision = 10)
    public Integer getSrRefid() {
        return this.srRefid;
    }

    public void setSrRefid(Integer srRefid) {
        this.srRefid = srRefid;
    }

    // ignoring self relation properties to avoid circular loops & unwanted fields from the related entity.
    @JsonIgnoreProperties({"selfRelationTableBySrRefid", "selfRelationTablesForSrRefid"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`SR_REFID`", referencedColumnName = "`PK_ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_SELF_RELATION_TABLE_Tv4uYY`"))
    @Fetch(FetchMode.JOIN)
    public SelfRelationTable getSelfRelationTableBySrRefid() {
        return this.selfRelationTableBySrRefid;
    }

    public void setSelfRelationTableBySrRefid(SelfRelationTable selfRelationTableBySrRefid) {
        if(selfRelationTableBySrRefid != null) {
            this.srRefid = selfRelationTableBySrRefid.getPkId();
        }

        this.selfRelationTableBySrRefid = selfRelationTableBySrRefid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelfRelationTable)) return false;
        final SelfRelationTable selfRelationTable = (SelfRelationTable) o;
        return Objects.equals(getPkId(), selfRelationTable.getPkId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPkId());
    }
}
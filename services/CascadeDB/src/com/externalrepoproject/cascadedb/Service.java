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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Service generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`service`")
public class Service implements Serializable {

    private Integer serviceId;
    private String column2;
    private String column3;
    private Integer column4;
    private ServiceRef serviceRef;
    private Service serviceByColumn4;

    @Id
    @Column(name = "`Service_Id`", nullable = false, scale = 0, precision = 10)
    public Integer getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Column(name = "`COLUMN2`", nullable = true, length = 255)
    public String getColumn2() {
        return this.column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    @Column(name = "`COLUMN3`", nullable = true, length = 255)
    public String getColumn3() {
        return this.column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    @Column(name = "`COLUMN4`", nullable = true, scale = 0, precision = 10)
    public Integer getColumn4() {
        return this.column4;
    }

    public void setColumn4(Integer column4) {
        this.column4 = column4;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`COLUMN2`", referencedColumnName = "`ID`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_service_TO_Service_Rer35Ck`"))
    @Fetch(FetchMode.JOIN)
    public ServiceRef getServiceRef() {
        return this.serviceRef;
    }

    public void setServiceRef(ServiceRef serviceRef) {
        if(serviceRef != null) {
            this.column2 = serviceRef.getId();
        }

        this.serviceRef = serviceRef;
    }

    // ignoring self relation properties to avoid circular loops & unwanted fields from the related entity.
    @JsonIgnoreProperties({"serviceByColumn4", "servicesForColumn4"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`COLUMN4`", referencedColumnName = "`Service_Id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_service_TO_service_COiC7ai`"))
    @Fetch(FetchMode.JOIN)
    public Service getServiceByColumn4() {
        return this.serviceByColumn4;
    }

    public void setServiceByColumn4(Service serviceByColumn4) {
        if(serviceByColumn4 != null) {
            this.column4 = serviceByColumn4.getServiceId();
        }

        this.serviceByColumn4 = serviceByColumn4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        final Service service = (Service) o;
        return Objects.equals(getServiceId(), service.getServiceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceId());
    }
}
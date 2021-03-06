/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Timestamp;
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Address generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`address`")
public class Address implements Serializable {

    private Short addressId;
    private String address;
    private String address2;
    private String district;
    private short cityId;
    private String postalCode;
    private String phone;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] location;
    private Timestamp lastUpdate;
    private City city;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`address_id`", nullable = false, scale = 0, precision = 5)
    public Short getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Short addressId) {
        this.addressId = addressId;
    }

    @Column(name = "`address`", nullable = false, length = 50)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "`address2`", nullable = true, length = 50)
    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "`district`", nullable = false, length = 20)
    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "`city_id`", nullable = false, scale = 0, precision = 5)
    public short getCityId() {
        return this.cityId;
    }

    public void setCityId(short cityId) {
        this.cityId = cityId;
    }

    @Column(name = "`postal_code`", nullable = true, length = 10)
    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "`phone`", nullable = false, length = 20)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "`location`", nullable = false)
    public byte[] getLocation() {
        return this.location;
    }

    public void setLocation(byte[] location) {
        this.location = location;
    }

    @Column(name = "`last_update`", nullable = false)
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`city_id`", referencedColumnName = "`city_id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`fk_address_city`"))
    @Fetch(FetchMode.JOIN)
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        if(city != null) {
            this.cityId = city.getCityId();
        }

        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        final Address addressInstance = (Address) o;
        return Objects.equals(getAddressId(), addressInstance.getAddressId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressId());
    }
}
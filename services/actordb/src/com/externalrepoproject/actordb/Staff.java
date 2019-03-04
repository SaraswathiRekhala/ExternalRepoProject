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
 * Staff generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`staff`")
public class Staff implements Serializable {

    private Short staffId;
    private String firstName;
    private String lastName;
    private short addressId;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] picture;
    private String email;
    private short storeId;
    private boolean active = true;
    private String username;
    private String password;
    private Timestamp lastUpdate;
    private Address address;
    private Store storeByStoreId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`staff_id`", nullable = false, scale = 0, precision = 3)
    public Short getStaffId() {
        return this.staffId;
    }

    public void setStaffId(Short staffId) {
        this.staffId = staffId;
    }

    @Column(name = "`first_name`", nullable = false, length = 45)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "`last_name`", nullable = false, length = 45)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "`address_id`", nullable = false, scale = 0, precision = 5)
    public short getAddressId() {
        return this.addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
    }

    @Column(name = "`picture`", nullable = true)
    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Column(name = "`email`", nullable = true, length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "`store_id`", nullable = false, scale = 0, precision = 3)
    public short getStoreId() {
        return this.storeId;
    }

    public void setStoreId(short storeId) {
        this.storeId = storeId;
    }

    @Column(name = "`active`", nullable = false)
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "`username`", nullable = false, length = 16)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "`password`", nullable = true, length = 40)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "`last_update`", nullable = false)
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`address_id`", referencedColumnName = "`address_id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`fk_staff_address`"))
    @Fetch(FetchMode.JOIN)
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        if(address != null) {
            this.addressId = address.getAddressId();
        }

        this.address = address;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`store_id`", referencedColumnName = "`store_id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`fk_staff_store`"))
    @Fetch(FetchMode.JOIN)
    public Store getStoreByStoreId() {
        return this.storeByStoreId;
    }

    public void setStoreByStoreId(Store storeByStoreId) {
        if(storeByStoreId != null) {
            this.storeId = storeByStoreId.getStoreId();
        }

        this.storeByStoreId = storeByStoreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        final Staff staff = (Staff) o;
        return Objects.equals(getStaffId(), staff.getStaffId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStaffId());
    }
}
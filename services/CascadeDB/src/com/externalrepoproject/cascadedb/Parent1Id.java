/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.cascadedb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

public class Parent1Id implements Serializable {

    private Integer pid;
    private String firstName;
    private String lastName;
    private String column5;

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getColumn5() {
        return this.column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parent1)) return false;
        final Parent1 parent1 = (Parent1) o;
        return Objects.equals(getPid(), parent1.getPid()) &&
                Objects.equals(getFirstName(), parent1.getFirstName()) &&
                Objects.equals(getLastName(), parent1.getLastName()) &&
                Objects.equals(getColumn5(), parent1.getColumn5());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPid(),
                getFirstName(),
                getLastName(),
                getColumn5());
    }
}
/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class SalesByFilmCategoryId implements Serializable {

    private String category;
    private BigDecimal totalSales;

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getTotalSales() {
        return this.totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesByFilmCategory)) return false;
        final SalesByFilmCategory salesByFilmCategory = (SalesByFilmCategory) o;
        return Objects.equals(getCategory(), salesByFilmCategory.getCategory()) &&
                Objects.equals(getTotalSales(), salesByFilmCategory.getTotalSales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(),
                getTotalSales());
    }
}
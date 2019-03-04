/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.externalrepoproject.actordb.Address;
import com.externalrepoproject.actordb.City;


/**
 * ServiceImpl object for domain model class City.
 *
 * @see City
 */
@Service("actordb.CityService")
@Validated
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("actordb.AddressService")
    private AddressService addressService;

    @Autowired
    @Qualifier("actordb.CityDao")
    private WMGenericDao<City, Short> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<City, Short> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public City create(City cityInstance) {
        LOGGER.debug("Creating a new City with information: {}", cityInstance);

        City cityInstanceCreated = this.wmGenericDao.create(cityInstance);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(cityInstanceCreated);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public City getById(Short cityIdInstance) {
        LOGGER.debug("Finding City by id: {}", cityIdInstance);
        return this.wmGenericDao.findById(cityIdInstance);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public City findById(Short cityIdInstance) {
        LOGGER.debug("Finding City by id: {}", cityIdInstance);
        try {
            return this.wmGenericDao.findById(cityIdInstance);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No City found with id: {}", cityIdInstance, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public List<City> findByMultipleIds(List<Short> cityIdInstances, boolean orderedReturn) {
        LOGGER.debug("Finding Cities by ids: {}", cityIdInstances);

        return this.wmGenericDao.findByMultipleIds(cityIdInstances, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "actordbTransactionManager")
    @Override
    public City update(City cityInstance) {
        LOGGER.debug("Updating City with information: {}", cityInstance);

        this.wmGenericDao.update(cityInstance);
        this.wmGenericDao.refresh(cityInstance);

        return cityInstance;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public City delete(Short cityIdInstance) {
        LOGGER.debug("Deleting City with id: {}", cityIdInstance);
        City deleted = this.wmGenericDao.findById(cityIdInstance);
        if (deleted == null) {
            LOGGER.debug("No City found with id: {}", cityIdInstance);
            throw new EntityNotFoundException(String.valueOf(cityIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public void delete(City cityInstance) {
        LOGGER.debug("Deleting City with {}", cityInstance);
        this.wmGenericDao.delete(cityInstance);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<City> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Cities");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<City> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Cities");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service actordb for table City to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service actordb for table City to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Address> findAssociatedAddresses(Short cityId, Pageable pageable) {
        LOGGER.debug("Fetching all associated addresses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("city.cityId = '" + cityId + "'");

        return addressService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service AddressService instance
     */
    protected void setAddressService(AddressService service) {
        this.addressService = service;
    }

}
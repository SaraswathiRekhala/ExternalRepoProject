/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.cascadedb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.externalrepoproject.cascadedb.One2oneucPNotnull;


/**
 * ServiceImpl object for domain model class One2oneucPNotnull.
 *
 * @see One2oneucPNotnull
 */
@Service("CascadeDB.One2oneucPNotnullService")
@Validated
public class One2oneucPNotnullServiceImpl implements One2oneucPNotnullService {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2oneucPNotnullServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.One2oneucPNotnullDao")
    private WMGenericDao<One2oneucPNotnull, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<One2oneucPNotnull, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneucPNotnull create(One2oneucPNotnull one2oneucPnotnull) {
        LOGGER.debug("Creating a new One2oneucPNotnull with information: {}", one2oneucPnotnull);

        One2oneucPNotnull one2oneucPnotnullCreated = this.wmGenericDao.create(one2oneucPnotnull);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(one2oneucPnotnullCreated);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneucPNotnull getById(Integer one2oneucpnotnullId) {
        LOGGER.debug("Finding One2oneucPNotnull by id: {}", one2oneucpnotnullId);
        return this.wmGenericDao.findById(one2oneucpnotnullId);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneucPNotnull findById(Integer one2oneucpnotnullId) {
        LOGGER.debug("Finding One2oneucPNotnull by id: {}", one2oneucpnotnullId);
        try {
            return this.wmGenericDao.findById(one2oneucpnotnullId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No One2oneucPNotnull found with id: {}", one2oneucpnotnullId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<One2oneucPNotnull> findByMultipleIds(List<Integer> one2oneucpnotnullIds, boolean orderedReturn) {
        LOGGER.debug("Finding One2oneucPNotnulls by ids: {}", one2oneucpnotnullIds);

        return this.wmGenericDao.findByMultipleIds(one2oneucpnotnullIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public One2oneucPNotnull update(One2oneucPNotnull one2oneucPnotnull) {
        LOGGER.debug("Updating One2oneucPNotnull with information: {}", one2oneucPnotnull);

        this.wmGenericDao.update(one2oneucPnotnull);
        this.wmGenericDao.refresh(one2oneucPnotnull);

        return one2oneucPnotnull;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneucPNotnull delete(Integer one2oneucpnotnullId) {
        LOGGER.debug("Deleting One2oneucPNotnull with id: {}", one2oneucpnotnullId);
        One2oneucPNotnull deleted = this.wmGenericDao.findById(one2oneucpnotnullId);
        if (deleted == null) {
            LOGGER.debug("No One2oneucPNotnull found with id: {}", one2oneucpnotnullId);
            throw new EntityNotFoundException(String.valueOf(one2oneucpnotnullId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(One2oneucPNotnull one2oneucPnotnull) {
        LOGGER.debug("Deleting One2oneucPNotnull with {}", one2oneucPnotnull);
        this.wmGenericDao.delete(one2oneucPnotnull);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneucPNotnull> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all One2oneucPNotnulls");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneucPNotnull> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all One2oneucPNotnulls");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneucPNotnull to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneucPNotnull to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}
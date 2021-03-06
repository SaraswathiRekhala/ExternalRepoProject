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

import com.externalrepoproject.cascadedb.One2oneUcP;


/**
 * ServiceImpl object for domain model class One2oneUcP.
 *
 * @see One2oneUcP
 */
@Service("CascadeDB.One2oneUcPService")
@Validated
public class One2oneUcPServiceImpl implements One2oneUcPService {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2oneUcPServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.One2oneUcPDao")
    private WMGenericDao<One2oneUcP, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<One2oneUcP, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneUcP create(One2oneUcP one2oneUcP) {
        LOGGER.debug("Creating a new One2oneUcP with information: {}", one2oneUcP);

        One2oneUcP one2oneUcPCreated = this.wmGenericDao.create(one2oneUcP);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(one2oneUcPCreated);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneUcP getById(Integer one2oneucpId) {
        LOGGER.debug("Finding One2oneUcP by id: {}", one2oneucpId);
        return this.wmGenericDao.findById(one2oneucpId);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneUcP findById(Integer one2oneucpId) {
        LOGGER.debug("Finding One2oneUcP by id: {}", one2oneucpId);
        try {
            return this.wmGenericDao.findById(one2oneucpId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No One2oneUcP found with id: {}", one2oneucpId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<One2oneUcP> findByMultipleIds(List<Integer> one2oneucpIds, boolean orderedReturn) {
        LOGGER.debug("Finding One2oneUcPs by ids: {}", one2oneucpIds);

        return this.wmGenericDao.findByMultipleIds(one2oneucpIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public One2oneUcP update(One2oneUcP one2oneUcP) {
        LOGGER.debug("Updating One2oneUcP with information: {}", one2oneUcP);

        this.wmGenericDao.update(one2oneUcP);
        this.wmGenericDao.refresh(one2oneUcP);

        return one2oneUcP;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneUcP delete(Integer one2oneucpId) {
        LOGGER.debug("Deleting One2oneUcP with id: {}", one2oneucpId);
        One2oneUcP deleted = this.wmGenericDao.findById(one2oneucpId);
        if (deleted == null) {
            LOGGER.debug("No One2oneUcP found with id: {}", one2oneucpId);
            throw new EntityNotFoundException(String.valueOf(one2oneucpId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(One2oneUcP one2oneUcP) {
        LOGGER.debug("Deleting One2oneUcP with {}", one2oneUcP);
        this.wmGenericDao.delete(one2oneUcP);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneUcP> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all One2oneUcPs");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneUcP> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all One2oneUcPs");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneUcP to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneUcP to {} format", options.getExportType());
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
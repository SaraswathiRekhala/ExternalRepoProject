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

import com.externalrepoproject.cascadedb.One2oneCompUniqueP;
import com.externalrepoproject.cascadedb.One2oneCompUniquePId;


/**
 * ServiceImpl object for domain model class One2oneCompUniqueP.
 *
 * @see One2oneCompUniqueP
 */
@Service("CascadeDB.One2oneCompUniquePService")
@Validated
public class One2oneCompUniquePServiceImpl implements One2oneCompUniquePService {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2oneCompUniquePServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.One2oneCompUniquePDao")
    private WMGenericDao<One2oneCompUniqueP, One2oneCompUniquePId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<One2oneCompUniqueP, One2oneCompUniquePId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneCompUniqueP create(One2oneCompUniqueP one2oneCompUniqueP) {
        LOGGER.debug("Creating a new One2oneCompUniqueP with information: {}", one2oneCompUniqueP);

        One2oneCompUniqueP one2oneCompUniquePCreated = this.wmGenericDao.create(one2oneCompUniqueP);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(one2oneCompUniquePCreated);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneCompUniqueP getById(One2oneCompUniquePId one2onecompuniquepId) {
        LOGGER.debug("Finding One2oneCompUniqueP by id: {}", one2onecompuniquepId);
        return this.wmGenericDao.findById(one2onecompuniquepId);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneCompUniqueP findById(One2oneCompUniquePId one2onecompuniquepId) {
        LOGGER.debug("Finding One2oneCompUniqueP by id: {}", one2onecompuniquepId);
        try {
            return this.wmGenericDao.findById(one2onecompuniquepId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No One2oneCompUniqueP found with id: {}", one2onecompuniquepId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<One2oneCompUniqueP> findByMultipleIds(List<One2oneCompUniquePId> one2onecompuniquepIds, boolean orderedReturn) {
        LOGGER.debug("Finding One2oneCompUniquePs by ids: {}", one2onecompuniquepIds);

        return this.wmGenericDao.findByMultipleIds(one2onecompuniquepIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public One2oneCompUniqueP update(One2oneCompUniqueP one2oneCompUniqueP) {
        LOGGER.debug("Updating One2oneCompUniqueP with information: {}", one2oneCompUniqueP);

        this.wmGenericDao.update(one2oneCompUniqueP);
        this.wmGenericDao.refresh(one2oneCompUniqueP);

        return one2oneCompUniqueP;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneCompUniqueP delete(One2oneCompUniquePId one2onecompuniquepId) {
        LOGGER.debug("Deleting One2oneCompUniqueP with id: {}", one2onecompuniquepId);
        One2oneCompUniqueP deleted = this.wmGenericDao.findById(one2onecompuniquepId);
        if (deleted == null) {
            LOGGER.debug("No One2oneCompUniqueP found with id: {}", one2onecompuniquepId);
            throw new EntityNotFoundException(String.valueOf(one2onecompuniquepId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(One2oneCompUniqueP one2oneCompUniqueP) {
        LOGGER.debug("Deleting One2oneCompUniqueP with {}", one2oneCompUniqueP);
        this.wmGenericDao.delete(one2oneCompUniqueP);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneCompUniqueP> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all One2oneCompUniquePs");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneCompUniqueP> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all One2oneCompUniquePs");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneCompUniqueP to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneCompUniqueP to {} format", options.getExportType());
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
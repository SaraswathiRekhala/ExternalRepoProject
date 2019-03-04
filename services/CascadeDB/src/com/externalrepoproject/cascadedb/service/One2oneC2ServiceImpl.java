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

import com.externalrepoproject.cascadedb.One2oneC2;


/**
 * ServiceImpl object for domain model class One2oneC2.
 *
 * @see One2oneC2
 */
@Service("CascadeDB.One2oneC2Service")
@Validated
public class One2oneC2ServiceImpl implements One2oneC2Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2oneC2ServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.One2oneC2Dao")
    private WMGenericDao<One2oneC2, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<One2oneC2, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneC2 create(One2oneC2 one2oneC2) {
        LOGGER.debug("Creating a new One2oneC2 with information: {}", one2oneC2);

        One2oneC2 one2oneC2Created = this.wmGenericDao.create(one2oneC2);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(one2oneC2Created);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneC2 getById(Integer one2onec2Id) {
        LOGGER.debug("Finding One2oneC2 by id: {}", one2onec2Id);
        return this.wmGenericDao.findById(one2onec2Id);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneC2 findById(Integer one2onec2Id) {
        LOGGER.debug("Finding One2oneC2 by id: {}", one2onec2Id);
        try {
            return this.wmGenericDao.findById(one2onec2Id);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No One2oneC2 found with id: {}", one2onec2Id, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<One2oneC2> findByMultipleIds(List<Integer> one2onec2Ids, boolean orderedReturn) {
        LOGGER.debug("Finding One2oneC2s by ids: {}", one2onec2Ids);

        return this.wmGenericDao.findByMultipleIds(one2onec2Ids, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public One2oneC2 update(One2oneC2 one2oneC2) {
        LOGGER.debug("Updating One2oneC2 with information: {}", one2oneC2);

        this.wmGenericDao.update(one2oneC2);
        this.wmGenericDao.refresh(one2oneC2);

        return one2oneC2;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneC2 delete(Integer one2onec2Id) {
        LOGGER.debug("Deleting One2oneC2 with id: {}", one2onec2Id);
        One2oneC2 deleted = this.wmGenericDao.findById(one2onec2Id);
        if (deleted == null) {
            LOGGER.debug("No One2oneC2 found with id: {}", one2onec2Id);
            throw new EntityNotFoundException(String.valueOf(one2onec2Id));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(One2oneC2 one2oneC2) {
        LOGGER.debug("Deleting One2oneC2 with {}", one2oneC2);
        this.wmGenericDao.delete(one2oneC2);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneC2> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all One2oneC2s");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneC2> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all One2oneC2s");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneC2 to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneC2 to {} format", options.getExportType());
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
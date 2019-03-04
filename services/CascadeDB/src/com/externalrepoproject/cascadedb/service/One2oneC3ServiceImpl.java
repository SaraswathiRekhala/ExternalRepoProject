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

import com.externalrepoproject.cascadedb.One2oneC3;


/**
 * ServiceImpl object for domain model class One2oneC3.
 *
 * @see One2oneC3
 */
@Service("CascadeDB.One2oneC3Service")
@Validated
public class One2oneC3ServiceImpl implements One2oneC3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2oneC3ServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.One2oneC3Dao")
    private WMGenericDao<One2oneC3, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<One2oneC3, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneC3 create(One2oneC3 one2oneC3) {
        LOGGER.debug("Creating a new One2oneC3 with information: {}", one2oneC3);

        One2oneC3 one2oneC3Created = this.wmGenericDao.create(one2oneC3);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(one2oneC3Created);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneC3 getById(Integer one2onec3Id) {
        LOGGER.debug("Finding One2oneC3 by id: {}", one2onec3Id);
        return this.wmGenericDao.findById(one2onec3Id);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public One2oneC3 findById(Integer one2onec3Id) {
        LOGGER.debug("Finding One2oneC3 by id: {}", one2onec3Id);
        try {
            return this.wmGenericDao.findById(one2onec3Id);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No One2oneC3 found with id: {}", one2onec3Id, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<One2oneC3> findByMultipleIds(List<Integer> one2onec3Ids, boolean orderedReturn) {
        LOGGER.debug("Finding One2oneC3s by ids: {}", one2onec3Ids);

        return this.wmGenericDao.findByMultipleIds(one2onec3Ids, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public One2oneC3 update(One2oneC3 one2oneC3) {
        LOGGER.debug("Updating One2oneC3 with information: {}", one2oneC3);

        this.wmGenericDao.update(one2oneC3);
        this.wmGenericDao.refresh(one2oneC3);

        return one2oneC3;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public One2oneC3 delete(Integer one2onec3Id) {
        LOGGER.debug("Deleting One2oneC3 with id: {}", one2onec3Id);
        One2oneC3 deleted = this.wmGenericDao.findById(one2onec3Id);
        if (deleted == null) {
            LOGGER.debug("No One2oneC3 found with id: {}", one2onec3Id);
            throw new EntityNotFoundException(String.valueOf(one2onec3Id));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(One2oneC3 one2oneC3) {
        LOGGER.debug("Deleting One2oneC3 with {}", one2oneC3);
        this.wmGenericDao.delete(one2oneC3);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneC3> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all One2oneC3s");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<One2oneC3> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all One2oneC3s");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneC3 to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table One2oneC3 to {} format", options.getExportType());
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
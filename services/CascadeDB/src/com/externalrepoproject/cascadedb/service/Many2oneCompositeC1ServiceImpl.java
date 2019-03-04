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

import com.externalrepoproject.cascadedb.Many2oneCompositeC1;


/**
 * ServiceImpl object for domain model class Many2oneCompositeC1.
 *
 * @see Many2oneCompositeC1
 */
@Service("CascadeDB.Many2oneCompositeC1Service")
@Validated
public class Many2oneCompositeC1ServiceImpl implements Many2oneCompositeC1Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Many2oneCompositeC1ServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.Many2oneCompositeC1Dao")
    private WMGenericDao<Many2oneCompositeC1, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Many2oneCompositeC1, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public Many2oneCompositeC1 create(Many2oneCompositeC1 many2oneCompositeC1) {
        LOGGER.debug("Creating a new Many2oneCompositeC1 with information: {}", many2oneCompositeC1);

        Many2oneCompositeC1 many2oneCompositeC1Created = this.wmGenericDao.create(many2oneCompositeC1);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(many2oneCompositeC1Created);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Many2oneCompositeC1 getById(Integer many2onecompositec1Id) {
        LOGGER.debug("Finding Many2oneCompositeC1 by id: {}", many2onecompositec1Id);
        return this.wmGenericDao.findById(many2onecompositec1Id);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Many2oneCompositeC1 findById(Integer many2onecompositec1Id) {
        LOGGER.debug("Finding Many2oneCompositeC1 by id: {}", many2onecompositec1Id);
        try {
            return this.wmGenericDao.findById(many2onecompositec1Id);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Many2oneCompositeC1 found with id: {}", many2onecompositec1Id, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<Many2oneCompositeC1> findByMultipleIds(List<Integer> many2onecompositec1Ids, boolean orderedReturn) {
        LOGGER.debug("Finding Many2oneCompositeC1s by ids: {}", many2onecompositec1Ids);

        return this.wmGenericDao.findByMultipleIds(many2onecompositec1Ids, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public Many2oneCompositeC1 update(Many2oneCompositeC1 many2oneCompositeC1) {
        LOGGER.debug("Updating Many2oneCompositeC1 with information: {}", many2oneCompositeC1);

        this.wmGenericDao.update(many2oneCompositeC1);
        this.wmGenericDao.refresh(many2oneCompositeC1);

        return many2oneCompositeC1;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public Many2oneCompositeC1 delete(Integer many2onecompositec1Id) {
        LOGGER.debug("Deleting Many2oneCompositeC1 with id: {}", many2onecompositec1Id);
        Many2oneCompositeC1 deleted = this.wmGenericDao.findById(many2onecompositec1Id);
        if (deleted == null) {
            LOGGER.debug("No Many2oneCompositeC1 found with id: {}", many2onecompositec1Id);
            throw new EntityNotFoundException(String.valueOf(many2onecompositec1Id));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(Many2oneCompositeC1 many2oneCompositeC1) {
        LOGGER.debug("Deleting Many2oneCompositeC1 with {}", many2oneCompositeC1);
        this.wmGenericDao.delete(many2oneCompositeC1);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Many2oneCompositeC1> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Many2oneCompositeC1s");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Many2oneCompositeC1> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Many2oneCompositeC1s");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table Many2oneCompositeC1 to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table Many2oneCompositeC1 to {} format", options.getExportType());
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
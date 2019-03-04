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

import com.externalrepoproject.cascadedb.Many2oneC1;
import com.externalrepoproject.cascadedb.Many2oneP;


/**
 * ServiceImpl object for domain model class Many2oneP.
 *
 * @see Many2oneP
 */
@Service("CascadeDB.Many2onePService")
@Validated
public class Many2onePServiceImpl implements Many2onePService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Many2onePServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("CascadeDB.Many2oneC1Service")
    private Many2oneC1Service many2oneC1Service;

    @Autowired
    @Qualifier("CascadeDB.Many2onePDao")
    private WMGenericDao<Many2oneP, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Many2oneP, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public Many2oneP create(Many2oneP many2oneP) {
        LOGGER.debug("Creating a new Many2oneP with information: {}", many2oneP);

        Many2oneP many2onePCreated = this.wmGenericDao.create(many2oneP);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(many2onePCreated);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Many2oneP getById(Integer many2onepId) {
        LOGGER.debug("Finding Many2oneP by id: {}", many2onepId);
        return this.wmGenericDao.findById(many2onepId);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Many2oneP findById(Integer many2onepId) {
        LOGGER.debug("Finding Many2oneP by id: {}", many2onepId);
        try {
            return this.wmGenericDao.findById(many2onepId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Many2oneP found with id: {}", many2onepId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<Many2oneP> findByMultipleIds(List<Integer> many2onepIds, boolean orderedReturn) {
        LOGGER.debug("Finding Many2onePs by ids: {}", many2onepIds);

        return this.wmGenericDao.findByMultipleIds(many2onepIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public Many2oneP update(Many2oneP many2oneP) {
        LOGGER.debug("Updating Many2oneP with information: {}", many2oneP);

        this.wmGenericDao.update(many2oneP);
        this.wmGenericDao.refresh(many2oneP);

        return many2oneP;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public Many2oneP delete(Integer many2onepId) {
        LOGGER.debug("Deleting Many2oneP with id: {}", many2onepId);
        Many2oneP deleted = this.wmGenericDao.findById(many2onepId);
        if (deleted == null) {
            LOGGER.debug("No Many2oneP found with id: {}", many2onepId);
            throw new EntityNotFoundException(String.valueOf(many2onepId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(Many2oneP many2oneP) {
        LOGGER.debug("Deleting Many2oneP with {}", many2oneP);
        this.wmGenericDao.delete(many2oneP);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Many2oneP> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Many2onePs");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Many2oneP> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Many2onePs");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table Many2oneP to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table Many2oneP to {} format", options.getExportType());
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

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Many2oneC1> findAssociatedMany2oneC1s(Integer parentId, Pageable pageable) {
        LOGGER.debug("Fetching all associated many2oneC1s");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("many2oneP.parentId = '" + parentId + "'");

        return many2oneC1Service.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service Many2oneC1Service instance
     */
    protected void setMany2oneC1Service(Many2oneC1Service service) {
        this.many2oneC1Service = service;
    }

}
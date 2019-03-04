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

import com.externalrepoproject.cascadedb.Blobhidden;


/**
 * ServiceImpl object for domain model class Blobhidden.
 *
 * @see Blobhidden
 */
@Service("CascadeDB.BlobhiddenService")
@Validated
public class BlobhiddenServiceImpl implements BlobhiddenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlobhiddenServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.BlobhiddenDao")
    private WMGenericDao<Blobhidden, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Blobhidden, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public Blobhidden create(Blobhidden blobhidden) {
        LOGGER.debug("Creating a new Blobhidden with information: {}", blobhidden);

        Blobhidden blobhiddenCreated = this.wmGenericDao.create(blobhidden);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(blobhiddenCreated);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Blobhidden getById(Integer blobhiddenId) {
        LOGGER.debug("Finding Blobhidden by id: {}", blobhiddenId);
        return this.wmGenericDao.findById(blobhiddenId);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Blobhidden findById(Integer blobhiddenId) {
        LOGGER.debug("Finding Blobhidden by id: {}", blobhiddenId);
        try {
            return this.wmGenericDao.findById(blobhiddenId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Blobhidden found with id: {}", blobhiddenId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<Blobhidden> findByMultipleIds(List<Integer> blobhiddenIds, boolean orderedReturn) {
        LOGGER.debug("Finding Blobhiddens by ids: {}", blobhiddenIds);

        return this.wmGenericDao.findByMultipleIds(blobhiddenIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public Blobhidden update(Blobhidden blobhidden) {
        LOGGER.debug("Updating Blobhidden with information: {}", blobhidden);

        this.wmGenericDao.update(blobhidden);
        this.wmGenericDao.refresh(blobhidden);

        return blobhidden;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public Blobhidden delete(Integer blobhiddenId) {
        LOGGER.debug("Deleting Blobhidden with id: {}", blobhiddenId);
        Blobhidden deleted = this.wmGenericDao.findById(blobhiddenId);
        if (deleted == null) {
            LOGGER.debug("No Blobhidden found with id: {}", blobhiddenId);
            throw new EntityNotFoundException(String.valueOf(blobhiddenId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(Blobhidden blobhidden) {
        LOGGER.debug("Deleting Blobhidden with {}", blobhidden);
        this.wmGenericDao.delete(blobhidden);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Blobhidden> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Blobhiddens");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<Blobhidden> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Blobhiddens");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table Blobhidden to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table Blobhidden to {} format", options.getExportType());
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
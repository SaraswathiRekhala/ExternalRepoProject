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

import com.externalrepoproject.cascadedb.FilterTable;


/**
 * ServiceImpl object for domain model class FilterTable.
 *
 * @see FilterTable
 */
@Service("CascadeDB.FilterTableService")
@Validated
public class FilterTableServiceImpl implements FilterTableService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterTableServiceImpl.class);


    @Autowired
    @Qualifier("CascadeDB.FilterTableDao")
    private WMGenericDao<FilterTable, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FilterTable, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public FilterTable create(FilterTable filterTable) {
        LOGGER.debug("Creating a new FilterTable with information: {}", filterTable);

        FilterTable filterTableCreated = this.wmGenericDao.create(filterTable);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(filterTableCreated);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public FilterTable getById(Integer filtertableId) {
        LOGGER.debug("Finding FilterTable by id: {}", filtertableId);
        return this.wmGenericDao.findById(filtertableId);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public FilterTable findById(Integer filtertableId) {
        LOGGER.debug("Finding FilterTable by id: {}", filtertableId);
        try {
            return this.wmGenericDao.findById(filtertableId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No FilterTable found with id: {}", filtertableId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public List<FilterTable> findByMultipleIds(List<Integer> filtertableIds, boolean orderedReturn) {
        LOGGER.debug("Finding FilterTables by ids: {}", filtertableIds);

        return this.wmGenericDao.findByMultipleIds(filtertableIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "CascadeDBTransactionManager")
    @Override
    public FilterTable update(FilterTable filterTable) {
        LOGGER.debug("Updating FilterTable with information: {}", filterTable);

        this.wmGenericDao.update(filterTable);
        this.wmGenericDao.refresh(filterTable);

        return filterTable;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public FilterTable delete(Integer filtertableId) {
        LOGGER.debug("Deleting FilterTable with id: {}", filtertableId);
        FilterTable deleted = this.wmGenericDao.findById(filtertableId);
        if (deleted == null) {
            LOGGER.debug("No FilterTable found with id: {}", filtertableId);
            throw new EntityNotFoundException(String.valueOf(filtertableId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "CascadeDBTransactionManager")
    @Override
    public void delete(FilterTable filterTable) {
        LOGGER.debug("Deleting FilterTable with {}", filterTable);
        this.wmGenericDao.delete(filterTable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<FilterTable> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FilterTables");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager")
    @Override
    public Page<FilterTable> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FilterTables");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service CascadeDB for table FilterTable to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "CascadeDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service CascadeDB for table FilterTable to {} format", options.getExportType());
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
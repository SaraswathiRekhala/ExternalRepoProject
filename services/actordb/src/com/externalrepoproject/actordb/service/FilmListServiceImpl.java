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

import com.externalrepoproject.actordb.FilmList;
import com.externalrepoproject.actordb.FilmListId;


/**
 * ServiceImpl object for domain model class FilmList.
 *
 * @see FilmList
 */
@Service("actordb.FilmListService")
@Validated
public class FilmListServiceImpl implements FilmListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmListServiceImpl.class);


    @Autowired
    @Qualifier("actordb.FilmListDao")
    private WMGenericDao<FilmList, FilmListId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FilmList, FilmListId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public FilmList create(FilmList filmList) {
        LOGGER.debug("Creating a new FilmList with information: {}", filmList);

        FilmList filmListCreated = this.wmGenericDao.create(filmList);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(filmListCreated);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public FilmList getById(FilmListId filmlistId) {
        LOGGER.debug("Finding FilmList by id: {}", filmlistId);
        return this.wmGenericDao.findById(filmlistId);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public FilmList findById(FilmListId filmlistId) {
        LOGGER.debug("Finding FilmList by id: {}", filmlistId);
        try {
            return this.wmGenericDao.findById(filmlistId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No FilmList found with id: {}", filmlistId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public List<FilmList> findByMultipleIds(List<FilmListId> filmlistIds, boolean orderedReturn) {
        LOGGER.debug("Finding FilmLists by ids: {}", filmlistIds);

        return this.wmGenericDao.findByMultipleIds(filmlistIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "actordbTransactionManager")
    @Override
    public FilmList update(FilmList filmList) {
        LOGGER.debug("Updating FilmList with information: {}", filmList);

        this.wmGenericDao.update(filmList);
        this.wmGenericDao.refresh(filmList);

        return filmList;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public FilmList delete(FilmListId filmlistId) {
        LOGGER.debug("Deleting FilmList with id: {}", filmlistId);
        FilmList deleted = this.wmGenericDao.findById(filmlistId);
        if (deleted == null) {
            LOGGER.debug("No FilmList found with id: {}", filmlistId);
            throw new EntityNotFoundException(String.valueOf(filmlistId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public void delete(FilmList filmList) {
        LOGGER.debug("Deleting FilmList with {}", filmList);
        this.wmGenericDao.delete(filmList);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmList> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FilmLists");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmList> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FilmLists");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service actordb for table FilmList to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service actordb for table FilmList to {} format", options.getExportType());
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



}
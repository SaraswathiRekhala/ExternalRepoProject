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

import com.externalrepoproject.actordb.FilmActor;
import com.externalrepoproject.actordb.FilmActorId;


/**
 * ServiceImpl object for domain model class FilmActor.
 *
 * @see FilmActor
 */
@Service("actordb.FilmActorService")
@Validated
public class FilmActorServiceImpl implements FilmActorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmActorServiceImpl.class);


    @Autowired
    @Qualifier("actordb.FilmActorDao")
    private WMGenericDao<FilmActor, FilmActorId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FilmActor, FilmActorId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public FilmActor create(FilmActor filmActor) {
        LOGGER.debug("Creating a new FilmActor with information: {}", filmActor);

        FilmActor filmActorCreated = this.wmGenericDao.create(filmActor);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(filmActorCreated);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public FilmActor getById(FilmActorId filmactorId) {
        LOGGER.debug("Finding FilmActor by id: {}", filmactorId);
        return this.wmGenericDao.findById(filmactorId);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public FilmActor findById(FilmActorId filmactorId) {
        LOGGER.debug("Finding FilmActor by id: {}", filmactorId);
        try {
            return this.wmGenericDao.findById(filmactorId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No FilmActor found with id: {}", filmactorId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public List<FilmActor> findByMultipleIds(List<FilmActorId> filmactorIds, boolean orderedReturn) {
        LOGGER.debug("Finding FilmActors by ids: {}", filmactorIds);

        return this.wmGenericDao.findByMultipleIds(filmactorIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "actordbTransactionManager")
    @Override
    public FilmActor update(FilmActor filmActor) {
        LOGGER.debug("Updating FilmActor with information: {}", filmActor);

        this.wmGenericDao.update(filmActor);
        this.wmGenericDao.refresh(filmActor);

        return filmActor;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public FilmActor delete(FilmActorId filmactorId) {
        LOGGER.debug("Deleting FilmActor with id: {}", filmactorId);
        FilmActor deleted = this.wmGenericDao.findById(filmactorId);
        if (deleted == null) {
            LOGGER.debug("No FilmActor found with id: {}", filmactorId);
            throw new EntityNotFoundException(String.valueOf(filmactorId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public void delete(FilmActor filmActor) {
        LOGGER.debug("Deleting FilmActor with {}", filmActor);
        this.wmGenericDao.delete(filmActor);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmActor> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FilmActors");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmActor> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FilmActors");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service actordb for table FilmActor to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service actordb for table FilmActor to {} format", options.getExportType());
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
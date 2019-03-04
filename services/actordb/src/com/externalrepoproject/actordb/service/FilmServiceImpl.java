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

import com.externalrepoproject.actordb.Film;
import com.externalrepoproject.actordb.FilmActor;
import com.externalrepoproject.actordb.FilmCategory;
import com.externalrepoproject.actordb.Inventory;


/**
 * ServiceImpl object for domain model class Film.
 *
 * @see Film
 */
@Service("actordb.FilmService")
@Validated
public class FilmServiceImpl implements FilmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("actordb.FilmCategoryService")
    private FilmCategoryService filmCategoryService;

    @Lazy
    @Autowired
    @Qualifier("actordb.FilmActorService")
    private FilmActorService filmActorService;

    @Lazy
    @Autowired
    @Qualifier("actordb.InventoryService")
    private InventoryService inventoryService;

    @Autowired
    @Qualifier("actordb.FilmDao")
    private WMGenericDao<Film, Short> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Film, Short> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public Film create(Film film) {
        LOGGER.debug("Creating a new Film with information: {}", film);

        Film filmCreated = this.wmGenericDao.create(film);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(filmCreated);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Film getById(Short filmIdInstance) {
        LOGGER.debug("Finding Film by id: {}", filmIdInstance);
        return this.wmGenericDao.findById(filmIdInstance);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Film findById(Short filmIdInstance) {
        LOGGER.debug("Finding Film by id: {}", filmIdInstance);
        try {
            return this.wmGenericDao.findById(filmIdInstance);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Film found with id: {}", filmIdInstance, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public List<Film> findByMultipleIds(List<Short> filmIdInstances, boolean orderedReturn) {
        LOGGER.debug("Finding Films by ids: {}", filmIdInstances);

        return this.wmGenericDao.findByMultipleIds(filmIdInstances, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "actordbTransactionManager")
    @Override
    public Film update(Film film) {
        LOGGER.debug("Updating Film with information: {}", film);

        this.wmGenericDao.update(film);
        this.wmGenericDao.refresh(film);

        return film;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public Film delete(Short filmIdInstance) {
        LOGGER.debug("Deleting Film with id: {}", filmIdInstance);
        Film deleted = this.wmGenericDao.findById(filmIdInstance);
        if (deleted == null) {
            LOGGER.debug("No Film found with id: {}", filmIdInstance);
            throw new EntityNotFoundException(String.valueOf(filmIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public void delete(Film film) {
        LOGGER.debug("Deleting Film with {}", film);
        this.wmGenericDao.delete(film);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Film> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Films");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Film> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Films");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service actordb for table Film to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service actordb for table Film to {} format", options.getExportType());
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

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmActor> findAssociatedFilmActors(Short filmId, Pageable pageable) {
        LOGGER.debug("Fetching all associated filmActors");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("film.filmId = '" + filmId + "'");

        return filmActorService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmCategory> findAssociatedFilmCategories(Short filmId, Pageable pageable) {
        LOGGER.debug("Fetching all associated filmCategories");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("film.filmId = '" + filmId + "'");

        return filmCategoryService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Inventory> findAssociatedInventories(Short filmId, Pageable pageable) {
        LOGGER.debug("Fetching all associated inventories");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("film.filmId = '" + filmId + "'");

        return inventoryService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service FilmCategoryService instance
     */
    protected void setFilmCategoryService(FilmCategoryService service) {
        this.filmCategoryService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service FilmActorService instance
     */
    protected void setFilmActorService(FilmActorService service) {
        this.filmActorService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service InventoryService instance
     */
    protected void setInventoryService(InventoryService service) {
        this.inventoryService = service;
    }

}
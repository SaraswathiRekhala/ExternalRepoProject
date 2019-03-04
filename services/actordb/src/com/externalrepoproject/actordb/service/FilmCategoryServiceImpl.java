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

import com.externalrepoproject.actordb.FilmCategory;
import com.externalrepoproject.actordb.FilmCategoryId;


/**
 * ServiceImpl object for domain model class FilmCategory.
 *
 * @see FilmCategory
 */
@Service("actordb.FilmCategoryService")
@Validated
public class FilmCategoryServiceImpl implements FilmCategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmCategoryServiceImpl.class);


    @Autowired
    @Qualifier("actordb.FilmCategoryDao")
    private WMGenericDao<FilmCategory, FilmCategoryId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FilmCategory, FilmCategoryId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public FilmCategory create(FilmCategory filmCategory) {
        LOGGER.debug("Creating a new FilmCategory with information: {}", filmCategory);

        FilmCategory filmCategoryCreated = this.wmGenericDao.create(filmCategory);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(filmCategoryCreated);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public FilmCategory getById(FilmCategoryId filmcategoryId) {
        LOGGER.debug("Finding FilmCategory by id: {}", filmcategoryId);
        return this.wmGenericDao.findById(filmcategoryId);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public FilmCategory findById(FilmCategoryId filmcategoryId) {
        LOGGER.debug("Finding FilmCategory by id: {}", filmcategoryId);
        try {
            return this.wmGenericDao.findById(filmcategoryId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No FilmCategory found with id: {}", filmcategoryId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public List<FilmCategory> findByMultipleIds(List<FilmCategoryId> filmcategoryIds, boolean orderedReturn) {
        LOGGER.debug("Finding FilmCategories by ids: {}", filmcategoryIds);

        return this.wmGenericDao.findByMultipleIds(filmcategoryIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "actordbTransactionManager")
    @Override
    public FilmCategory update(FilmCategory filmCategory) {
        LOGGER.debug("Updating FilmCategory with information: {}", filmCategory);

        this.wmGenericDao.update(filmCategory);
        this.wmGenericDao.refresh(filmCategory);

        return filmCategory;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public FilmCategory delete(FilmCategoryId filmcategoryId) {
        LOGGER.debug("Deleting FilmCategory with id: {}", filmcategoryId);
        FilmCategory deleted = this.wmGenericDao.findById(filmcategoryId);
        if (deleted == null) {
            LOGGER.debug("No FilmCategory found with id: {}", filmcategoryId);
            throw new EntityNotFoundException(String.valueOf(filmcategoryId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public void delete(FilmCategory filmCategory) {
        LOGGER.debug("Deleting FilmCategory with {}", filmCategory);
        this.wmGenericDao.delete(filmCategory);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmCategory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FilmCategories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<FilmCategory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FilmCategories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service actordb for table FilmCategory to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service actordb for table FilmCategory to {} format", options.getExportType());
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
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

import com.externalrepoproject.actordb.Inventory;
import com.externalrepoproject.actordb.Rental;


/**
 * ServiceImpl object for domain model class Inventory.
 *
 * @see Inventory
 */
@Service("actordb.InventoryService")
@Validated
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("actordb.RentalService")
    private RentalService rentalService;

    @Autowired
    @Qualifier("actordb.InventoryDao")
    private WMGenericDao<Inventory, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Inventory, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public Inventory create(Inventory inventory) {
        LOGGER.debug("Creating a new Inventory with information: {}", inventory);

        Inventory inventoryCreated = this.wmGenericDao.create(inventory);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(inventoryCreated);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Inventory getById(Integer inventoryIdInstance) {
        LOGGER.debug("Finding Inventory by id: {}", inventoryIdInstance);
        return this.wmGenericDao.findById(inventoryIdInstance);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Inventory findById(Integer inventoryIdInstance) {
        LOGGER.debug("Finding Inventory by id: {}", inventoryIdInstance);
        try {
            return this.wmGenericDao.findById(inventoryIdInstance);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Inventory found with id: {}", inventoryIdInstance, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public List<Inventory> findByMultipleIds(List<Integer> inventoryIdInstances, boolean orderedReturn) {
        LOGGER.debug("Finding Inventories by ids: {}", inventoryIdInstances);

        return this.wmGenericDao.findByMultipleIds(inventoryIdInstances, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "actordbTransactionManager")
    @Override
    public Inventory update(Inventory inventory) {
        LOGGER.debug("Updating Inventory with information: {}", inventory);

        this.wmGenericDao.update(inventory);
        this.wmGenericDao.refresh(inventory);

        return inventory;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public Inventory delete(Integer inventoryIdInstance) {
        LOGGER.debug("Deleting Inventory with id: {}", inventoryIdInstance);
        Inventory deleted = this.wmGenericDao.findById(inventoryIdInstance);
        if (deleted == null) {
            LOGGER.debug("No Inventory found with id: {}", inventoryIdInstance);
            throw new EntityNotFoundException(String.valueOf(inventoryIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public void delete(Inventory inventory) {
        LOGGER.debug("Deleting Inventory with {}", inventory);
        this.wmGenericDao.delete(inventory);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Inventory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Inventories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Inventory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Inventories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service actordb for table Inventory to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service actordb for table Inventory to {} format", options.getExportType());
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
    public Page<Rental> findAssociatedRentals(Integer inventoryId, Pageable pageable) {
        LOGGER.debug("Fetching all associated rentals");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inventory.inventoryId = '" + inventoryId + "'");

        return rentalService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service RentalService instance
     */
    protected void setRentalService(RentalService service) {
        this.rentalService = service;
    }

}
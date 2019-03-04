/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.externalrepoproject.actordb.Customer;
import com.externalrepoproject.actordb.Inventory;
import com.externalrepoproject.actordb.Staff;
import com.externalrepoproject.actordb.Store;

/**
 * Service object for domain model class {@link Store}.
 */
public interface StoreService {

    /**
     * Creates a new Store. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Store if any.
     *
     * @param store Details of the Store to be created; value cannot be null.
     * @return The newly created Store.
     */
    Store create(@Valid Store store);


	/**
     * Returns Store by given id if exists.
     *
     * @param storeIdInstance The id of the Store to get; value cannot be null.
     * @return Store associated with the given storeIdInstance.
	 * @throws EntityNotFoundException If no Store is found.
     */
    Store getById(Short storeIdInstance);

    /**
     * Find and return the Store by given id if exists, returns null otherwise.
     *
     * @param storeIdInstance The id of the Store to get; value cannot be null.
     * @return Store associated with the given storeIdInstance.
     */
    Store findById(Short storeIdInstance);

	/**
     * Find and return the list of Stores by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param storeIdInstances The id's of the Store to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Stores associated with the given storeIdInstances.
     */
    List<Store> findByMultipleIds(List<Short> storeIdInstances, boolean orderedReturn);

    /**
     * Find and return the Store for given managerStaffId  if exists.
     *
     * @param managerStaffId value of managerStaffId; value cannot be null.
     * @return Store associated with the given inputs.
     * @throws EntityNotFoundException if no matching Store found.
     */
    Store getByManagerStaffId(short managerStaffId);

    /**
     * Updates the details of an existing Store. It replaces all fields of the existing Store with the given store.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Store if any.
     *
     * @param store The details of the Store to be updated; value cannot be null.
     * @return The updated Store.
     * @throws EntityNotFoundException if no Store is found with given input.
     */
    Store update(@Valid Store store);

    /**
     * Deletes an existing Store with the given id.
     *
     * @param storeIdInstance The id of the Store to be deleted; value cannot be null.
     * @return The deleted Store.
     * @throws EntityNotFoundException if no Store found with the given id.
     */
    Store delete(Short storeIdInstance);

    /**
     * Deletes an existing Store with the given object.
     *
     * @param store The instance of the Store to be deleted; value cannot be null.
     */
    void delete(Store store);

    /**
     * Find all Stores matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Stores.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Store> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Stores matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Stores.
     *
     * @see Pageable
     * @see Page
     */
    Page<Store> findAll(String query, Pageable pageable);

    /**
     * Exports all Stores matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all Stores matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the Stores in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Store.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated customers for given Store id.
     *
     * @param storeId value of storeId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Customer instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Customer> findAssociatedCustomers(Short storeId, Pageable pageable);

    /*
     * Returns the associated inventories for given Store id.
     *
     * @param storeId value of storeId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Inventory instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Inventory> findAssociatedInventories(Short storeId, Pageable pageable);

    /*
     * Returns the associated staffsForStoreId for given Store id.
     *
     * @param storeId value of storeId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Staff instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Staff> findAssociatedStaffsForStoreId(Short storeId, Pageable pageable);

}
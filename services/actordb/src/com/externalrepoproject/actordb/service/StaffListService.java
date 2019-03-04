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

import com.externalrepoproject.actordb.StaffList;
import com.externalrepoproject.actordb.StaffListId;

/**
 * Service object for domain model class {@link StaffList}.
 */
public interface StaffListService {

    /**
     * Creates a new StaffList. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on StaffList if any.
     *
     * @param staffList Details of the StaffList to be created; value cannot be null.
     * @return The newly created StaffList.
     */
    StaffList create(@Valid StaffList staffList);


	/**
     * Returns StaffList by given id if exists.
     *
     * @param stafflistId The id of the StaffList to get; value cannot be null.
     * @return StaffList associated with the given stafflistId.
	 * @throws EntityNotFoundException If no StaffList is found.
     */
    StaffList getById(StaffListId stafflistId);

    /**
     * Find and return the StaffList by given id if exists, returns null otherwise.
     *
     * @param stafflistId The id of the StaffList to get; value cannot be null.
     * @return StaffList associated with the given stafflistId.
     */
    StaffList findById(StaffListId stafflistId);

	/**
     * Find and return the list of StaffLists by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param stafflistIds The id's of the StaffList to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return StaffLists associated with the given stafflistIds.
     */
    List<StaffList> findByMultipleIds(List<StaffListId> stafflistIds, boolean orderedReturn);


    /**
     * Updates the details of an existing StaffList. It replaces all fields of the existing StaffList with the given staffList.
     *
     * This method overrides the input field values using Server side or database managed properties defined on StaffList if any.
     *
     * @param staffList The details of the StaffList to be updated; value cannot be null.
     * @return The updated StaffList.
     * @throws EntityNotFoundException if no StaffList is found with given input.
     */
    StaffList update(@Valid StaffList staffList);

    /**
     * Deletes an existing StaffList with the given id.
     *
     * @param stafflistId The id of the StaffList to be deleted; value cannot be null.
     * @return The deleted StaffList.
     * @throws EntityNotFoundException if no StaffList found with the given id.
     */
    StaffList delete(StaffListId stafflistId);

    /**
     * Deletes an existing StaffList with the given object.
     *
     * @param staffList The instance of the StaffList to be deleted; value cannot be null.
     */
    void delete(StaffList staffList);

    /**
     * Find all StaffLists matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching StaffLists.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<StaffList> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all StaffLists matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching StaffLists.
     *
     * @see Pageable
     * @see Page
     */
    Page<StaffList> findAll(String query, Pageable pageable);

    /**
     * Exports all StaffLists matching the given input query to the given exportType format.
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
     * Exports all StaffLists matching the given input query to the given exportType format.
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
     * Retrieve the count of the StaffLists in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the StaffList.
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


}
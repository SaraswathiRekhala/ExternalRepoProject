/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.cascadedb.service;

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

import com.externalrepoproject.cascadedb.One2oneUcC;

/**
 * Service object for domain model class {@link One2oneUcC}.
 */
public interface One2oneUcCService {

    /**
     * Creates a new One2oneUcC. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2oneUcC if any.
     *
     * @param one2oneUcC Details of the One2oneUcC to be created; value cannot be null.
     * @return The newly created One2oneUcC.
     */
    One2oneUcC create(@Valid One2oneUcC one2oneUcC);


	/**
     * Returns One2oneUcC by given id if exists.
     *
     * @param one2oneuccId The id of the One2oneUcC to get; value cannot be null.
     * @return One2oneUcC associated with the given one2oneuccId.
	 * @throws EntityNotFoundException If no One2oneUcC is found.
     */
    One2oneUcC getById(Integer one2oneuccId);

    /**
     * Find and return the One2oneUcC by given id if exists, returns null otherwise.
     *
     * @param one2oneuccId The id of the One2oneUcC to get; value cannot be null.
     * @return One2oneUcC associated with the given one2oneuccId.
     */
    One2oneUcC findById(Integer one2oneuccId);

	/**
     * Find and return the list of One2oneUcCs by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param one2oneuccIds The id's of the One2oneUcC to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return One2oneUcCs associated with the given one2oneuccIds.
     */
    List<One2oneUcC> findByMultipleIds(List<Integer> one2oneuccIds, boolean orderedReturn);

    /**
     * Find and return the One2oneUcC for given childColumn1  if exists.
     *
     * @param childColumn1 value of childColumn1; value cannot be null.
     * @return One2oneUcC associated with the given inputs.
     * @throws EntityNotFoundException if no matching One2oneUcC found.
     */
    One2oneUcC getByChildColumn1(Integer childColumn1);

    /**
     * Updates the details of an existing One2oneUcC. It replaces all fields of the existing One2oneUcC with the given one2oneUcC.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2oneUcC if any.
     *
     * @param one2oneUcC The details of the One2oneUcC to be updated; value cannot be null.
     * @return The updated One2oneUcC.
     * @throws EntityNotFoundException if no One2oneUcC is found with given input.
     */
    One2oneUcC update(@Valid One2oneUcC one2oneUcC);

    /**
     * Deletes an existing One2oneUcC with the given id.
     *
     * @param one2oneuccId The id of the One2oneUcC to be deleted; value cannot be null.
     * @return The deleted One2oneUcC.
     * @throws EntityNotFoundException if no One2oneUcC found with the given id.
     */
    One2oneUcC delete(Integer one2oneuccId);

    /**
     * Deletes an existing One2oneUcC with the given object.
     *
     * @param one2oneUcC The instance of the One2oneUcC to be deleted; value cannot be null.
     */
    void delete(One2oneUcC one2oneUcC);

    /**
     * Find all One2oneUcCs matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2oneUcCs.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<One2oneUcC> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all One2oneUcCs matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2oneUcCs.
     *
     * @see Pageable
     * @see Page
     */
    Page<One2oneUcC> findAll(String query, Pageable pageable);

    /**
     * Exports all One2oneUcCs matching the given input query to the given exportType format.
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
     * Exports all One2oneUcCs matching the given input query to the given exportType format.
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
     * Retrieve the count of the One2oneUcCs in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the One2oneUcC.
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
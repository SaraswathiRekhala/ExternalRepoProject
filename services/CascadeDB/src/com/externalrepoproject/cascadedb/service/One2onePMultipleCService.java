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

import com.externalrepoproject.cascadedb.One2onePMultipleC;

/**
 * Service object for domain model class {@link One2onePMultipleC}.
 */
public interface One2onePMultipleCService {

    /**
     * Creates a new One2onePMultipleC. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2onePMultipleC if any.
     *
     * @param one2onePmultipleC Details of the One2onePMultipleC to be created; value cannot be null.
     * @return The newly created One2onePMultipleC.
     */
    One2onePMultipleC create(@Valid One2onePMultipleC one2onePmultipleC);


	/**
     * Returns One2onePMultipleC by given id if exists.
     *
     * @param one2onepmultiplecId The id of the One2onePMultipleC to get; value cannot be null.
     * @return One2onePMultipleC associated with the given one2onepmultiplecId.
	 * @throws EntityNotFoundException If no One2onePMultipleC is found.
     */
    One2onePMultipleC getById(Integer one2onepmultiplecId);

    /**
     * Find and return the One2onePMultipleC by given id if exists, returns null otherwise.
     *
     * @param one2onepmultiplecId The id of the One2onePMultipleC to get; value cannot be null.
     * @return One2onePMultipleC associated with the given one2onepmultiplecId.
     */
    One2onePMultipleC findById(Integer one2onepmultiplecId);

	/**
     * Find and return the list of One2onePMultipleCs by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param one2onepmultiplecIds The id's of the One2onePMultipleC to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return One2onePMultipleCs associated with the given one2onepmultiplecIds.
     */
    List<One2onePMultipleC> findByMultipleIds(List<Integer> one2onepmultiplecIds, boolean orderedReturn);


    /**
     * Updates the details of an existing One2onePMultipleC. It replaces all fields of the existing One2onePMultipleC with the given one2onePmultipleC.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2onePMultipleC if any.
     *
     * @param one2onePmultipleC The details of the One2onePMultipleC to be updated; value cannot be null.
     * @return The updated One2onePMultipleC.
     * @throws EntityNotFoundException if no One2onePMultipleC is found with given input.
     */
    One2onePMultipleC update(@Valid One2onePMultipleC one2onePmultipleC);

    /**
     * Deletes an existing One2onePMultipleC with the given id.
     *
     * @param one2onepmultiplecId The id of the One2onePMultipleC to be deleted; value cannot be null.
     * @return The deleted One2onePMultipleC.
     * @throws EntityNotFoundException if no One2onePMultipleC found with the given id.
     */
    One2onePMultipleC delete(Integer one2onepmultiplecId);

    /**
     * Deletes an existing One2onePMultipleC with the given object.
     *
     * @param one2onePmultipleC The instance of the One2onePMultipleC to be deleted; value cannot be null.
     */
    void delete(One2onePMultipleC one2onePmultipleC);

    /**
     * Find all One2onePMultipleCs matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2onePMultipleCs.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<One2onePMultipleC> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all One2onePMultipleCs matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2onePMultipleCs.
     *
     * @see Pageable
     * @see Page
     */
    Page<One2onePMultipleC> findAll(String query, Pageable pageable);

    /**
     * Exports all One2onePMultipleCs matching the given input query to the given exportType format.
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
     * Exports all One2onePMultipleCs matching the given input query to the given exportType format.
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
     * Retrieve the count of the One2onePMultipleCs in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the One2onePMultipleC.
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
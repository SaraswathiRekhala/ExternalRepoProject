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

import com.externalrepoproject.cascadedb.One2oneucPNotnull;

/**
 * Service object for domain model class {@link One2oneucPNotnull}.
 */
public interface One2oneucPNotnullService {

    /**
     * Creates a new One2oneucPNotnull. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2oneucPNotnull if any.
     *
     * @param one2oneucPnotnull Details of the One2oneucPNotnull to be created; value cannot be null.
     * @return The newly created One2oneucPNotnull.
     */
    One2oneucPNotnull create(@Valid One2oneucPNotnull one2oneucPnotnull);


	/**
     * Returns One2oneucPNotnull by given id if exists.
     *
     * @param one2oneucpnotnullId The id of the One2oneucPNotnull to get; value cannot be null.
     * @return One2oneucPNotnull associated with the given one2oneucpnotnullId.
	 * @throws EntityNotFoundException If no One2oneucPNotnull is found.
     */
    One2oneucPNotnull getById(Integer one2oneucpnotnullId);

    /**
     * Find and return the One2oneucPNotnull by given id if exists, returns null otherwise.
     *
     * @param one2oneucpnotnullId The id of the One2oneucPNotnull to get; value cannot be null.
     * @return One2oneucPNotnull associated with the given one2oneucpnotnullId.
     */
    One2oneucPNotnull findById(Integer one2oneucpnotnullId);

	/**
     * Find and return the list of One2oneucPNotnulls by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param one2oneucpnotnullIds The id's of the One2oneucPNotnull to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return One2oneucPNotnulls associated with the given one2oneucpnotnullIds.
     */
    List<One2oneucPNotnull> findByMultipleIds(List<Integer> one2oneucpnotnullIds, boolean orderedReturn);


    /**
     * Updates the details of an existing One2oneucPNotnull. It replaces all fields of the existing One2oneucPNotnull with the given one2oneucPnotnull.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2oneucPNotnull if any.
     *
     * @param one2oneucPnotnull The details of the One2oneucPNotnull to be updated; value cannot be null.
     * @return The updated One2oneucPNotnull.
     * @throws EntityNotFoundException if no One2oneucPNotnull is found with given input.
     */
    One2oneucPNotnull update(@Valid One2oneucPNotnull one2oneucPnotnull);

    /**
     * Deletes an existing One2oneucPNotnull with the given id.
     *
     * @param one2oneucpnotnullId The id of the One2oneucPNotnull to be deleted; value cannot be null.
     * @return The deleted One2oneucPNotnull.
     * @throws EntityNotFoundException if no One2oneucPNotnull found with the given id.
     */
    One2oneucPNotnull delete(Integer one2oneucpnotnullId);

    /**
     * Deletes an existing One2oneucPNotnull with the given object.
     *
     * @param one2oneucPnotnull The instance of the One2oneucPNotnull to be deleted; value cannot be null.
     */
    void delete(One2oneucPNotnull one2oneucPnotnull);

    /**
     * Find all One2oneucPNotnulls matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2oneucPNotnulls.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<One2oneucPNotnull> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all One2oneucPNotnulls matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2oneucPNotnulls.
     *
     * @see Pageable
     * @see Page
     */
    Page<One2oneucPNotnull> findAll(String query, Pageable pageable);

    /**
     * Exports all One2oneucPNotnulls matching the given input query to the given exportType format.
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
     * Exports all One2oneucPNotnulls matching the given input query to the given exportType format.
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
     * Retrieve the count of the One2oneucPNotnulls in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the One2oneucPNotnull.
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
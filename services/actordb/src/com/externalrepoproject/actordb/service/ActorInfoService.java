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

import com.externalrepoproject.actordb.ActorInfo;
import com.externalrepoproject.actordb.ActorInfoId;

/**
 * Service object for domain model class {@link ActorInfo}.
 */
public interface ActorInfoService {

    /**
     * Creates a new ActorInfo. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ActorInfo if any.
     *
     * @param actorInfo Details of the ActorInfo to be created; value cannot be null.
     * @return The newly created ActorInfo.
     */
    ActorInfo create(@Valid ActorInfo actorInfo);


	/**
     * Returns ActorInfo by given id if exists.
     *
     * @param actorinfoId The id of the ActorInfo to get; value cannot be null.
     * @return ActorInfo associated with the given actorinfoId.
	 * @throws EntityNotFoundException If no ActorInfo is found.
     */
    ActorInfo getById(ActorInfoId actorinfoId);

    /**
     * Find and return the ActorInfo by given id if exists, returns null otherwise.
     *
     * @param actorinfoId The id of the ActorInfo to get; value cannot be null.
     * @return ActorInfo associated with the given actorinfoId.
     */
    ActorInfo findById(ActorInfoId actorinfoId);

	/**
     * Find and return the list of ActorInfos by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param actorinfoIds The id's of the ActorInfo to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return ActorInfos associated with the given actorinfoIds.
     */
    List<ActorInfo> findByMultipleIds(List<ActorInfoId> actorinfoIds, boolean orderedReturn);


    /**
     * Updates the details of an existing ActorInfo. It replaces all fields of the existing ActorInfo with the given actorInfo.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ActorInfo if any.
     *
     * @param actorInfo The details of the ActorInfo to be updated; value cannot be null.
     * @return The updated ActorInfo.
     * @throws EntityNotFoundException if no ActorInfo is found with given input.
     */
    ActorInfo update(@Valid ActorInfo actorInfo);

    /**
     * Deletes an existing ActorInfo with the given id.
     *
     * @param actorinfoId The id of the ActorInfo to be deleted; value cannot be null.
     * @return The deleted ActorInfo.
     * @throws EntityNotFoundException if no ActorInfo found with the given id.
     */
    ActorInfo delete(ActorInfoId actorinfoId);

    /**
     * Deletes an existing ActorInfo with the given object.
     *
     * @param actorInfo The instance of the ActorInfo to be deleted; value cannot be null.
     */
    void delete(ActorInfo actorInfo);

    /**
     * Find all ActorInfos matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ActorInfos.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<ActorInfo> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all ActorInfos matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ActorInfos.
     *
     * @see Pageable
     * @see Page
     */
    Page<ActorInfo> findAll(String query, Pageable pageable);

    /**
     * Exports all ActorInfos matching the given input query to the given exportType format.
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
     * Exports all ActorInfos matching the given input query to the given exportType format.
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
     * Retrieve the count of the ActorInfos in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the ActorInfo.
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
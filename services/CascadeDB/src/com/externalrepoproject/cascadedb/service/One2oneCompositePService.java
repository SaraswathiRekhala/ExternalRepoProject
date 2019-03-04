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

import com.externalrepoproject.cascadedb.One2oneCompositeP;
import com.externalrepoproject.cascadedb.One2oneCompositePId;

/**
 * Service object for domain model class {@link One2oneCompositeP}.
 */
public interface One2oneCompositePService {

    /**
     * Creates a new One2oneCompositeP. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2oneCompositeP if any.
     *
     * @param one2oneCompositeP Details of the One2oneCompositeP to be created; value cannot be null.
     * @return The newly created One2oneCompositeP.
     */
    One2oneCompositeP create(@Valid One2oneCompositeP one2oneCompositeP);


	/**
     * Returns One2oneCompositeP by given id if exists.
     *
     * @param one2onecompositepId The id of the One2oneCompositeP to get; value cannot be null.
     * @return One2oneCompositeP associated with the given one2onecompositepId.
	 * @throws EntityNotFoundException If no One2oneCompositeP is found.
     */
    One2oneCompositeP getById(One2oneCompositePId one2onecompositepId);

    /**
     * Find and return the One2oneCompositeP by given id if exists, returns null otherwise.
     *
     * @param one2onecompositepId The id of the One2oneCompositeP to get; value cannot be null.
     * @return One2oneCompositeP associated with the given one2onecompositepId.
     */
    One2oneCompositeP findById(One2oneCompositePId one2onecompositepId);

	/**
     * Find and return the list of One2oneCompositePs by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param one2onecompositepIds The id's of the One2oneCompositeP to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return One2oneCompositePs associated with the given one2onecompositepIds.
     */
    List<One2oneCompositeP> findByMultipleIds(List<One2oneCompositePId> one2onecompositepIds, boolean orderedReturn);


    /**
     * Updates the details of an existing One2oneCompositeP. It replaces all fields of the existing One2oneCompositeP with the given one2oneCompositeP.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2oneCompositeP if any.
     *
     * @param one2oneCompositeP The details of the One2oneCompositeP to be updated; value cannot be null.
     * @return The updated One2oneCompositeP.
     * @throws EntityNotFoundException if no One2oneCompositeP is found with given input.
     */
    One2oneCompositeP update(@Valid One2oneCompositeP one2oneCompositeP);

    /**
     * Deletes an existing One2oneCompositeP with the given id.
     *
     * @param one2onecompositepId The id of the One2oneCompositeP to be deleted; value cannot be null.
     * @return The deleted One2oneCompositeP.
     * @throws EntityNotFoundException if no One2oneCompositeP found with the given id.
     */
    One2oneCompositeP delete(One2oneCompositePId one2onecompositepId);

    /**
     * Deletes an existing One2oneCompositeP with the given object.
     *
     * @param one2oneCompositeP The instance of the One2oneCompositeP to be deleted; value cannot be null.
     */
    void delete(One2oneCompositeP one2oneCompositeP);

    /**
     * Find all One2oneCompositePs matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2oneCompositePs.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<One2oneCompositeP> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all One2oneCompositePs matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2oneCompositePs.
     *
     * @see Pageable
     * @see Page
     */
    Page<One2oneCompositeP> findAll(String query, Pageable pageable);

    /**
     * Exports all One2oneCompositePs matching the given input query to the given exportType format.
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
     * Exports all One2oneCompositePs matching the given input query to the given exportType format.
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
     * Retrieve the count of the One2oneCompositePs in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the One2oneCompositeP.
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
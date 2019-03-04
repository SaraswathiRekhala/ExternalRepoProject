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

import com.externalrepoproject.cascadedb.Many2oneCompositeC1;
import com.externalrepoproject.cascadedb.One2manyCompositeP1;
import com.externalrepoproject.cascadedb.One2manyCompositeP1Id;

/**
 * Service object for domain model class {@link One2manyCompositeP1}.
 */
public interface One2manyCompositeP1Service {

    /**
     * Creates a new One2manyCompositeP1. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2manyCompositeP1 if any.
     *
     * @param one2manyCompositeP1 Details of the One2manyCompositeP1 to be created; value cannot be null.
     * @return The newly created One2manyCompositeP1.
     */
    One2manyCompositeP1 create(@Valid One2manyCompositeP1 one2manyCompositeP1);


	/**
     * Returns One2manyCompositeP1 by given id if exists.
     *
     * @param one2manycompositep1Id The id of the One2manyCompositeP1 to get; value cannot be null.
     * @return One2manyCompositeP1 associated with the given one2manycompositep1Id.
	 * @throws EntityNotFoundException If no One2manyCompositeP1 is found.
     */
    One2manyCompositeP1 getById(One2manyCompositeP1Id one2manycompositep1Id);

    /**
     * Find and return the One2manyCompositeP1 by given id if exists, returns null otherwise.
     *
     * @param one2manycompositep1Id The id of the One2manyCompositeP1 to get; value cannot be null.
     * @return One2manyCompositeP1 associated with the given one2manycompositep1Id.
     */
    One2manyCompositeP1 findById(One2manyCompositeP1Id one2manycompositep1Id);

	/**
     * Find and return the list of One2manyCompositeP1s by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param one2manycompositep1Ids The id's of the One2manyCompositeP1 to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return One2manyCompositeP1s associated with the given one2manycompositep1Ids.
     */
    List<One2manyCompositeP1> findByMultipleIds(List<One2manyCompositeP1Id> one2manycompositep1Ids, boolean orderedReturn);


    /**
     * Updates the details of an existing One2manyCompositeP1. It replaces all fields of the existing One2manyCompositeP1 with the given one2manyCompositeP1.
     *
     * This method overrides the input field values using Server side or database managed properties defined on One2manyCompositeP1 if any.
     *
     * @param one2manyCompositeP1 The details of the One2manyCompositeP1 to be updated; value cannot be null.
     * @return The updated One2manyCompositeP1.
     * @throws EntityNotFoundException if no One2manyCompositeP1 is found with given input.
     */
    One2manyCompositeP1 update(@Valid One2manyCompositeP1 one2manyCompositeP1);

    /**
     * Deletes an existing One2manyCompositeP1 with the given id.
     *
     * @param one2manycompositep1Id The id of the One2manyCompositeP1 to be deleted; value cannot be null.
     * @return The deleted One2manyCompositeP1.
     * @throws EntityNotFoundException if no One2manyCompositeP1 found with the given id.
     */
    One2manyCompositeP1 delete(One2manyCompositeP1Id one2manycompositep1Id);

    /**
     * Deletes an existing One2manyCompositeP1 with the given object.
     *
     * @param one2manyCompositeP1 The instance of the One2manyCompositeP1 to be deleted; value cannot be null.
     */
    void delete(One2manyCompositeP1 one2manyCompositeP1);

    /**
     * Find all One2manyCompositeP1s matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2manyCompositeP1s.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<One2manyCompositeP1> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all One2manyCompositeP1s matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching One2manyCompositeP1s.
     *
     * @see Pageable
     * @see Page
     */
    Page<One2manyCompositeP1> findAll(String query, Pageable pageable);

    /**
     * Exports all One2manyCompositeP1s matching the given input query to the given exportType format.
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
     * Exports all One2manyCompositeP1s matching the given input query to the given exportType format.
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
     * Retrieve the count of the One2manyCompositeP1s in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the One2manyCompositeP1.
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
     * Returns the associated many2oneCompositeC1s for given One2manyCompositeP1 id.
     *
     * @param parentStringId value of parentStringId; value cannot be null
     * @param parentIntId value of parentIntId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Many2oneCompositeC1 instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Many2oneCompositeC1> findAssociatedMany2oneCompositeC1s(String parentStringId, Integer parentIntId, Pageable pageable);

}
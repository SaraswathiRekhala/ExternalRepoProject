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

import com.externalrepoproject.cascadedb.Service;

/**
 * Service object for domain model class {@link Service}.
 */
public interface ServiceService {

    /**
     * Creates a new Service. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Service if any.
     *
     * @param service Details of the Service to be created; value cannot be null.
     * @return The newly created Service.
     */
    Service create(@Valid Service service);


	/**
     * Returns Service by given id if exists.
     *
     * @param serviceIdInstance The id of the Service to get; value cannot be null.
     * @return Service associated with the given serviceIdInstance.
	 * @throws EntityNotFoundException If no Service is found.
     */
    Service getById(Integer serviceIdInstance);

    /**
     * Find and return the Service by given id if exists, returns null otherwise.
     *
     * @param serviceIdInstance The id of the Service to get; value cannot be null.
     * @return Service associated with the given serviceIdInstance.
     */
    Service findById(Integer serviceIdInstance);

	/**
     * Find and return the list of Services by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param serviceIdInstances The id's of the Service to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Services associated with the given serviceIdInstances.
     */
    List<Service> findByMultipleIds(List<Integer> serviceIdInstances, boolean orderedReturn);


    /**
     * Updates the details of an existing Service. It replaces all fields of the existing Service with the given service.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Service if any.
     *
     * @param service The details of the Service to be updated; value cannot be null.
     * @return The updated Service.
     * @throws EntityNotFoundException if no Service is found with given input.
     */
    Service update(@Valid Service service);

    /**
     * Deletes an existing Service with the given id.
     *
     * @param serviceIdInstance The id of the Service to be deleted; value cannot be null.
     * @return The deleted Service.
     * @throws EntityNotFoundException if no Service found with the given id.
     */
    Service delete(Integer serviceIdInstance);

    /**
     * Deletes an existing Service with the given object.
     *
     * @param service The instance of the Service to be deleted; value cannot be null.
     */
    void delete(Service service);

    /**
     * Find all Services matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Services.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Service> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Services matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Services.
     *
     * @see Pageable
     * @see Page
     */
    Page<Service> findAll(String query, Pageable pageable);

    /**
     * Exports all Services matching the given input query to the given exportType format.
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
     * Exports all Services matching the given input query to the given exportType format.
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
     * Retrieve the count of the Services in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Service.
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
     * Returns the associated servicesForColumn4 for given Service id.
     *
     * @param serviceId value of serviceId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Service instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Service> findAssociatedServicesForColumn4(Integer serviceId, Pageable pageable);

}
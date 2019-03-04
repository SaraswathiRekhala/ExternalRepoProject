/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.cascadedb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.externalrepoproject.cascadedb.Service;
import com.externalrepoproject.cascadedb.ServiceRef;
import com.externalrepoproject.cascadedb.service.ServiceRefService;


/**
 * Controller object for domain model class ServiceRef.
 * @see ServiceRef
 */
@RestController("CascadeDB.ServiceRefController")
@Api(value = "ServiceRefController", description = "Exposes APIs to work with ServiceRef resource.")
@RequestMapping("/CascadeDB/ServiceRef")
public class ServiceRefController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRefController.class);

    @Autowired
	@Qualifier("CascadeDB.ServiceRefService")
	private ServiceRefService serviceRefService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new ServiceRef instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ServiceRef createServiceRef(@RequestBody ServiceRef serviceRef) {
		LOGGER.debug("Create ServiceRef with information: {}" , serviceRef);

		serviceRef = serviceRefService.create(serviceRef);
		LOGGER.debug("Created ServiceRef with information: {}" , serviceRef);

	    return serviceRef;
	}

    @ApiOperation(value = "Returns the ServiceRef instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ServiceRef getServiceRef(@PathVariable("id") String id) {
        LOGGER.debug("Getting ServiceRef with id: {}" , id);

        ServiceRef foundServiceRef = serviceRefService.getById(id);
        LOGGER.debug("ServiceRef details with id: {}" , foundServiceRef);

        return foundServiceRef;
    }

    @ApiOperation(value = "Updates the ServiceRef instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ServiceRef editServiceRef(@PathVariable("id") String id, @RequestBody ServiceRef serviceRef) {
        LOGGER.debug("Editing ServiceRef with id: {}" , serviceRef.getId());

        serviceRef.setId(id);
        serviceRef = serviceRefService.update(serviceRef);
        LOGGER.debug("ServiceRef details with id: {}" , serviceRef);

        return serviceRef;
    }

    @ApiOperation(value = "Deletes the ServiceRef instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteServiceRef(@PathVariable("id") String id) {
        LOGGER.debug("Deleting ServiceRef with id: {}" , id);

        ServiceRef deletedServiceRef = serviceRefService.delete(id);

        return deletedServiceRef != null;
    }

    /**
     * @deprecated Use {@link #findServiceRefs(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of ServiceRef instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ServiceRef> searchServiceRefsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering ServiceRefs list by query filter:{}", (Object) queryFilters);
        return serviceRefService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of ServiceRef instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ServiceRef> findServiceRefs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering ServiceRefs list by filter:", query);
        return serviceRefService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of ServiceRef instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ServiceRef> filterServiceRefs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering ServiceRefs list by filter", query);
        return serviceRefService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportServiceRefs(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return serviceRefService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportServiceRefsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = ServiceRef.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> serviceRefService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of ServiceRef instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countServiceRefs( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting ServiceRefs");
		return serviceRefService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getServiceRefAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return serviceRefService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/services", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the services instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Service> findAssociatedServices(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated services");
        return serviceRefService.findAssociatedServices(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ServiceRefService instance
	 */
	protected void setServiceRefService(ServiceRefService service) {
		this.serviceRefService = service;
	}

}
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

import com.externalrepoproject.cascadedb.Many2oneC1;
import com.externalrepoproject.cascadedb.service.Many2oneC1Service;


/**
 * Controller object for domain model class Many2oneC1.
 * @see Many2oneC1
 */
@RestController("CascadeDB.Many2oneC1Controller")
@Api(value = "Many2oneC1Controller", description = "Exposes APIs to work with Many2oneC1 resource.")
@RequestMapping("/CascadeDB/Many2oneC1")
public class Many2oneC1Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Many2oneC1Controller.class);

    @Autowired
	@Qualifier("CascadeDB.Many2oneC1Service")
	private Many2oneC1Service many2oneC1Service;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Many2oneC1 instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Many2oneC1 createMany2oneC1(@RequestBody Many2oneC1 many2oneC1) {
		LOGGER.debug("Create Many2oneC1 with information: {}" , many2oneC1);

		many2oneC1 = many2oneC1Service.create(many2oneC1);
		LOGGER.debug("Created Many2oneC1 with information: {}" , many2oneC1);

	    return many2oneC1;
	}

    @ApiOperation(value = "Returns the Many2oneC1 instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Many2oneC1 getMany2oneC1(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting Many2oneC1 with id: {}" , id);

        Many2oneC1 foundMany2oneC1 = many2oneC1Service.getById(id);
        LOGGER.debug("Many2oneC1 details with id: {}" , foundMany2oneC1);

        return foundMany2oneC1;
    }

    @ApiOperation(value = "Updates the Many2oneC1 instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Many2oneC1 editMany2oneC1(@PathVariable("id") Integer id, @RequestBody Many2oneC1 many2oneC1) {
        LOGGER.debug("Editing Many2oneC1 with id: {}" , many2oneC1.getChild1Id());

        many2oneC1.setChild1Id(id);
        many2oneC1 = many2oneC1Service.update(many2oneC1);
        LOGGER.debug("Many2oneC1 details with id: {}" , many2oneC1);

        return many2oneC1;
    }

    @ApiOperation(value = "Deletes the Many2oneC1 instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMany2oneC1(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting Many2oneC1 with id: {}" , id);

        Many2oneC1 deletedMany2oneC1 = many2oneC1Service.delete(id);

        return deletedMany2oneC1 != null;
    }

    /**
     * @deprecated Use {@link #findMany2oneC1s(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Many2oneC1 instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Many2oneC1> searchMany2oneC1sByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Many2oneC1s list by query filter:{}", (Object) queryFilters);
        return many2oneC1Service.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Many2oneC1 instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Many2oneC1> findMany2oneC1s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Many2oneC1s list by filter:", query);
        return many2oneC1Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Many2oneC1 instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Many2oneC1> filterMany2oneC1s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Many2oneC1s list by filter", query);
        return many2oneC1Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMany2oneC1s(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return many2oneC1Service.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportMany2oneC1sAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Many2oneC1.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> many2oneC1Service.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Many2oneC1 instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countMany2oneC1s( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Many2oneC1s");
		return many2oneC1Service.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getMany2oneC1AggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return many2oneC1Service.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Many2oneC1Service instance
	 */
	protected void setMany2oneC1Service(Many2oneC1Service service) {
		this.many2oneC1Service = service;
	}

}
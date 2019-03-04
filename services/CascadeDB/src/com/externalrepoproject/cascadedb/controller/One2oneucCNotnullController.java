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

import com.externalrepoproject.cascadedb.One2oneucCNotnull;
import com.externalrepoproject.cascadedb.service.One2oneucCNotnullService;


/**
 * Controller object for domain model class One2oneucCNotnull.
 * @see One2oneucCNotnull
 */
@RestController("CascadeDB.One2oneucCNotnullController")
@Api(value = "One2oneucCNotnullController", description = "Exposes APIs to work with One2oneucCNotnull resource.")
@RequestMapping("/CascadeDB/One2oneucCNotnull")
public class One2oneucCNotnullController {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2oneucCNotnullController.class);

    @Autowired
	@Qualifier("CascadeDB.One2oneucCNotnullService")
	private One2oneucCNotnullService one2oneucCNotnullService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new One2oneucCNotnull instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneucCNotnull createOne2oneucCNotnull(@RequestBody One2oneucCNotnull one2oneucCnotnull) {
		LOGGER.debug("Create One2oneucCNotnull with information: {}" , one2oneucCnotnull);

		one2oneucCnotnull = one2oneucCNotnullService.create(one2oneucCnotnull);
		LOGGER.debug("Created One2oneucCNotnull with information: {}" , one2oneucCnotnull);

	    return one2oneucCnotnull;
	}

    @ApiOperation(value = "Returns the One2oneucCNotnull instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneucCNotnull getOne2oneucCNotnull(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting One2oneucCNotnull with id: {}" , id);

        One2oneucCNotnull foundOne2oneucCNotnull = one2oneucCNotnullService.getById(id);
        LOGGER.debug("One2oneucCNotnull details with id: {}" , foundOne2oneucCNotnull);

        return foundOne2oneucCNotnull;
    }

    @ApiOperation(value = "Updates the One2oneucCNotnull instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneucCNotnull editOne2oneucCNotnull(@PathVariable("id") Integer id, @RequestBody One2oneucCNotnull one2oneucCnotnull) {
        LOGGER.debug("Editing One2oneucCNotnull with id: {}" , one2oneucCnotnull.getChildId());

        one2oneucCnotnull.setChildId(id);
        one2oneucCnotnull = one2oneucCNotnullService.update(one2oneucCnotnull);
        LOGGER.debug("One2oneucCNotnull details with id: {}" , one2oneucCnotnull);

        return one2oneucCnotnull;
    }

    @ApiOperation(value = "Deletes the One2oneucCNotnull instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteOne2oneucCNotnull(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting One2oneucCNotnull with id: {}" , id);

        One2oneucCNotnull deletedOne2oneucCNotnull = one2oneucCNotnullService.delete(id);

        return deletedOne2oneucCNotnull != null;
    }

    @RequestMapping(value = "/childColumn1/{childColumn1}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching One2oneucCNotnull with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneucCNotnull getByChildColumn1(@PathVariable("childColumn1") int childColumn1) {
        LOGGER.debug("Getting One2oneucCNotnull with uniques key ChildColumn1");
        return one2oneucCNotnullService.getByChildColumn1(childColumn1);
    }

    /**
     * @deprecated Use {@link #findOne2oneucCNotnulls(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of One2oneucCNotnull instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2oneucCNotnull> searchOne2oneucCNotnullsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering One2oneucCNotnulls list by query filter:{}", (Object) queryFilters);
        return one2oneucCNotnullService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of One2oneucCNotnull instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2oneucCNotnull> findOne2oneucCNotnulls(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering One2oneucCNotnulls list by filter:", query);
        return one2oneucCNotnullService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of One2oneucCNotnull instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2oneucCNotnull> filterOne2oneucCNotnulls(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering One2oneucCNotnulls list by filter", query);
        return one2oneucCNotnullService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportOne2oneucCNotnulls(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return one2oneucCNotnullService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportOne2oneucCNotnullsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = One2oneucCNotnull.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> one2oneucCNotnullService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of One2oneucCNotnull instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countOne2oneucCNotnulls( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting One2oneucCNotnulls");
		return one2oneucCNotnullService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getOne2oneucCNotnullAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return one2oneucCNotnullService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service One2oneucCNotnullService instance
	 */
	protected void setOne2oneucCNotnullService(One2oneucCNotnullService service) {
		this.one2oneucCNotnullService = service;
	}

}
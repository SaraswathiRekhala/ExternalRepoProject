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

import com.externalrepoproject.cascadedb.Many2oneCompositeC1;
import com.externalrepoproject.cascadedb.One2manyCompositeP1;
import com.externalrepoproject.cascadedb.One2manyCompositeP1Id;
import com.externalrepoproject.cascadedb.service.One2manyCompositeP1Service;


/**
 * Controller object for domain model class One2manyCompositeP1.
 * @see One2manyCompositeP1
 */
@RestController("CascadeDB.One2manyCompositeP1Controller")
@Api(value = "One2manyCompositeP1Controller", description = "Exposes APIs to work with One2manyCompositeP1 resource.")
@RequestMapping("/CascadeDB/One2manyCompositeP1")
public class One2manyCompositeP1Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2manyCompositeP1Controller.class);

    @Autowired
	@Qualifier("CascadeDB.One2manyCompositeP1Service")
	private One2manyCompositeP1Service one2manyCompositeP1Service;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new One2manyCompositeP1 instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2manyCompositeP1 createOne2manyCompositeP1(@RequestBody One2manyCompositeP1 one2manyCompositeP1) {
		LOGGER.debug("Create One2manyCompositeP1 with information: {}" , one2manyCompositeP1);

		one2manyCompositeP1 = one2manyCompositeP1Service.create(one2manyCompositeP1);
		LOGGER.debug("Created One2manyCompositeP1 with information: {}" , one2manyCompositeP1);

	    return one2manyCompositeP1;
	}

    @ApiOperation(value = "Returns the One2manyCompositeP1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2manyCompositeP1 getOne2manyCompositeP1(@RequestParam("parentStringId") String parentStringId, @RequestParam("parentIntId") Integer parentIntId) {

        One2manyCompositeP1Id one2manycompositep1Id = new One2manyCompositeP1Id();
        one2manycompositep1Id.setParentStringId(parentStringId);
        one2manycompositep1Id.setParentIntId(parentIntId);

        LOGGER.debug("Getting One2manyCompositeP1 with id: {}" , one2manycompositep1Id);
        One2manyCompositeP1 one2manyCompositeP1 = one2manyCompositeP1Service.getById(one2manycompositep1Id);
        LOGGER.debug("One2manyCompositeP1 details with id: {}" , one2manyCompositeP1);

        return one2manyCompositeP1;
    }



    @ApiOperation(value = "Updates the One2manyCompositeP1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2manyCompositeP1 editOne2manyCompositeP1(@RequestParam("parentStringId") String parentStringId, @RequestParam("parentIntId") Integer parentIntId, @RequestBody One2manyCompositeP1 one2manyCompositeP1) {

        one2manyCompositeP1.setParentStringId(parentStringId);
        one2manyCompositeP1.setParentIntId(parentIntId);

        LOGGER.debug("One2manyCompositeP1 details with id is updated with: {}" , one2manyCompositeP1);

        return one2manyCompositeP1Service.update(one2manyCompositeP1);
    }


    @ApiOperation(value = "Deletes the One2manyCompositeP1 instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteOne2manyCompositeP1(@RequestParam("parentStringId") String parentStringId, @RequestParam("parentIntId") Integer parentIntId) {

        One2manyCompositeP1Id one2manycompositep1Id = new One2manyCompositeP1Id();
        one2manycompositep1Id.setParentStringId(parentStringId);
        one2manycompositep1Id.setParentIntId(parentIntId);

        LOGGER.debug("Deleting One2manyCompositeP1 with id: {}" , one2manycompositep1Id);
        One2manyCompositeP1 one2manyCompositeP1 = one2manyCompositeP1Service.delete(one2manycompositep1Id);

        return one2manyCompositeP1 != null;
    }


    /**
     * @deprecated Use {@link #findOne2manyCompositeP1s(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of One2manyCompositeP1 instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2manyCompositeP1> searchOne2manyCompositeP1sByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering One2manyCompositeP1s list by query filter:{}", (Object) queryFilters);
        return one2manyCompositeP1Service.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of One2manyCompositeP1 instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2manyCompositeP1> findOne2manyCompositeP1s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering One2manyCompositeP1s list by filter:", query);
        return one2manyCompositeP1Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of One2manyCompositeP1 instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2manyCompositeP1> filterOne2manyCompositeP1s(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering One2manyCompositeP1s list by filter", query);
        return one2manyCompositeP1Service.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportOne2manyCompositeP1s(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return one2manyCompositeP1Service.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportOne2manyCompositeP1sAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = One2manyCompositeP1.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> one2manyCompositeP1Service.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of One2manyCompositeP1 instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countOne2manyCompositeP1s( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting One2manyCompositeP1s");
		return one2manyCompositeP1Service.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getOne2manyCompositeP1AggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return one2manyCompositeP1Service.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/composite-id/many2oneCompositeC1s", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the many2oneCompositeC1s instance associated with the given composite-id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Many2oneCompositeC1> findAssociatedMany2oneCompositeC1s(@RequestParam("parentStringId") String parentStringId, @RequestParam("parentIntId") Integer parentIntId, Pageable pageable) {

        LOGGER.debug("Fetching all associated many2oneCompositeC1s");
        return one2manyCompositeP1Service.findAssociatedMany2oneCompositeC1s(parentStringId, parentIntId, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service One2manyCompositeP1Service instance
	 */
	protected void setOne2manyCompositeP1Service(One2manyCompositeP1Service service) {
		this.one2manyCompositeP1Service = service;
	}

}
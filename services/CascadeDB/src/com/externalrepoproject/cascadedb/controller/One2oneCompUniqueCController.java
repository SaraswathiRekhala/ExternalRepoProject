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

import com.externalrepoproject.cascadedb.One2oneCompUniqueC;
import com.externalrepoproject.cascadedb.service.One2oneCompUniqueCService;


/**
 * Controller object for domain model class One2oneCompUniqueC.
 * @see One2oneCompUniqueC
 */
@RestController("CascadeDB.One2oneCompUniqueCController")
@Api(value = "One2oneCompUniqueCController", description = "Exposes APIs to work with One2oneCompUniqueC resource.")
@RequestMapping("/CascadeDB/One2oneCompUniqueC")
public class One2oneCompUniqueCController {

    private static final Logger LOGGER = LoggerFactory.getLogger(One2oneCompUniqueCController.class);

    @Autowired
	@Qualifier("CascadeDB.One2oneCompUniqueCService")
	private One2oneCompUniqueCService one2oneCompUniqueCService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new One2oneCompUniqueC instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneCompUniqueC createOne2oneCompUniqueC(@RequestBody One2oneCompUniqueC one2oneCompUniqueC) {
		LOGGER.debug("Create One2oneCompUniqueC with information: {}" , one2oneCompUniqueC);

		one2oneCompUniqueC = one2oneCompUniqueCService.create(one2oneCompUniqueC);
		LOGGER.debug("Created One2oneCompUniqueC with information: {}" , one2oneCompUniqueC);

	    return one2oneCompUniqueC;
	}

    @ApiOperation(value = "Returns the One2oneCompUniqueC instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneCompUniqueC getOne2oneCompUniqueC(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting One2oneCompUniqueC with id: {}" , id);

        One2oneCompUniqueC foundOne2oneCompUniqueC = one2oneCompUniqueCService.getById(id);
        LOGGER.debug("One2oneCompUniqueC details with id: {}" , foundOne2oneCompUniqueC);

        return foundOne2oneCompUniqueC;
    }

    @ApiOperation(value = "Updates the One2oneCompUniqueC instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneCompUniqueC editOne2oneCompUniqueC(@PathVariable("id") Integer id, @RequestBody One2oneCompUniqueC one2oneCompUniqueC) {
        LOGGER.debug("Editing One2oneCompUniqueC with id: {}" , one2oneCompUniqueC.getChildId());

        one2oneCompUniqueC.setChildId(id);
        one2oneCompUniqueC = one2oneCompUniqueCService.update(one2oneCompUniqueC);
        LOGGER.debug("One2oneCompUniqueC details with id: {}" , one2oneCompUniqueC);

        return one2oneCompUniqueC;
    }

    @ApiOperation(value = "Deletes the One2oneCompUniqueC instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteOne2oneCompUniqueC(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting One2oneCompUniqueC with id: {}" , id);

        One2oneCompUniqueC deletedOne2oneCompUniqueC = one2oneCompUniqueCService.delete(id);

        return deletedOne2oneCompUniqueC != null;
    }

    @RequestMapping(value = "/childIntId-childStringId", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching One2oneCompUniqueC with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public One2oneCompUniqueC getByChildIntIdAndChildStringId(@RequestParam("childIntId") Integer childIntId, @RequestParam("childStringId") String childStringId) {
        LOGGER.debug("Getting One2oneCompUniqueC with uniques key ChildIntIdAndChildStringId");
        return one2oneCompUniqueCService.getByChildIntIdAndChildStringId(childIntId, childStringId);
    }

    /**
     * @deprecated Use {@link #findOne2oneCompUniqueCs(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of One2oneCompUniqueC instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2oneCompUniqueC> searchOne2oneCompUniqueCsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering One2oneCompUniqueCs list by query filter:{}", (Object) queryFilters);
        return one2oneCompUniqueCService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of One2oneCompUniqueC instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2oneCompUniqueC> findOne2oneCompUniqueCs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering One2oneCompUniqueCs list by filter:", query);
        return one2oneCompUniqueCService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of One2oneCompUniqueC instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<One2oneCompUniqueC> filterOne2oneCompUniqueCs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering One2oneCompUniqueCs list by filter", query);
        return one2oneCompUniqueCService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportOne2oneCompUniqueCs(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return one2oneCompUniqueCService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportOne2oneCompUniqueCsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = One2oneCompUniqueC.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> one2oneCompUniqueCService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of One2oneCompUniqueC instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countOne2oneCompUniqueCs( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting One2oneCompUniqueCs");
		return one2oneCompUniqueCService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getOne2oneCompUniqueCAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return one2oneCompUniqueCService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service One2oneCompUniqueCService instance
	 */
	protected void setOne2oneCompUniqueCService(One2oneCompUniqueCService service) {
		this.one2oneCompUniqueCService = service;
	}

}
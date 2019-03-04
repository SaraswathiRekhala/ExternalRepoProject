/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.TypeMismatchException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.externalrepoproject.actordb.Payment;
import com.externalrepoproject.actordb.Rental;
import com.externalrepoproject.actordb.Staff;
import com.externalrepoproject.actordb.service.StaffService;


/**
 * Controller object for domain model class Staff.
 * @see Staff
 */
@RestController("actordb.StaffController")
@Api(value = "StaffController", description = "Exposes APIs to work with Staff resource.")
@RequestMapping("/actordb/Staff")
public class StaffController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaffController.class);

    @Autowired
	@Qualifier("actordb.StaffService")
	private StaffService staffService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Staff instance.")
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Staff createStaff(@RequestPart("wm_data_json") Staff staff, @RequestPart(value = "picture", required = false) MultipartFile _picture) {
		LOGGER.debug("Create Staff with information: {}" , staff);

    staff.setPicture(WMMultipartUtils.toByteArray(_picture));
		staff = staffService.create(staff);
		LOGGER.debug("Created Staff with information: {}" , staff);

	    return staff;
	}

    @ApiOperation(value = "Returns the Staff instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Staff getStaff(@PathVariable("id") Short id) {
        LOGGER.debug("Getting Staff with id: {}" , id);

        Staff foundStaff = staffService.getById(id);
        LOGGER.debug("Staff details with id: {}" , foundStaff);

        return foundStaff;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Staff instance" )
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces="application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getStaffBLOBContent(@PathVariable("id") Short id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value="download", defaultValue = "false") boolean download) {

        LOGGER.debug("Retrieves content for the given BLOB field {} in Staff instance" , fieldName);

        if(!WMRuntimeUtils.isLob(Staff.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        Staff staff = staffService.getById(id);

        return WMMultipartUtils.buildDownloadResponseForBlob(staff, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the Staff instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Staff editStaff(@PathVariable("id") Short id, @RequestBody Staff staff) {
        LOGGER.debug("Editing Staff with id: {}" , staff.getStaffId());

        staff.setStaffId(id);
        staff = staffService.update(staff);
        LOGGER.debug("Staff details with id: {}" , staff);

        return staff;
    }

    @ApiOperation(value = "Updates the Staff instance associated with the given id.This API should be used when Staff instance fields that require multipart data.") 
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Staff editStaff(@PathVariable("id") Short id, MultipartHttpServletRequest multipartHttpServletRequest) {
        Staff newStaff = WMMultipartUtils.toObject(multipartHttpServletRequest, Staff.class, "actordb");
        newStaff.setStaffId(id);

        Staff oldStaff = staffService.getById(id);
        WMMultipartUtils.updateLobsContent(oldStaff, newStaff);
        LOGGER.debug("Updating Staff with information: {}" , newStaff);

        return staffService.update(newStaff);
    }

    @ApiOperation(value = "Deletes the Staff instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteStaff(@PathVariable("id") Short id) {
        LOGGER.debug("Deleting Staff with id: {}" , id);

        Staff deletedStaff = staffService.delete(id);

        return deletedStaff != null;
    }

    /**
     * @deprecated Use {@link #findStaffs(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Staff instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Staff> searchStaffsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Staffs list by query filter:{}", (Object) queryFilters);
        return staffService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Staff instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Staff> findStaffs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Staffs list by filter:", query);
        return staffService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Staff instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Staff> filterStaffs(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Staffs list by filter", query);
        return staffService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportStaffs(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return staffService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportStaffsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Staff.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> staffService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Staff instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countStaffs( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Staffs");
		return staffService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getStaffAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return staffService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/payments", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the payments instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Payment> findAssociatedPayments(@PathVariable("id") Short id, Pageable pageable) {

        LOGGER.debug("Fetching all associated payments");
        return staffService.findAssociatedPayments(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/rentals", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the rentals instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rental> findAssociatedRentals(@PathVariable("id") Short id, Pageable pageable) {

        LOGGER.debug("Fetching all associated rentals");
        return staffService.findAssociatedRentals(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StaffService instance
	 */
	protected void setStaffService(StaffService service) {
		this.staffService = service;
	}

}
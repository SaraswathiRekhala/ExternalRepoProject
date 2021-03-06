/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.time.LocalDateTime;
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

import com.externalrepoproject.actordb.Payment;
import com.externalrepoproject.actordb.Rental;
import com.externalrepoproject.actordb.service.RentalService;


/**
 * Controller object for domain model class Rental.
 * @see Rental
 */
@RestController("actordb.RentalController")
@Api(value = "RentalController", description = "Exposes APIs to work with Rental resource.")
@RequestMapping("/actordb/Rental")
public class RentalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalController.class);

    @Autowired
	@Qualifier("actordb.RentalService")
	private RentalService rentalService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Rental instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rental createRental(@RequestBody Rental rental) {
		LOGGER.debug("Create Rental with information: {}" , rental);

		rental = rentalService.create(rental);
		LOGGER.debug("Created Rental with information: {}" , rental);

	    return rental;
	}

    @ApiOperation(value = "Returns the Rental instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rental getRental(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting Rental with id: {}" , id);

        Rental foundRental = rentalService.getById(id);
        LOGGER.debug("Rental details with id: {}" , foundRental);

        return foundRental;
    }

    @ApiOperation(value = "Updates the Rental instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rental editRental(@PathVariable("id") Integer id, @RequestBody Rental rental) {
        LOGGER.debug("Editing Rental with id: {}" , rental.getRentalId());

        rental.setRentalId(id);
        rental = rentalService.update(rental);
        LOGGER.debug("Rental details with id: {}" , rental);

        return rental;
    }

    @ApiOperation(value = "Deletes the Rental instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteRental(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting Rental with id: {}" , id);

        Rental deletedRental = rentalService.delete(id);

        return deletedRental != null;
    }

    @RequestMapping(value = "/rentalDate-inventoryId-customerId", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Rental with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rental getByRentalDateAndInventoryIdAndCustomerId(@RequestParam("rentalDate") LocalDateTime rentalDate, @RequestParam("inventoryId") int inventoryId, @RequestParam("customerId") short customerId) {
        LOGGER.debug("Getting Rental with uniques key RentalDateAndInventoryIdAndCustomerId");
        return rentalService.getByRentalDateAndInventoryIdAndCustomerId(rentalDate, inventoryId, customerId);
    }

    /**
     * @deprecated Use {@link #findRentals(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Rental instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rental> searchRentalsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Rentals list by query filter:{}", (Object) queryFilters);
        return rentalService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Rental instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rental> findRentals(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Rentals list by filter:", query);
        return rentalService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Rental instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rental> filterRentals(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Rentals list by filter", query);
        return rentalService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportRentals(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return rentalService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportRentalsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Rental.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> rentalService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Rental instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countRentals( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Rentals");
		return rentalService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getRentalAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return rentalService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/payments", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the payments instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Payment> findAssociatedPayments(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated payments");
        return rentalService.findAssociatedPayments(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service RentalService instance
	 */
	protected void setRentalService(RentalService service) {
		this.rentalService = service;
	}

}
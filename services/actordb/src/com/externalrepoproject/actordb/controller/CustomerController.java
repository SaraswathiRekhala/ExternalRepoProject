/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb.controller;

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

import com.externalrepoproject.actordb.Customer;
import com.externalrepoproject.actordb.Payment;
import com.externalrepoproject.actordb.Rental;
import com.externalrepoproject.actordb.service.CustomerService;


/**
 * Controller object for domain model class Customer.
 * @see Customer
 */
@RestController("actordb.CustomerController")
@Api(value = "CustomerController", description = "Exposes APIs to work with Customer resource.")
@RequestMapping("/actordb/Customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
	@Qualifier("actordb.CustomerService")
	private CustomerService customerService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Customer instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Customer createCustomer(@RequestBody Customer customer) {
		LOGGER.debug("Create Customer with information: {}" , customer);

		customer = customerService.create(customer);
		LOGGER.debug("Created Customer with information: {}" , customer);

	    return customer;
	}

    @ApiOperation(value = "Returns the Customer instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Customer getCustomer(@PathVariable("id") Short id) {
        LOGGER.debug("Getting Customer with id: {}" , id);

        Customer foundCustomer = customerService.getById(id);
        LOGGER.debug("Customer details with id: {}" , foundCustomer);

        return foundCustomer;
    }

    @ApiOperation(value = "Updates the Customer instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Customer editCustomer(@PathVariable("id") Short id, @RequestBody Customer customer) {
        LOGGER.debug("Editing Customer with id: {}" , customer.getCustomerId());

        customer.setCustomerId(id);
        customer = customerService.update(customer);
        LOGGER.debug("Customer details with id: {}" , customer);

        return customer;
    }

    @ApiOperation(value = "Deletes the Customer instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteCustomer(@PathVariable("id") Short id) {
        LOGGER.debug("Deleting Customer with id: {}" , id);

        Customer deletedCustomer = customerService.delete(id);

        return deletedCustomer != null;
    }

    /**
     * @deprecated Use {@link #findCustomers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Customer instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Customer> searchCustomersByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Customers list by query filter:{}", (Object) queryFilters);
        return customerService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Customer instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Customer> findCustomers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Customers list by filter:", query);
        return customerService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Customer instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Customer> filterCustomers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Customers list by filter", query);
        return customerService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportCustomers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return customerService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportCustomersAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Customer.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> customerService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Customer instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countCustomers( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Customers");
		return customerService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getCustomerAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return customerService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/payments", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the payments instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Payment> findAssociatedPayments(@PathVariable("id") Short id, Pageable pageable) {

        LOGGER.debug("Fetching all associated payments");
        return customerService.findAssociatedPayments(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/rentals", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the rentals instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rental> findAssociatedRentals(@PathVariable("id") Short id, Pageable pageable) {

        LOGGER.debug("Fetching all associated rentals");
        return customerService.findAssociatedRentals(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CustomerService instance
	 */
	protected void setCustomerService(CustomerService service) {
		this.customerService = service;
	}

}
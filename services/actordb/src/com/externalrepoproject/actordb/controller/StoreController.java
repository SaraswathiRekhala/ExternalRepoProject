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
import com.externalrepoproject.actordb.Inventory;
import com.externalrepoproject.actordb.Staff;
import com.externalrepoproject.actordb.Store;
import com.externalrepoproject.actordb.service.StoreService;


/**
 * Controller object for domain model class Store.
 * @see Store
 */
@RestController("actordb.StoreController")
@Api(value = "StoreController", description = "Exposes APIs to work with Store resource.")
@RequestMapping("/actordb/Store")
public class StoreController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

    @Autowired
	@Qualifier("actordb.StoreService")
	private StoreService storeService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Store instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Store createStore(@RequestBody Store store) {
		LOGGER.debug("Create Store with information: {}" , store);

		store = storeService.create(store);
		LOGGER.debug("Created Store with information: {}" , store);

	    return store;
	}

    @ApiOperation(value = "Returns the Store instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Store getStore(@PathVariable("id") Short id) {
        LOGGER.debug("Getting Store with id: {}" , id);

        Store foundStore = storeService.getById(id);
        LOGGER.debug("Store details with id: {}" , foundStore);

        return foundStore;
    }

    @ApiOperation(value = "Updates the Store instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Store editStore(@PathVariable("id") Short id, @RequestBody Store store) {
        LOGGER.debug("Editing Store with id: {}" , store.getStoreId());

        store.setStoreId(id);
        store = storeService.update(store);
        LOGGER.debug("Store details with id: {}" , store);

        return store;
    }

    @ApiOperation(value = "Deletes the Store instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteStore(@PathVariable("id") Short id) {
        LOGGER.debug("Deleting Store with id: {}" , id);

        Store deletedStore = storeService.delete(id);

        return deletedStore != null;
    }

    @RequestMapping(value = "/managerStaffId/{managerStaffId}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Store with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Store getByManagerStaffId(@PathVariable("managerStaffId") short managerStaffId) {
        LOGGER.debug("Getting Store with uniques key ManagerStaffId");
        return storeService.getByManagerStaffId(managerStaffId);
    }

    /**
     * @deprecated Use {@link #findStores(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Store instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Store> searchStoresByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Stores list by query filter:{}", (Object) queryFilters);
        return storeService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Store instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Store> findStores(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Stores list by filter:", query);
        return storeService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Store instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Store> filterStores(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Stores list by filter", query);
        return storeService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportStores(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return storeService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportStoresAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Store.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> storeService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Store instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countStores( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Stores");
		return storeService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getStoreAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return storeService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/customers", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the customers instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Customer> findAssociatedCustomers(@PathVariable("id") Short id, Pageable pageable) {

        LOGGER.debug("Fetching all associated customers");
        return storeService.findAssociatedCustomers(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/inventories", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the inventories instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Inventory> findAssociatedInventories(@PathVariable("id") Short id, Pageable pageable) {

        LOGGER.debug("Fetching all associated inventories");
        return storeService.findAssociatedInventories(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/staffsForStoreId", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the staffsForStoreId instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Staff> findAssociatedStaffsForStoreId(@PathVariable("id") Short id, Pageable pageable) {

        LOGGER.debug("Fetching all associated staffsForStoreId");
        return storeService.findAssociatedStaffsForStoreId(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StoreService instance
	 */
	protected void setStoreService(StoreService service) {
		this.storeService = service;
	}

}
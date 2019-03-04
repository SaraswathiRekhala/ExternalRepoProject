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

import com.externalrepoproject.actordb.FilmList;
import com.externalrepoproject.actordb.FilmListId;
import com.externalrepoproject.actordb.service.FilmListService;


/**
 * Controller object for domain model class FilmList.
 * @see FilmList
 */
@RestController("actordb.FilmListController")
@Api(value = "FilmListController", description = "Exposes APIs to work with FilmList resource.")
@RequestMapping("/actordb/FilmList")
public class FilmListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmListController.class);

    @Autowired
	@Qualifier("actordb.FilmListService")
	private FilmListService filmListService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new FilmList instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FilmList createFilmList(@RequestBody FilmList filmList) {
		LOGGER.debug("Create FilmList with information: {}" , filmList);

		filmList = filmListService.create(filmList);
		LOGGER.debug("Created FilmList with information: {}" , filmList);

	    return filmList;
	}

    @ApiOperation(value = "Returns the FilmList instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FilmList getFilmList(@RequestParam("fid") Short fid, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("category") String category, @RequestParam("price") Float price, @RequestParam("length") Short length, @RequestParam("rating") String rating, @RequestParam("actors") String actors) {

        FilmListId filmlistId = new FilmListId();
        filmlistId.setFid(fid);
        filmlistId.setTitle(title);
        filmlistId.setDescription(description);
        filmlistId.setCategory(category);
        filmlistId.setPrice(price);
        filmlistId.setLength(length);
        filmlistId.setRating(rating);
        filmlistId.setActors(actors);

        LOGGER.debug("Getting FilmList with id: {}" , filmlistId);
        FilmList filmList = filmListService.getById(filmlistId);
        LOGGER.debug("FilmList details with id: {}" , filmList);

        return filmList;
    }



    @ApiOperation(value = "Updates the FilmList instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FilmList editFilmList(@RequestParam("fid") Short fid, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("category") String category, @RequestParam("price") Float price, @RequestParam("length") Short length, @RequestParam("rating") String rating, @RequestParam("actors") String actors, @RequestBody FilmList filmList) {

        filmList.setFid(fid);
        filmList.setTitle(title);
        filmList.setDescription(description);
        filmList.setCategory(category);
        filmList.setPrice(price);
        filmList.setLength(length);
        filmList.setRating(rating);
        filmList.setActors(actors);

        LOGGER.debug("FilmList details with id is updated with: {}" , filmList);

        return filmListService.update(filmList);
    }


    @ApiOperation(value = "Deletes the FilmList instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteFilmList(@RequestParam("fid") Short fid, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("category") String category, @RequestParam("price") Float price, @RequestParam("length") Short length, @RequestParam("rating") String rating, @RequestParam("actors") String actors) {

        FilmListId filmlistId = new FilmListId();
        filmlistId.setFid(fid);
        filmlistId.setTitle(title);
        filmlistId.setDescription(description);
        filmlistId.setCategory(category);
        filmlistId.setPrice(price);
        filmlistId.setLength(length);
        filmlistId.setRating(rating);
        filmlistId.setActors(actors);

        LOGGER.debug("Deleting FilmList with id: {}" , filmlistId);
        FilmList filmList = filmListService.delete(filmlistId);

        return filmList != null;
    }


    /**
     * @deprecated Use {@link #findFilmLists(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of FilmList instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FilmList> searchFilmListsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FilmLists list by query filter:{}", (Object) queryFilters);
        return filmListService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FilmList instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FilmList> findFilmLists(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FilmLists list by filter:", query);
        return filmListService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FilmList instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FilmList> filterFilmLists(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FilmLists list by filter", query);
        return filmListService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportFilmLists(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return filmListService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportFilmListsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = FilmList.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> filmListService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of FilmList instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countFilmLists( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting FilmLists");
		return filmListService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getFilmListAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return filmListService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FilmListService instance
	 */
	protected void setFilmListService(FilmListService service) {
		this.filmListService = service;
	}

}
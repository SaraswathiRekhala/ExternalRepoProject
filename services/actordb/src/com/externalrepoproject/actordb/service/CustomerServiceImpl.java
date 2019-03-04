/*Copyright (c) 2015-2016 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.externalrepoproject.actordb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.externalrepoproject.actordb.Customer;
import com.externalrepoproject.actordb.Payment;
import com.externalrepoproject.actordb.Rental;


/**
 * ServiceImpl object for domain model class Customer.
 *
 * @see Customer
 */
@Service("actordb.CustomerService")
@Validated
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("actordb.RentalService")
    private RentalService rentalService;

    @Lazy
    @Autowired
    @Qualifier("actordb.PaymentService")
    private PaymentService paymentService;

    @Autowired
    @Qualifier("actordb.CustomerDao")
    private WMGenericDao<Customer, Short> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Customer, Short> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public Customer create(Customer customer) {
        LOGGER.debug("Creating a new Customer with information: {}", customer);

        Customer customerCreated = this.wmGenericDao.create(customer);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(customerCreated);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Customer getById(Short customerIdInstance) {
        LOGGER.debug("Finding Customer by id: {}", customerIdInstance);
        return this.wmGenericDao.findById(customerIdInstance);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Customer findById(Short customerIdInstance) {
        LOGGER.debug("Finding Customer by id: {}", customerIdInstance);
        try {
            return this.wmGenericDao.findById(customerIdInstance);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Customer found with id: {}", customerIdInstance, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public List<Customer> findByMultipleIds(List<Short> customerIdInstances, boolean orderedReturn) {
        LOGGER.debug("Finding Customers by ids: {}", customerIdInstances);

        return this.wmGenericDao.findByMultipleIds(customerIdInstances, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "actordbTransactionManager")
    @Override
    public Customer update(Customer customer) {
        LOGGER.debug("Updating Customer with information: {}", customer);

        this.wmGenericDao.update(customer);
        this.wmGenericDao.refresh(customer);

        return customer;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public Customer delete(Short customerIdInstance) {
        LOGGER.debug("Deleting Customer with id: {}", customerIdInstance);
        Customer deleted = this.wmGenericDao.findById(customerIdInstance);
        if (deleted == null) {
            LOGGER.debug("No Customer found with id: {}", customerIdInstance);
            throw new EntityNotFoundException(String.valueOf(customerIdInstance));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "actordbTransactionManager")
    @Override
    public void delete(Customer customer) {
        LOGGER.debug("Deleting Customer with {}", customer);
        this.wmGenericDao.delete(customer);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Customer> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Customers");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Customer> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Customers");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service actordb for table Customer to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service actordb for table Customer to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Payment> findAssociatedPayments(Short customerId, Pageable pageable) {
        LOGGER.debug("Fetching all associated payments");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("customer.customerId = '" + customerId + "'");

        return paymentService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "actordbTransactionManager")
    @Override
    public Page<Rental> findAssociatedRentals(Short customerId, Pageable pageable) {
        LOGGER.debug("Fetching all associated rentals");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("customer.customerId = '" + customerId + "'");

        return rentalService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service RentalService instance
     */
    protected void setRentalService(RentalService service) {
        this.rentalService = service;
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service PaymentService instance
     */
    protected void setPaymentService(PaymentService service) {
        this.paymentService = service;
    }

}
package com.oreon.ecomm.service;

import com.oreon.ecomm.domain.Shipment;
import com.oreon.ecomm.repository.ShipmentRepository;
import com.oreon.ecomm.repository.search.ShipmentSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Shipment.
 */
@Service
@Transactional
public class ShipmentService {

    private final Logger log = LoggerFactory.getLogger(ShipmentService.class);

    private final ShipmentRepository shipmentRepository;

    private final ShipmentSearchRepository shipmentSearchRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, ShipmentSearchRepository shipmentSearchRepository) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentSearchRepository = shipmentSearchRepository;
    }

    /**
     * Save a shipment.
     *
     * @param shipment the entity to save
     * @return the persisted entity
     */
    public Shipment save(Shipment shipment) {
        log.debug("Request to save Shipment : {}", shipment);
        Shipment result = shipmentRepository.save(shipment);
        shipmentSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the shipments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Shipment> findAll(Pageable pageable) {
        log.debug("Request to get all Shipments");
        return shipmentRepository.findAll(pageable);
    }

    /**
     * Get one shipment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Shipment findOne(Long id) {
        log.debug("Request to get Shipment : {}", id);
        return shipmentRepository.findOne(id);
    }

    /**
     * Delete the shipment by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Shipment : {}", id);
        shipmentRepository.delete(id);
        shipmentSearchRepository.delete(id);
    }

    /**
     * Search for the shipment corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Shipment> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Shipments for query {}", query);
        Page<Shipment> result = shipmentSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}

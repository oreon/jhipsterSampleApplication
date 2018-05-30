package com.oreon.ecomm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oreon.ecomm.domain.Shipment;

import com.oreon.ecomm.repository.ShipmentRepository;
import com.oreon.ecomm.repository.search.ShipmentSearchRepository;
import com.oreon.ecomm.web.rest.errors.BadRequestAlertException;
import com.oreon.ecomm.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Shipment.
 */
@RestController
@RequestMapping("/api")
public class ShipmentResource {

    private final Logger log = LoggerFactory.getLogger(ShipmentResource.class);

    private static final String ENTITY_NAME = "shipment";

    private final ShipmentRepository shipmentRepository;

    private final ShipmentSearchRepository shipmentSearchRepository;

    public ShipmentResource(ShipmentRepository shipmentRepository, ShipmentSearchRepository shipmentSearchRepository) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentSearchRepository = shipmentSearchRepository;
    }

    /**
     * POST  /shipments : Create a new shipment.
     *
     * @param shipment the shipment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new shipment, or with status 400 (Bad Request) if the shipment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/shipments")
    @Timed
    public ResponseEntity<Shipment> createShipment(@Valid @RequestBody Shipment shipment) throws URISyntaxException {
        log.debug("REST request to save Shipment : {}", shipment);
        if (shipment.getId() != null) {
            throw new BadRequestAlertException("A new shipment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Shipment result = shipmentRepository.save(shipment);
        shipmentSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/shipments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /shipments : Updates an existing shipment.
     *
     * @param shipment the shipment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated shipment,
     * or with status 400 (Bad Request) if the shipment is not valid,
     * or with status 500 (Internal Server Error) if the shipment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/shipments")
    @Timed
    public ResponseEntity<Shipment> updateShipment(@Valid @RequestBody Shipment shipment) throws URISyntaxException {
        log.debug("REST request to update Shipment : {}", shipment);
        if (shipment.getId() == null) {
            return createShipment(shipment);
        }
        Shipment result = shipmentRepository.save(shipment);
        shipmentSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, shipment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /shipments : get all the shipments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of shipments in body
     */
    @GetMapping("/shipments")
    @Timed
    public List<Shipment> getAllShipments() {
        log.debug("REST request to get all Shipments");
        return shipmentRepository.findAll();
        }

    /**
     * GET  /shipments/:id : get the "id" shipment.
     *
     * @param id the id of the shipment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the shipment, or with status 404 (Not Found)
     */
    @GetMapping("/shipments/{id}")
    @Timed
    public ResponseEntity<Shipment> getShipment(@PathVariable Long id) {
        log.debug("REST request to get Shipment : {}", id);
        Shipment shipment = shipmentRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(shipment));
    }

    /**
     * DELETE  /shipments/:id : delete the "id" shipment.
     *
     * @param id the id of the shipment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/shipments/{id}")
    @Timed
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        log.debug("REST request to delete Shipment : {}", id);
        shipmentRepository.delete(id);
        shipmentSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/shipments?query=:query : search for the shipment corresponding
     * to the query.
     *
     * @param query the query of the shipment search
     * @return the result of the search
     */
    @GetMapping("/_search/shipments")
    @Timed
    public List<Shipment> searchShipments(@RequestParam String query) {
        log.debug("REST request to search Shipments for query {}", query);
        return StreamSupport
            .stream(shipmentSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}

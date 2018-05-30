package com.oreon.ecomm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oreon.ecomm.domain.OrderItem;

import com.oreon.ecomm.repository.OrderItemRepository;
import com.oreon.ecomm.repository.search.OrderItemSearchRepository;
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
 * REST controller for managing OrderItem.
 */
@RestController
@RequestMapping("/api")
public class OrderItemResource {

    private final Logger log = LoggerFactory.getLogger(OrderItemResource.class);

    private static final String ENTITY_NAME = "orderItem";

    private final OrderItemRepository orderItemRepository;

    private final OrderItemSearchRepository orderItemSearchRepository;

    public OrderItemResource(OrderItemRepository orderItemRepository, OrderItemSearchRepository orderItemSearchRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemSearchRepository = orderItemSearchRepository;
    }

    /**
     * POST  /order-items : Create a new orderItem.
     *
     * @param orderItem the orderItem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderItem, or with status 400 (Bad Request) if the orderItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-items")
    @Timed
    public ResponseEntity<OrderItem> createOrderItem(@Valid @RequestBody OrderItem orderItem) throws URISyntaxException {
        log.debug("REST request to save OrderItem : {}", orderItem);
        if (orderItem.getId() != null) {
            throw new BadRequestAlertException("A new orderItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderItem result = orderItemRepository.save(orderItem);
        orderItemSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/order-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-items : Updates an existing orderItem.
     *
     * @param orderItem the orderItem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderItem,
     * or with status 400 (Bad Request) if the orderItem is not valid,
     * or with status 500 (Internal Server Error) if the orderItem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-items")
    @Timed
    public ResponseEntity<OrderItem> updateOrderItem(@Valid @RequestBody OrderItem orderItem) throws URISyntaxException {
        log.debug("REST request to update OrderItem : {}", orderItem);
        if (orderItem.getId() == null) {
            return createOrderItem(orderItem);
        }
        OrderItem result = orderItemRepository.save(orderItem);
        orderItemSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderItem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-items : get all the orderItems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orderItems in body
     */
    @GetMapping("/order-items")
    @Timed
    public List<OrderItem> getAllOrderItems() {
        log.debug("REST request to get all OrderItems");
        return orderItemRepository.findAll();
        }

    /**
     * GET  /order-items/:id : get the "id" orderItem.
     *
     * @param id the id of the orderItem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderItem, or with status 404 (Not Found)
     */
    @GetMapping("/order-items/{id}")
    @Timed
    public ResponseEntity<OrderItem> getOrderItem(@PathVariable Long id) {
        log.debug("REST request to get OrderItem : {}", id);
        OrderItem orderItem = orderItemRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderItem));
    }

    /**
     * DELETE  /order-items/:id : delete the "id" orderItem.
     *
     * @param id the id of the orderItem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        log.debug("REST request to delete OrderItem : {}", id);
        orderItemRepository.delete(id);
        orderItemSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/order-items?query=:query : search for the orderItem corresponding
     * to the query.
     *
     * @param query the query of the orderItem search
     * @return the result of the search
     */
    @GetMapping("/_search/order-items")
    @Timed
    public List<OrderItem> searchOrderItems(@RequestParam String query) {
        log.debug("REST request to search OrderItems for query {}", query);
        return StreamSupport
            .stream(orderItemSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}

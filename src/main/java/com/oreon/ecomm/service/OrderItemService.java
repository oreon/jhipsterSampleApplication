package com.oreon.ecomm.service;

import com.oreon.ecomm.domain.OrderItem;
import com.oreon.ecomm.repository.OrderItemRepository;
import com.oreon.ecomm.repository.search.OrderItemSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing OrderItem.
 */
@Service
@Transactional
public class OrderItemService {

    private final Logger log = LoggerFactory.getLogger(OrderItemService.class);

    private final OrderItemRepository orderItemRepository;

    private final OrderItemSearchRepository orderItemSearchRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemSearchRepository orderItemSearchRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemSearchRepository = orderItemSearchRepository;
    }

    /**
     * Save a orderItem.
     *
     * @param orderItem the entity to save
     * @return the persisted entity
     */
    public OrderItem save(OrderItem orderItem) {
        log.debug("Request to save OrderItem : {}", orderItem);
        OrderItem result = orderItemRepository.save(orderItem);
        orderItemSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the orderItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<OrderItem> findAll(Pageable pageable) {
        log.debug("Request to get all OrderItems");
        return orderItemRepository.findAll(pageable);
    }

    /**
     * Get one orderItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public OrderItem findOne(Long id) {
        log.debug("Request to get OrderItem : {}", id);
        return orderItemRepository.findOne(id);
    }

    /**
     * Delete the orderItem by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderItem : {}", id);
        orderItemRepository.delete(id);
        orderItemSearchRepository.delete(id);
    }

    /**
     * Search for the orderItem corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<OrderItem> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of OrderItems for query {}", query);
        Page<OrderItem> result = orderItemSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
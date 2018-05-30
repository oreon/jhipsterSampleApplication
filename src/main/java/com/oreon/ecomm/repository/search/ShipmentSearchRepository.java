package com.oreon.ecomm.repository.search;

import com.oreon.ecomm.domain.Shipment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Shipment entity.
 */
public interface ShipmentSearchRepository extends ElasticsearchRepository<Shipment, Long> {
}

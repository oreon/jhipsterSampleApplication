package com.oreon.ecomm.repository.search;

import com.oreon.ecomm.domain.ProductOrder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ProductOrder entity.
 */
public interface ProductOrderSearchRepository extends ElasticsearchRepository<ProductOrder, Long> {
}

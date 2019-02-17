package io.noep.graph.repo;

import io.noep.graph.domain.FlatNode;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Desc  :
 */
public interface FlatNodeRepository extends ReactiveMongoRepository<FlatNode, String> {
}

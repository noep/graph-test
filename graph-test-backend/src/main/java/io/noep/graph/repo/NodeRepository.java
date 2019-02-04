package io.noep.graph.repo;

import io.noep.graph.domain.Node;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * Desc  :
 */
@NoRepositoryBean
public interface NodeRepository  extends Repository<String, Node> {
}

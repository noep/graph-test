package io.noep.graph;

/**
 * Desc  :
 */
public class NodeService {

    private int id;

    public Node traverse(Node node, int rootId) {

        node.setId(++id);
        System.out.println(node);

        node.getChildren().forEach(childNode -> {
            int computedRootId = rootId == 0 ? node.getId() : rootId;
            childNode.setParentId(node.getId());
            childNode.setRootId(computedRootId);
            this.traverse(childNode, computedRootId);
        });
        return node;
    }
}

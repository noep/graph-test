package io.noep.graph;

import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Desc  :
 */
public class NodeService {

    /**
     * node를 순회하면서 id, rootId, parentId를 기록
     * 최상위 node는 rootId, parentId = 0으로 설정한다
     *
     * @param node
     * @param rootId
     * @return
     */
    public Node traverse(Node node, int rootId, IdGenerator idGenerator) {

        node.setId(idGenerator.getId());
        System.out.println(node);

        node.getChildren().forEach(childNode -> {
            int computedRootId = rootId == 0 ? node.getId() : rootId;
            childNode.setParentId(node.getId());
            childNode.setRootId(computedRootId);
            this.traverse(childNode, computedRootId, idGenerator);
        });
        return node;
    }

    /**
     * id에 해당하는 노드 제외
     * @param node
     * @param id
     * @return
     */
    public Node remove(Node node, int id) {

        node.setChildren(
                node.getChildren().stream()
                        .filter(childNode -> childNode.getId() != id)
                        .collect(Collectors.toList()));

        node.getChildren().forEach(childNode -> {
            this.remove(childNode, id);
        });
        return node;
    }

    /**
     * 조건에 해당하는 노드 찾기
     * @param node
     * @param predicate
     * @return
     */
    public Node find(Node node, Predicate<Node> predicate) {

        if (predicate.test(node)) {
            return node;
        }

        for (Node childNode : node.getChildren()) {
            Node result = this.find(childNode, predicate);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}

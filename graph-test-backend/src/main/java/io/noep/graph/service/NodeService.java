package io.noep.graph.service;

import io.noep.graph.utils.IdGenerator;
import io.noep.graph.utils.SequentialIdGenerater;
import io.noep.graph.dto.Node;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Desc  :
 */
public class NodeService {

    /**
     * node를 순회하면서 weight, rootId, parentId를 기록
     * 최상위 node는 rootId, parentId = 0으로 설정한다
     * fixme  최상위 node의 rootId가 0이면 쿼리할 때 불편, 같은 값을 두고, boolean isRoot() 를 추가하는 게 좋지않을까
     *
     * @param node
     * @param rootId
     * @return
     */
    public Node traverse(Node node, int rootId, IdGenerator idGenerator) {

        node.setWeight(idGenerator.getId());
        //System.out.println(node);

        node.getChildren().forEach(childNode -> {
            int computedRootId = rootId == 0 ? node.getWeight() : rootId;
            childNode.setParentId(node.getWeight());
            childNode.setRootId(computedRootId);
            this.traverse(childNode, computedRootId, idGenerator);
        });
        return node;
    }

    /**
     * id에 해당하는 노드 제외
     *
     * @param node
     * @param id
     * @return
     */
    public void remove(Node node, int id) {

        node.setChildren(
                node.getChildren().stream()
                        .filter(childNode -> childNode.getWeight() != id)
                        .collect(Collectors.toList()));

        node.getChildren().forEach(childNode -> {
            this.remove(childNode, id);
        });
    }

    /**
     * 조건에 해당하는 노드 찾기
     *
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

    public void merge(Node node, int parentNodeId, List<Integer> mergeTargets) {

        List<Node> findedTargets = mergeTargets.stream()
                .map(id -> {
                    Node target = this.find(node, targetNode -> targetNode.getWeight() == id);
                    try {
                        return (Node) target.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());

        mergeTargets.forEach(integer -> this.remove(node, integer));

        Node newNode = new Node("new-node");
        newNode.getChildren().addAll(findedTargets);

        this.find(node, targetNode -> targetNode.getWeight() == parentNodeId)
                .getChildren().add(newNode);

        this.traverse(node, 0, new SequentialIdGenerater());
    }
}

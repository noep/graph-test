package io.noep.graph;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class NodeTest {

    private ObjectMapper obejctMapper = new ObjectMapper();

    private NodeService nodeService = new NodeService();

    @Test
    public void createNodeTest() {

        print(this.generate());
    }

    @Test
    public void traverseTest() {
        Node node = this.generate();
        Node traverse = nodeService.traverse(node, 0, new SequentialIdGenerater());
        print(traverse);
    }

    @Test
    public void removeTest() {
        Node node = nodeService.traverse(this.generate(), 0, new SequentialIdGenerater());
        print(node);

        Node deleted = nodeService.remove(node, 5);
        deleted = nodeService.remove(node, 6);
        deleted = nodeService.remove(node, 7);
        deleted = nodeService.remove(node, 8);
        deleted = nodeService.remove(node, 9999);

        print(deleted);
        print(nodeService.traverse(deleted, 0, new SequentialIdGenerater()));
    }

    @Test
    public void find() {
        Node node1 = new Node("1");
        Node node11 = new Node("11");
        Node node12 = new Node("12");
        node1.setChildren(new ArrayList<>(Arrays.asList(node11, node12)));

        Node node = nodeService.traverse(generate(), 0, new SequentialIdGenerater());
        print(node);

        Node node2 = nodeService.find(node, target -> target.getId() == 1);
        print(node2);

        Node node3 = nodeService.find(node, target -> target.getId() == 2);
        print(node3);

        Node node9 = nodeService.find(node, target -> target.getId() == 9);
        print(node9);
    }

    private Node generate() {
        Node node1 = new Node("1");
        Node node11 = new Node("11");
        Node node12 = new Node("12");
        Node node13 = new Node("13");
        Node node14 = new Node("14");
        Node node15 = new Node("15");
        Node node151 = new Node("151");
        Node node1511 = new Node("1511");
        Node node1512 = new Node("1512");
        Node node15121 = new Node("15121");
        Node node15122 = new Node("15122");
        Node node15123 = new Node("15123");
        node1512.setChildren(new ArrayList<>(Arrays.asList(node15121, node15122, node15123)));
        Node node1513 = new Node("1513");
        node151.setChildren(new ArrayList<>(Arrays.asList(node1511, node1512, node1513)));
        Node node152 = new Node("152");
        Node node153 = new Node("153");
        node15.setChildren(new ArrayList<>(Arrays.asList(node151, node152, node153)));
        node1.setChildren(new ArrayList<>(Arrays.asList(node11, node12, node13, node14, node15)));

        return node1;
    }


    private <T> T print(T value) {
        try {
            String result = obejctMapper.writeValueAsString(value);
            System.out.println(result);
            return value;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }
}

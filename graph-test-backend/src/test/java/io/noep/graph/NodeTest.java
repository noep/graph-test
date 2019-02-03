package io.noep.graph;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

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
        Node traverse = nodeService.traverse(node, 0);

        print(traverse);
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
        node1512.setChildren(Arrays.asList(node15121, node15122, node15123));
        Node node1513 = new Node("1513");
        node151.setChildren(Arrays.asList(node1511, node1512, node1513));
        Node node152 = new Node("152");
        Node node153 = new Node("153");
        node15.setChildren(Arrays.asList(node151, node152, node153));
        node1.setChildren(Arrays.asList(node11, node12, node13, node14, node15));

        return node1;
    }


    public <T> T print(T value) {
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

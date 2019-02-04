package io.noep.graph;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.noep.graph.utils.NodeTestUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    private ObjectMapper obejctMapper = new ObjectMapper();

    private NodeService nodeService = new NodeService();

    @Test
    public void createNodeTest() {

        Node node = NodeTestUtils.generate();
        print(node);
        assertThat(node.getName()).isEqualTo("1");
        assertThat(node.getChildren().get(0).getName()).isEqualTo("11");
        assertThat(node.getChildren().get(1).getName()).isEqualTo("12");
        assertThat(node.getChildren().get(2).getName()).isEqualTo("13");
        assertThat(node.getChildren().get(3).getName()).isEqualTo("14");
        assertThat(node.getChildren().get(4).getName()).isEqualTo("15");
        assertThat(node.getChildren().get(4).getChildren().get(0).getName()).isEqualTo("151");
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(0).getName()).isEqualTo("1511");
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getName()).isEqualTo("1512");
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(0).getName()).isEqualTo("15121");
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(1).getName()).isEqualTo("15122");
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(2).getName()).isEqualTo("15123");
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(2).getName()).isEqualTo("1513");
        assertThat(node.getChildren().get(4).getChildren().get(1).getName()).isEqualTo("152");
        assertThat(node.getChildren().get(4).getChildren().get(2).getName()).isEqualTo("153");
    }

    @Test
    public void traverseTest() {
        Node node = NodeTestUtils.generate();
        Node traverse = nodeService.traverse(node, 0, new SequentialIdGenerater());
        
        //assert Ids
        assertThat(node.getId()).isEqualTo(1);
        assertThat(node.getChildren().get(0).getId()).isEqualTo(2);
        assertThat(node.getChildren().get(1).getId()).isEqualTo(3);
        assertThat(node.getChildren().get(2).getId()).isEqualTo(4);
        assertThat(node.getChildren().get(3).getId()).isEqualTo(5);
        assertThat(node.getChildren().get(4).getId()).isEqualTo(6);
        assertThat(node.getChildren().get(4).getChildren().get(0).getId()).isEqualTo(7);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(0).getId()).isEqualTo(8);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getId()).isEqualTo(9);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(0).getId()).isEqualTo(10);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(1).getId()).isEqualTo(11);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(2).getId()).isEqualTo(12);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(2).getId()).isEqualTo(13);
        assertThat(node.getChildren().get(4).getChildren().get(1).getId()).isEqualTo(14);
        assertThat(node.getChildren().get(4).getChildren().get(2).getId()).isEqualTo(15);

        // assert parentIds
        assertThat(node.getParentId()).isEqualTo(0);
        assertThat(node.getChildren().get(0).getParentId()).isEqualTo(1);
        assertThat(node.getChildren().get(1).getParentId()).isEqualTo(1);
        assertThat(node.getChildren().get(2).getParentId()).isEqualTo(1);
        assertThat(node.getChildren().get(3).getParentId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getParentId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getParentId()).isEqualTo(6);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(0).getParentId()).isEqualTo(7);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getParentId()).isEqualTo(7);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(0).getParentId()).isEqualTo(9);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(1).getParentId()).isEqualTo(9);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(2).getParentId()).isEqualTo(9);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(2).getParentId()).isEqualTo(7);
        assertThat(node.getChildren().get(4).getChildren().get(1).getParentId()).isEqualTo(6);
        assertThat(node.getChildren().get(4).getChildren().get(2).getParentId()).isEqualTo(6);

        // assert rootIds
        assertThat(node.getRootId()).isEqualTo(0);
        assertThat(node.getChildren().get(0).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(1).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(2).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(3).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(0).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(0).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(1).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(1).getChildren().get(2).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(0).getChildren().get(2).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(1).getRootId()).isEqualTo(1);
        assertThat(node.getChildren().get(4).getChildren().get(2).getRootId()).isEqualTo(1);
        print(traverse);
    }

    @Test
    public void removeTest() {
        Node node = nodeService.traverse(NodeTestUtils.generate(), 0, new SequentialIdGenerater());
        print(node);

        nodeService.remove(node, 5);
        nodeService.remove(node, 6);
        nodeService.remove(node, 7);
        nodeService.remove(node, 8);
        nodeService.remove(node, 9999);
        print(nodeService.traverse(NodeTestUtils.generate(), 0, new SequentialIdGenerater()));
        print(node);
    }

    @Test
    public void find() {
        Node node = nodeService.traverse(NodeTestUtils.generate(), 0, new SequentialIdGenerater());
        print(node);
        Node node1 = nodeService.find(node, target -> target.getId() == 1);
        Node node2 = nodeService.find(node, target -> target.getId() == 2);
        Node node9 = nodeService.find(node, target -> target.getId() == 9);

        assertThat(node1.getName()).isEqualTo("1");
        assertThat(node1.getChildren().size()).isEqualTo(5);
        assertThat(node2.getName()).isEqualTo("11");
        assertThat(node9.getName()).isEqualTo("1512");
        assertThat(node9.getChildren().size()).isEqualTo(3);
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

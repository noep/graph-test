package io.noep.graph.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Node implements Cloneable {

    @Id
    private String id;

    private int weight;

    private String name;

    private int parentId;

    private int rootId;

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    private List<Node> children = new ArrayList<>();

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return weight == node.weight;
    }

    @Override
    public int hashCode() {
        return weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", rootId=" + rootId +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

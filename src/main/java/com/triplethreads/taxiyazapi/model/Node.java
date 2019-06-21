package com.triplethreads.taxiyazapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Node{

    public Node() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private double latitude;

    private double longitude;

    @ManyToMany(targetEntity = AvailableNode.class, cascade = {CascadeType.ALL})
    private List<AvailableNode> availableNodes;

}

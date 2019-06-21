package com.triplethreads.taxiyazapi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RoutingNodes implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Node node;

    private double price;

    @ManyToOne(cascade = {CascadeType.ALL})
    private RoutingNodes nextNode;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

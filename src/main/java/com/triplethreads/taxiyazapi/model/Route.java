package com.triplethreads.taxiyazapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "route")

public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long routeId;

    private String title;

    @OneToOne
    @JoinColumn(name = "start_node_id")
    private Node start;

    @OneToOne
    @JoinColumn(name = "dest_node_id")
    private Node destination;

    private int hops;

    private double price;

    private double rating;

    @OneToOne(cascade = CascadeType.ALL)
    private RoutingNodes routingNodes;
}

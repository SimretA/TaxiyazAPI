package com.triplethreads.taxiyazapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This class (AvailableNode) maps reachable locations from an existing node with their price
 * Ex from bole to yeka, or bole to 4k...
 * */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "node_connection")
public class AvailableNode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long routeId;

    @OneToOne
    @JoinColumn(name = "dest_node_id")
    private Node destination;

    private double price;
}

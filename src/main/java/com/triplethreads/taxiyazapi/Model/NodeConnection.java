package com.triplethreads.taxiyazapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "node_connection")
public class NodeConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long routeId;



    @OneToOne
    @JoinColumn(name = "start_node_id")
    private Node start;

    @OneToOne
    @JoinColumn(name = "dest_node_id")
    private Node destination;



    private double price;
}

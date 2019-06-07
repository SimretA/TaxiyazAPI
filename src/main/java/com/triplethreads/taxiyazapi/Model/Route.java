package com.triplethreads.taxiyazapi.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Target;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @ManyToMany(targetEntity = Node.class)
    private List<Node> stops = new ArrayList<>();





}

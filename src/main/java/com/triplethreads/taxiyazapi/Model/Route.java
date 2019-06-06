package com.triplethreads.taxiyazapi.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

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

    private int hops;

    private double price;


    private ArrayList<Location> locations;

//    @ManyToOne
//   private Location start;
//
//    @ManyToOne
//   private Location destination;
//
//
//
//
//
//    private double rating;


}

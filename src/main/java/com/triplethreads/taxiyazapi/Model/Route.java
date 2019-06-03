package com.triplethreads.taxiyazapi.Model;


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
    private long Id;

    private String title;

    @ManyToOne
   private Location start;

    @ManyToOne
   private Location destination;

    private int hops;

    private double price;

    private double rating;

}

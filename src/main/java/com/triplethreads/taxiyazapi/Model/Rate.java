package com.triplethreads.taxiyazapi.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Route route;

    private double rate;
}

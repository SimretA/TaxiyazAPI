package com.triplethreads.taxiyazapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String phone_no;

    private String user_name;
}

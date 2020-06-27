package com.trukhachev.branch.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "branches")
public class BranchEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Long id;

    private String title;

    private String address;

    private double lat;

    private double lon;

}

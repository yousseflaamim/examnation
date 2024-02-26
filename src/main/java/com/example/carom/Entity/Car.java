package com.example.carom.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carImage;
    private String carType;
    private BigDecimal rentPrice;
    private String location;
    private boolean availableForBooking;
    private String model;
    private String color;
    private Integer seatingCapacity;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
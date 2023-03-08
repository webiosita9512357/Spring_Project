package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Longitude is mandatory")
    private String longitude;
    @NotBlank(message = "Latitude is mandatory")
    private String latitude;
    private Integer rating;


}

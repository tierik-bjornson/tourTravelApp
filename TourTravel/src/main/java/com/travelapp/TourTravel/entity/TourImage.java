package com.travelapp.TourTravel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name="tourimage")
public class TourImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "tourId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore

    private Tour tour;
}

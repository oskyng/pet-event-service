package com.example.peteventservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String lastName;
    @Column(nullable=false)
    private String rut;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private Integer phone;
    @Column(nullable=false)
    private String address;
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;
}

package com.example.peteventservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime dateEvent;
}

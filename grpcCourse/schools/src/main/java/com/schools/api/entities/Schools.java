package com.schools.api.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Schools")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schools {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private Integer status;
}

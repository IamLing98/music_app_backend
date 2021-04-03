package com.music.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "plan")
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_description")
    private String planDescription;

    @Column(name = "plan_price")
    private Double planPrice;

    @OneToMany(mappedBy = "plan")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "plan"})
    private Set<Account> accounts;

}

package com.music.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music.backend.base.userdetails.AuthProviderEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Data
@Table(name = "account")

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)

    })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "accounts"})
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "plan_id", referencedColumnName = "id")
    })
    @JsonIgnoreProperties({"hibernateLazyInitializer", "accounts"})
    private Plan plan;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProviderEnum provider;

    private String providerId;

}

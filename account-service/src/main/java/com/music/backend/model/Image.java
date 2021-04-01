package com.music.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "image")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "height")
    private Integer height;

    @Column(name = "width")
    private Integer width;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(
                    name = "userId",
                    referencedColumnName = "id")
    })
    @JsonBackReference
    private UserInfo userInfo;

}

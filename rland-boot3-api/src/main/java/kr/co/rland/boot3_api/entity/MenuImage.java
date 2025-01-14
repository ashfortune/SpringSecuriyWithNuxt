package kr.co.rland.boot3_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu_image")
public class MenuImage {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "is_default")
    private Boolean isDefault;

//    @Column(name = "menu_id")
//    private Long menuId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "src")
    private String src;

}
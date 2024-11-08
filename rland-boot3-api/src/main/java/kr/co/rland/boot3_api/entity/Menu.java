package kr.co.rland.boot3_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kor_name")
    private String korName;

    @Column(name = "eng_name")
    private String engName;

    @Column(name = "price")
    private Integer price;

//    @CreationTimestamp
    @ColumnDefault("current_timestamp()")
    @Column(name = "reg_date", insertable = false)
    private Instant regDate;

//    @PrePersist
//    public void prePersist() {
//        this.regDate = Instant.now(); // 엔티티가 persist 될 때 자동으로 현재 시간 설정
//    }

    private Long categoryId;
    private Long regMemberId;

    @OneToMany(mappedBy = "menu")
    @JsonManagedReference
    private List<MenuImage> images;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "reg_member_id")
//    private Member regMember;

}
package kr.co.rland.boot3_api.anonymous.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {

    private Long id;
    private String korName;
    private String engName;
    private Integer price;
    private LocalDateTime regDate;
    private Long categoryId;
    private Long regMemberId;
    private String defaultImage;

//    private List<MenuImage> images;

}

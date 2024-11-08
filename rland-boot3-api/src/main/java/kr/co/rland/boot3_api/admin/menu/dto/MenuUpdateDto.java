package kr.co.rland.boot3_api.admin.menu.dto;

import kr.co.rland.boot3_api.entity.MenuImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuUpdateDto {
    private Long id;
    private String korName;
    private String engName;
    private Integer price;
    private Instant regDate;
    private Long categoryId;
    private Long regMemberId;
    private List<MenuImage> images;

}

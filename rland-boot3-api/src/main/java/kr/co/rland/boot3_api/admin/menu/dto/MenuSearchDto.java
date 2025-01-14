package kr.co.rland.boot3_api.admin.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuSearchDto {

    private Integer page;
    private Integer size;
    private String keyWord;
    private List<Long> categoryIds;

}

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
public class MenuResponseDto {

    private long totalCount;
    private long totalPages;
    private long elementsPerPage;
    private boolean nextPage;
    private boolean previousPage;
    private List<Long> pages;
    private List<MenuListDto> menus;

}

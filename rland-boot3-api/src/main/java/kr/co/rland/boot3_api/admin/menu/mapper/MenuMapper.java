package kr.co.rland.boot3_api.admin.menu.mapper;

import kr.co.rland.boot3_api.admin.menu.dto.MenuListDto;
import kr.co.rland.boot3_api.entity.Menu;

public class MenuMapper {
    public static MenuListDto mapToMenuDto(Menu menu) {
        if (menu == null) {
            return null;
        }

        return MenuListDto.builder()
                .id(menu.getId())
                .korName(menu.getKorName())
                .engName(menu.getEngName())
                .price(menu.getPrice())
                .regDate(menu.getRegDate())
                .regMemberId(menu.getRegMemberId())
                .categoryId(menu.getCategoryId())
                .images(menu.getImages())
                .build();
    }

    public static Menu mapToMenu(MenuListDto menuListDto) {
        if (menuListDto == null) {
            return null;
        }
        return Menu.builder()
                .korName(menuListDto.getKorName())
                .engName(menuListDto.getEngName())
                .price(menuListDto.getPrice())
                .categoryId(menuListDto.getCategoryId())
                .regDate(menuListDto.getRegDate())
                .regMemberId(menuListDto.getRegMemberId())
                .build();
    }
}

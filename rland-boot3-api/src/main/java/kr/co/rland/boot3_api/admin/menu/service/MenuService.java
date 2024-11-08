package kr.co.rland.boot3_api.admin.menu.service;

import kr.co.rland.boot3_api.admin.menu.dto.MenuListDto;
import kr.co.rland.boot3_api.admin.menu.dto.MenuResponseDto;
import kr.co.rland.boot3_api.admin.menu.dto.MenuSearchDto;

import java.util.List;


public interface MenuService {
    MenuResponseDto getList(Integer page, String korName, List<Long> categoryIds);
    MenuResponseDto getList(MenuSearchDto search);
    MenuListDto getById(Long id);
    MenuListDto create(MenuListDto menuListDto);
    MenuListDto update(MenuListDto menuListDto);
    void delete(Long id);
}

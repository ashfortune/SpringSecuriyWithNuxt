package kr.co.rland.boot3_api.admin.menu.controller;

import kr.co.rland.boot3_api.admin.menu.dto.MenuListDto;
import kr.co.rland.boot3_api.admin.menu.dto.MenuResponseDto;
import kr.co.rland.boot3_api.admin.menu.dto.MenuSearchDto;
import kr.co.rland.boot3_api.admin.menu.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminMenuController")
@RequestMapping("admin/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<MenuResponseDto> getList(@ModelAttribute MenuSearchDto searchDto
//            @RequestParam(name = "p",defaultValue = "1") Integer page,
//            @RequestParam(name = "q",required = false) String korName,
//            @RequestParam(name = "cid",required = false) List<Long> categoryIds
    ) {
        if(searchDto.getPage() == null || searchDto.getPage() < 1) {
            searchDto.setPage(1);
        }
        return ResponseEntity.ok(menuService.getList(searchDto));
    }


    @GetMapping("{id}")
    public ResponseEntity<MenuListDto> get(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(menuService.getById(id));
    }


    @PostMapping
    public ResponseEntity<MenuListDto> create(@RequestBody MenuListDto menuListDto){

        System.out.println(menuListDto);

        return ResponseEntity.ok(menuService.create(menuListDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<MenuListDto> update(@PathVariable(name = "id")Long id, @RequestBody MenuListDto menuListDto){
        menuListDto.setId(id);

        return ResponseEntity.ok(menuService.update(menuListDto));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Long id){
        menuService.delete(id);
    }
}

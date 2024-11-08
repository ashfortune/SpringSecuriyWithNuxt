package kr.co.rland.boot3_api.anonymous.menu.controller;

import kr.co.rland.boot3_api.anonymous.menu.dto.MenuDto;
import kr.co.rland.boot3_api.entity.Menu;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menus")
@AllArgsConstructor
public class MenuController {


//    private MenuService menuService;

    private JpaRepository<Menu, Long> menuRepository;

//    @Autowired
//    public MenuController(MenuService menuService) {
//        this.menuService = menuService;
//    }


    @GetMapping
    public List<Menu> getList(
            @RequestParam(name = "p", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "s", defaultValue = "6", required = false) Integer size
    ) {
        return menuRepository.findAll();
    }


    @GetMapping("{id}")
    public ResponseEntity<MenuDto> get(@PathVariable("id") Long id) {

        System.out.println(id);
        MenuDto menuDto = MenuDto.builder().korName("라라라커피").build();

        return new ResponseEntity<>(menuDto, HttpStatus.OK);

//        return menuService.getListById(id);
//        return ResponseEntity.ok()
//                .header("new-line","12")
//                .body(menu);
    }

    @PostMapping()
    public ResponseEntity<MenuDto> create(
            @RequestBody MenuDto menuDto) {

        MenuDto newMenu = MenuDto.builder().korName("방금만든커피").build();
        System.out.println(newMenu);

        return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
    }

    //   1/likes/true
    //   2/menuImage/{imageName}

    @PutMapping("{id}")
    public ResponseEntity<MenuDto> update(
            @PathVariable("id") Long id,
            MenuDto menuDto) {

        menuDto.setKorName("방금수정된커피이름");

        return new ResponseEntity<>(menuDto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") Long id) {

        System.out.println(id + "가 삭제되었습니다");

        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

}

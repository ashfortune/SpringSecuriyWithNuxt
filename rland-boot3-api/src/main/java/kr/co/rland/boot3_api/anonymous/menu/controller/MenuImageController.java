package kr.co.rland.boot3_api.anonymous.menu.controller;


import kr.co.rland.boot3_api.anonymous.menu.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("menus/{menuId}/menu-images")
@AllArgsConstructor
public class MenuImageController {

    private MenuService menuService;

}

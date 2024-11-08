package kr.co.rland.boot3_api.repository;

import kr.co.rland.boot3_api.entity.Menu;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MenuCustomRepository {
    Page<Menu> findAll(String korName, Integer price, Integer pageNums, Integer pageSize);
}

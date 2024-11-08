package kr.co.rland.boot3_api.repository;

import kr.co.rland.boot3_api.entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MenuRepositoryTest {

    @Autowired
    private MenuRepository repository;

//    @Test
//    void imageTest(){
//        List<Menu> menus = repository.findAllWithDefaultImage(
//                "아메리카노",
//        );
//    }

//    @Test
//    void joinTest() {
//
//        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
//        Pageable pageable = PageRequest.of(0, 6, sort);
//        List<Long> cIds = Arrays.asList(1L, 2L, 3L);
//
////        List<Menu> list = repository.findByKorNameContainingAndCategoryIdIn(null,null,pageable);
////        list.forEach(System.out::println);
//
//        List<Menu> list1 = repository.findByCQuery(null,null,pageable);
//        list1.forEach(System.out::println);
//    }


//    @Test
//    void msTest() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
//        Pageable pageable = PageRequest.of(0, 6, sort);
//        List<Long> cIds = Arrays.asList(1L, 2L, 3L);
//        List<Menu> menus = repository.findBykorNameContainingAndPriceGreaterThanAndCategoryIdIn(
//                "아메리카노",2000,cIds,pageable);
//    }

    //    @Test
//    void queryMethodTest() {
    Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
    Pageable pageable = PageRequest.of(0, 6, sort);
    //
//        String korName = "아메리카노";
//        List<Menu> list = repository.findBykorNameIs(korName, pageable);   //필터링작업
//        List<Menu> list2 = repository.findByKorNameLike(korName, pageable);   //필터링작업
//        List<Menu> list3 = repository.findBykorNameContaining(korName, pageable);
//        List<Menu> list4 = repository.findBykorNameContainingAndPriceGreaterThan("아",2000,pageable);
//
//    List<Long> cIds = Arrays.asList(1L, 2L, 3L);
//    List<Menu> list5 = repository.findBykorNameContainingAndPriceGreaterThanAndCategoryIdIn(
//             2000,"아", cIds, pageable
//    );
//     list5.forEach(System.out::println);
//
////        LocalDateTime endDate = LocalDateTime.now();
////        LocalDateTime startDate = endDate.minusDays(30);
//
////        Instant endDate = Instant.now().atZone(ZoneId.systemDefault()).toInstant();
////        Instant startDate =Instant.now().minus(1,ChronoUnit.MONTHS).atZone(ZoneId.systemDefault()).toInstant();
////
////        List<Menu> list6 = repository.findByRegDateBetween(startDate, endDate, pageable);
//
//        List<Menu> list7 = repository.findByQuery("아메",3000,cIds,pageable);
//
//        //필터링작업
////        list.forEach(System.out::println);
////        System.out.println();
////        list2.forEach(System.out::println);
////        System.out.println();
////        list3.forEach(System.out::println);
////        System.out.println();
////        list4.forEach(System.out::println);
////        System.out.println();

////        System.out.println();
////        list6.forEach(System.out::println);
//
//        list7.forEach(System.out::println);
//    }
//
//    @Test
//    void streamTest() {
//
////        List<Menu> list = repository.findAll();
////        List<Menu> menuList =
//                repository.findAll()
//                .stream()
//                .filter(menuPrice -> menuPrice.getPrice() >= 5000)
//                        .forEach(System.out::println);  // 컬렉션에 할당하지않고 바로 출력할수도 있다.
////                .sorted(Comparator.comparing(Menu::getId))
////                .toList();
//
////        System.out.println(menuList);
//    }
//
//    @Test
//    void findAllTest() {
//
//        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
//        Pageable pageable = PageRequest.of(0, 6, sort);
//
//        List<Menu> menus = repository.findAll(pageable).getContent();
//
//        //6개씩 나눴을때 페이지 갯수
//        int count = repository.findAll(pageable).getTotalPages();
//
//        //전체 레코드 갯수
//        long totalRowCount = repository.findAll(pageable).getTotalElements();
//
//        //다음페이지가 있는지
//        boolean hasNextPage = repository.findAll(pageable).hasNext();
//
//        //이전페이지가 있는지
//        boolean hasPreviousPage = repository.findAll(pageable).hasPrevious();
//
//        //현재 페이지에서 가지고있는 레코드수
//        int currentPageRowCount = repository.findAll(pageable).getNumberOfElements();
//
////        List<Menu> menus = repository.findByOrderByRegDateDesc();
//        System.out.println(menus);
//
//    }
}
package kr.co.rland.boot3_api.repository;

import kr.co.rland.boot3_api.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long>,MenuCustomRepository {

    //메뉴 검색 목록(이미 (findAll) 있긴한데 메뉴얼하게는 따로 해야됨 + 정렬,페이징,검색 등등)
    //:규칙 1: 목록을 반환할때는 findBy머시기를 사용 + 정렬 + 필터링

    @Query("from Menu where " +
            " (:korName is null or korName like concat('%', :korName, '%'))" +
            " and (:categoryIds is null or categoryId in (:categoryIds))")
    Page<Menu> findByCQuery(
            String korName, List<Long> categoryIds, Pageable pageable);






//    @Query("select m from Menu m left join fetch MenuImage mi on m.id=mi.menu.id where mi.isDefault=true")
//    List<Menu> findAllWithDefaultImage(String korName,List<Long> categoryId,Pageable pageable);

//    List<Menu> findByKorNameContainingAndCategoryIdIn(String korName, List<Long> categoryId, Pageable pageable);


//    List<Menu> findByKorNameAndCategoryId(String korName, List<Long> categoryId,Pageable pageable);

//    @Query("from Menu where " +
//            " (:korName is null or korName like concat('%', :korName, '%'))" +
//            " and (:price is null or price > :price)" +
//            " and (:categoryId is null or categoryId in(:categoryId))")
//    List<Menu> findByQuery(
//            String korName, Integer price, List<Long> categoryId, Pageable pageable);
//
//    List<Menu> findByOrderByRegDateDesc();    //메서드 이름 자체가 규칙이다.
//
//    List<Menu> findBykorNameIs(String korName, Pageable pageable);
//
//    List<Menu> findByKorNameLike(String korName, Pageable pageable);
//
//    List<Menu> findBykorNameContaining(String korName, Pageable pageable);
//
//    List<Menu> findBykorNameContainingAndPriceGreaterThan(String korName, Integer price, Pageable pageable);
//
//    List<Menu> findBykorNameContainingAndPriceGreaterThanAndCategoryIdIn(
//            String korName, Integer price, List<Long> categoryId, Pageable pageable);
//
//    List<Menu> findByRegDateBetween(
//            Instant startDate, Instant endDate, Pageable pageable);

    //메뉴 상세(jpa - findById)

    //메뉴 등록(jpa - save)

    //메뉴 수정(jpa - save)

    //메뉴 삭제(jpa - delete)

}

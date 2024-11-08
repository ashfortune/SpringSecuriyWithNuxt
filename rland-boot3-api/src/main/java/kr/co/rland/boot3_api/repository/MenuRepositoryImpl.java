package kr.co.rland.boot3_api.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kr.co.rland.boot3_api.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuRepositoryImpl implements MenuCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Menu> findAll(String korName, Integer price, Integer pageNums, Integer pageSize) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
        Root<Menu> root = query.from(Menu.class);
        //select

//        Predicate preKor = cb.like(컬럼명, 값);  단일형태
        List<Predicate> predicates = new ArrayList<Predicate>(); // 배열형태

        //각각 하나의 predicates들이 where절의 조건문 하나에 해당한다.

        if(korName != null && !korName.isEmpty()) {
            predicates.add(cb.like(root.get("korName"), "%" + korName + "%"));
//            Predicate korNamePre = cb.like(root.get("korName"), "%" + korName + "%");
        }
        if(price != null) {
            predicates.add(cb.gt(root.get("price"), price));
//            Predicate pricePre = cb.gt(root.get("price"), price);
        }

        query.where(cb.or(predicates.toArray(new Predicate[0])));
        query.orderBy(cb.desc(root.get("regDate")));


        List<Menu> menus = entityManager.createQuery(query)
                .setFirstResult((pageNums-1) * pageSize) //offset
                .setMaxResults(pageSize) //limit
                .getResultList();

        //-----------------------------------------------------------------------

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Menu> countRoot = countQuery.from(Menu.class);

        List<Predicate> countPredicates = new ArrayList<Predicate>(); // 배열형태

        //각각 하나의 predicates들이 where절의 조건문 하나에 해당한다.

        if(korName != null && !korName.isEmpty()) {
            countPredicates.add(cb.like(countRoot.get("korName"), "%" + korName + "%"));
//            Predicate korNamePre = cb.like(root.get("korName"), "%" + korName + "%");
        }
        if(price != null) {
            countPredicates.add(cb.gt(countRoot.get("price"), price));
//            Predicate pricePre = cb.gt(root.get("price"), price);
        }

        countQuery.select(cb.count(countRoot));
        countQuery.where(countPredicates.toArray(new Predicate[0]));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(menus,PageRequest.of(pageNums-1, pageSize), count);
    }


}

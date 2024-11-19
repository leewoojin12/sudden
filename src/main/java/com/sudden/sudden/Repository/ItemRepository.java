package com.sudden.sudden.Repository;


import com.sudden.sudden.Item.Item;
import com.sudden.sudden.User.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {


    private final EntityManager em;


    public void save(Item item){em.persist(item);}


    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }



    @Transactional
    public void soldout(Long id) {
        Item item = em.find(Item.class , id);
        if (item != null) {
            em.remove(item);
            em.flush();
            System.out.println("아이템 팔렸따 ");
        } else {
            System.out.println("삭제할 아이템을 찾을 수 없습니다.");
        }
    }




    public List<Item> findAll() {

        return em.createQuery("select i from Item i  ", Item.class)
                .getResultList();
    }

    public List<Item> find_by_wp_name(String wp_name) {


        return em.createQuery("select i from Item i where i.wp_name = :wp_name ", Item.class)
                .setParameter(wp_name, wp_name)
                .getResultList();
    }

    public List<Item> findAllitem(Pageable pageable) {
        return em.createQuery("select i from Item i  ", Item.class)
                .getResultList();
    }


}

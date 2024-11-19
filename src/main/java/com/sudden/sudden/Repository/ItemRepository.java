package com.sudden.sudden.Repository;


import com.sudden.sudden.Item.Item;
import com.sudden.sudden.User.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

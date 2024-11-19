package com.sudden.sudden.Repository;


import com.sudden.sudden.Item.My_item;
import com.sudden.sudden.User.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor


public class OrderRepository {

    private final EntityManager em;

    public void save(My_item my_item) {
        em.persist(my_item);
    }


    @Transactional
    public int set_my_sp(Long id, int my_sp) {
        return em.createQuery("UPDATE Member m SET m.my_sp = :my_sp WHERE m.id = :id")
                .setParameter("my_sp", my_sp)
                .setParameter("id", id)
                .executeUpdate();
    }



}

package com.sudden.sudden.Repository;


import com.sudden.sudden.Member;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class MemberRepository {


    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }


    public List<Member> findAll() {

        return em.createQuery("select m from Member m  ", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String nickname) {


        return em.createQuery("select m from Member m where m.nickname = :nickname ", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }


    public boolean existsByNickname(String nickname) {
        Long count = em.createQuery("select count(m) from Member m where m.nickname = :nickname", Long.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
        return count > 0;
    }
}
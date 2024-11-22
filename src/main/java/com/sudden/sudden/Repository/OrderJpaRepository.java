package com.sudden.sudden.Repository;

import com.sudden.sudden.Item.My_item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<My_item , Long> {

    Page<My_item> findAllByMemberId(Long member_id, Pageable pageable);




}

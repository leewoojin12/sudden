package com.sudden.sudden.Repository;

import com.sudden.sudden.Item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WpRepository extends JpaRepository<Item, Long> {




    Page<Item> findAll(Pageable pageable);

}

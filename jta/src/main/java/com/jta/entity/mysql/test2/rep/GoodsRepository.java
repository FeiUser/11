package com.jta.entity.mysql.test2.rep;

import com.jta.entity.mysql.test2.tab.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods,Integer> {
}

package com.jta.entity.mysql.test1.rep;

import com.jta.entity.mysql.test1.tab.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    @Transactional("transactionManagerTest1")
    @Modifying
    @Query("update User u set u.username=?1 where u.id=?2")
    int updateUsername(String username, Integer id);
}

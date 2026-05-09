package org.test.mybatis.mapper.ds1;

import org.test.mybatis.entity.ds1.User;

public interface UserMapper {
    User getUserById(Integer id);

    int insert(User user);
}

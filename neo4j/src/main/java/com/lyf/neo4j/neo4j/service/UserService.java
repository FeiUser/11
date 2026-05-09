package com.lyf.neo4j.neo4j.service;

import com.lyf.neo4j.neo4j.entity.Know;
import com.lyf.neo4j.neo4j.entity.UserNode;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * @Description 添加user节点
     **/
    UserNode create(UserNode userNode);

    /**
     * @Description 根据id删除
     **/
    void deleteById(Long id);

    /**
     * @Description 根据id查询
     **/
    Optional<UserNode> findById(long id);

    /**
     * @Description 获取所有User节点
     **/
    List<UserNode> findAll();

    /**
     * @Description 增加“知道”关系
     **/
    Know addIKnows(UserNode fromNode, UserNode toNode);

    /**
     * @Description 修改单个节点
     **/
    UserNode updateByNode(UserNode userNode);

    /**
     * @Description 删除两个节点的关系
     **/
    void deleteKnowByNodeId(long fromId,long toId);
}

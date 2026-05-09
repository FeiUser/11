package com.lyf.neo4j.neo4j.dao;

import com.lyf.neo4j.neo4j.entity.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserNode, Long> {
    //    @Query("MATCH (n:User) RETURN n ")
//    List<UserNode> getUserNodeList();

//    @Query("create (n:User{name:{name},age:{age},sex:{sex}}) RETURN n ")
//    UserNode addUserNode(@Param("name") String name, @Param("age")int age, @Param("sex") String sex);

    @Query("MATCH (n) WHERE id(n) = :#{#userNode.nodeId} SET n.name = :#{#userNode.name},n.age = :#{#userNode.age},n.sex = :#{#userNode.sex} RETURN n")
    UserNode updateByNode(@Param("userNode") UserNode userNode);
}

package com.lyf.neo4j.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "know")
@Data
public class Know {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private UserNode from;

    @EndNode
    private UserNode to;

    @Override
    public String toString() {
        return "Know{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    public Know(UserNode from, UserNode to) {
        this.from = from;
        this.to = to;
    }

}
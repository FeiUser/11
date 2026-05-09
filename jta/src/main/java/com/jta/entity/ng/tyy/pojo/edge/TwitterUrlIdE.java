package com.jta.entity.ng.tyy.pojo.edge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "twitterUrlId")
public class TwitterUrlIdE {
    private String value;
}

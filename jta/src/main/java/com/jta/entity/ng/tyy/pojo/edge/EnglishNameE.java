package com.jta.entity.ng.tyy.pojo.edge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "englishName")
public class EnglishNameE {
    private String value;
}

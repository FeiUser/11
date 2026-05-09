package com.jta.entity.ng.tyy.pojo.result;

import com.jta.entity.ng.tyy.pojo.edge.EnglishNameE;
import com.jta.entity.ng.tyy.pojo.tag.NPerson;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonEnglishNamePerson {
    private NPerson sourceTag;
    private EnglishNameE n;
    private NPerson distTag;
}

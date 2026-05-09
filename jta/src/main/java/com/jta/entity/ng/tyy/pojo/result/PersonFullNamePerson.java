package com.jta.entity.ng.tyy.pojo.result;


import com.jta.entity.ng.tyy.pojo.edge.FullNameE;
import com.jta.entity.ng.tyy.pojo.tag.NPerson;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonFullNamePerson {
    private NPerson sourceTag;
    private FullNameE n;
    private NPerson distTag;
}
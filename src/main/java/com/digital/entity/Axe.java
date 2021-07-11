package com.digital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "axe")
public class Axe {
    @Id
    private String id;
    private String NameAxe;
    private int degreImportance ;

    @DBRef(lazy = true)
    private List<Question> question;
}

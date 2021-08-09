package com.digital.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "question")
public class Question {

    @Id
    @Field("_id")
    private String id ;
    private String contenu ;
    private String pourcentage;
    private int nbrReponse;
    private Axe axe;
    @DBRef(lazy = true)
    private List<Reponse> reponses;
}

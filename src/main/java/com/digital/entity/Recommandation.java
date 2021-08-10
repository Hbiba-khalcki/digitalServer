package com.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recommandation")
public class Recommandation {

    @Id
    @Field("id")
    @JsonIgnore
    private String id ;

    private String contenu ;
    private int priorite;
    private String reference ;

}

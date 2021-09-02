package com.digital.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reponse")
public class Reponse {
    @Id
    private String id ;
    private int pourcentage ;
    private String contenu;
    private int numReponse;
    private String qstId;

}

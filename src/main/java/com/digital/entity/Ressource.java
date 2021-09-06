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
@Document(collection = "ressource")
public class Ressource {
    @Id
    @Field("_id")
    @JsonIgnore
    private String id ;
    private String nom ;
    private String secteur ;
    private String entite;
    private String lien;
    private String ref_document ;
    private Axe axe;
}

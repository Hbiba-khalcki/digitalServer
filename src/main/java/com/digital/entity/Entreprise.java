package com.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "entreprise")

public class Entreprise {

    @Id
    @Field("_id")
    @JsonIgnore
    private String id ;
    private String nom ;
    private String type ;
    private List<String> secteur_Activité;
    private String siteWeb;
    private String adresse ;
    private String familie_Ent ;
    private Date année_const ;
    private String stade_developpement  ;
    private int nb_employé;
    private String revenu ;
}

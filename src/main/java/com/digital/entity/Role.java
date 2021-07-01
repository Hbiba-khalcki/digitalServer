package com.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Field("_id")
    @JsonIgnore
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
    
}

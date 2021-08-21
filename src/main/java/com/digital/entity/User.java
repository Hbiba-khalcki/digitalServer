package com.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Field("_id")
    //@JsonIgnore
    private String  id;

    @Nullable
    private String nom;
    @Nullable
    private String prenom;
    @Nullable
    private  String roleEntrp;
    
    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String address;

    @NotBlank
    @Size(max = 20)
    private String telephone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(@Nullable String nom, @Nullable String prenom,
                @Nullable String roleEntrp, String username,
                String email, String password, String address, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.roleEntrp = roleEntrp;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
    }

    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public void setRoleEntr(String roleEntrp) {
    }
}

package com.digital.controller;

import com.digital.entity.Ressource;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ressource")
public class RessourceController {
    @Autowired
    private RessourceRepository ressourceRepository;

    // get all ressource
    @GetMapping
    public List<Ressource> getAllRessources(){
        return this.ressourceRepository.findAll();
    }

    // get ressource by id
    @GetMapping("/{id}")
    public Ressource getRessourceById(@PathVariable(value = "id") String ressourceId){
        return this.ressourceRepository.findById(ressourceId)
                .orElseThrow(()-> new ResourceNotFoundException(" ressource not found whith id :" +ressourceId));
    }

    // create ressource
    @PostMapping
    public Ressource createRessource(@RequestBody Ressource ressource) {
        return this.ressourceRepository.save(ressource);
    }

    // update ressource
    @PutMapping("/{id}")
    public Ressource updateRessource(@RequestBody Ressource ressource, @PathVariable("id") String ressourceId) {
        Ressource existingRessource = this.ressourceRepository.findById(ressourceId)
                .orElseThrow(() -> new ResourceNotFoundException("ressource not found with id :" + ressourceId));

        existingRessource.setNom(ressource.getNom());
        existingRessource.setSecteur(ressource.getSecteur());
        existingRessource.setEntite(ressource.getEntite());
        existingRessource.setLien(ressource.getLien());
        existingRessource.setRef_document(ressource.getRef_document());
        return this.ressourceRepository.save(ressource);
    }



    // delete question by id
    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestion(@PathVariable("id") String id) {
        try {
            System.out.println(id);
            this.ressourceRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

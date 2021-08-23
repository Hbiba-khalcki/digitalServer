package com.digital.controller;

import com.digital.entity.Entreprise;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    // get all entreprise
    @GetMapping
    public List<Entreprise> getAllEntreprises(){
        return this.entrepriseRepository.findAll();
    }

    // get entreprise by id
    @GetMapping("/{id}")
    public Entreprise getEntrepriseById(@PathVariable(value = "id") String entrepriseId){
        return this.entrepriseRepository.findById(entrepriseId)
                .orElseThrow(()-> new ResourceNotFoundException(" entreprise not found whith id :" +entrepriseId));
    }

    // create entreprise
    @PostMapping
    public Entreprise createEntreprise(@RequestBody Entreprise entreprise) {
        return this.entrepriseRepository.save(entreprise);
    }

    /* update entreprise
    @PutMapping("/{id}")
    public Entreprise updateEntreprise(@RequestBody Entreprise entreprise, @PathVariable("id") String entrepriseId) {
        Entreprise existingEntreprise = this.entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new ResourceNotFoundException("entreprise not found with id :" + entrepriseId));

        existingEntreprise.setNom(entreprise.getNom());
        existingEntreprise.setType(entreprise.getType());
        existingEntreprise.setSecteur_Activité(entreprise.getSecteur_Activité());
        existingEntreprise.setSiteWeb(entreprise.getSiteWeb());
        existingEntreprise.setAdresse(entreprise.getAdresse());
        existingEntreprise.setFamilie_Ent(entreprise.getFamilie_Ent());
        existingEntreprise.setAnnee_const(entreprise.getAnnee_const());
        existingEntreprise.setStade_developpement(entreprise.getStade_developpement());
        existingEntreprise.setNb_employe(entreprise.getNb_employe());
        existingEntreprise.setRevenu(entreprise.getRevenu());
        return this.entrepriseRepository.save(entreprise);
    }*/

    // delete entreprise by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Entreprise> deleteEntreprise(@PathVariable("id") String entrepriseId) {
        Entreprise existingEntreprise = this.entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new ResourceNotFoundException("entreprise not found with id :" + entrepriseId));
        this.entrepriseRepository.delete(existingEntreprise);
        return ResponseEntity.ok().build();
    }
}

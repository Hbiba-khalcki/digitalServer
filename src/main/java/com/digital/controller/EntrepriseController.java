package com.digital.controller;

import com.digital.entity.Entreprise;
import com.digital.entity.User;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.EntrepriseRepository;
import com.digital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private UserRepository userRepository;



    // get all entreprise
    @GetMapping
    public List<Entreprise> getAllEntreprises(){
        return this.entrepriseRepository.findAll();
    }

    // get entreprise by id
    @GetMapping("/single")
    public Entreprise getEntrepriseByUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User existingUser = this.userRepository.findByUsername(username).get();
        Entreprise existingEntreprise =  new Entreprise();
        Optional<Entreprise> existingEntrepriseo = this.entrepriseRepository.findByUserId(existingUser.getId().toString());

        if(existingEntrepriseo.isPresent()){
            existingEntreprise = existingEntrepriseo.get();
        }
        return existingEntreprise;
    }

    // create entreprise
    @PostMapping
    public Entreprise createEntreprise(@RequestBody Entreprise entreprise) {
        return this.entrepriseRepository.save(entreprise);
    }

    //update entreprise
    @PutMapping("")
    public ResponseEntity<?>  updateEntreprise(@RequestBody Entreprise entreprise) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User existingUser = this.userRepository.findByUsername(username).get();
        Entreprise existingEntreprise =  new Entreprise();
        Optional<Entreprise> existingEntrepriseo = this.entrepriseRepository.findByUserId(existingUser.getId().toString());

        if(existingEntrepriseo.isPresent()){
            existingEntreprise = existingEntrepriseo.get();
        }
        existingEntreprise.setUserId(existingUser.getId().toString());
        if(entreprise.getNom() != null){
            existingEntreprise.setNom(entreprise.getNom());
            existingEntreprise.setSecteur_activite(entreprise.getSecteur_activite());
            existingEntreprise.setSiteWeb(entreprise.getSiteWeb());
            existingEntreprise.setAdresse(entreprise.getAdresse());
        } else {
            existingEntreprise.setFamilie_ent(entreprise.getFamilie_ent());
            existingEntreprise.setAnnee_const(entreprise.getAnnee_const());
            existingEntreprise.setStade_developpement(entreprise.getStade_developpement());
            existingEntreprise.setNb_employe(entreprise.getNb_employe());
            existingEntreprise.setRevenu(entreprise.getRevenu());
            existingEntreprise.setType(entreprise.getType());
        }

        this.entrepriseRepository.save(existingEntreprise);
        return ResponseEntity.ok(entreprise);
    }



    @RequestMapping(value = "getuser", method = RequestMethod.GET)
    public ResponseEntity<?> getuser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User existingUser = this.userRepository.findByUsername(username).get();
        return ResponseEntity.ok(existingUser);

    }
    // delete entreprise by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Entreprise> deleteEntreprise(@PathVariable("id") String entrepriseId) {
        Entreprise existingEntreprise = this.entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new ResourceNotFoundException("entreprise not found with id :" + entrepriseId));
        this.entrepriseRepository.delete(existingEntreprise);
        return ResponseEntity.ok().build();
    }
}

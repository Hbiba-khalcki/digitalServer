package com.digital.controller;

import com.digital.entity.Reponse;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reponse")
public class ReponseController {
    @Autowired
    private ReponseRepository reponseRepository;

    // get all reponse
    @GetMapping
    public List<Reponse> getAllReponses(){
      return this.reponseRepository.findAll();
    }

    // get reponse by id
    @GetMapping("/{id}")
    public Reponse getReponseById(@PathVariable(value = "id") String reponseId){
        return this.reponseRepository.findById(reponseId)
                .orElseThrow(()-> new ResourceNotFoundException(" reponse not found whith id :" +reponseId));
    }
    
    // create Reponse
    @PostMapping
    public Reponse createReponse(@RequestBody Reponse reponse) {
        return this.reponseRepository.save(reponse);
    }

    // update Reponse
    @PutMapping("/{id}")
    public Reponse updateReponse(@RequestBody Reponse reponse, @PathVariable("id") String reponseId) {
        Reponse existingReponse = this.reponseRepository.findById(reponseId)
                .orElseThrow(() -> new ResourceNotFoundException("Reponse not found with id :" + reponseId));
        existingReponse.setOrdrePriorite(reponse.getOrdrePriorite());
        return this.reponseRepository.save(reponse);
    }

    // delete Reponse by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Reponse> deleteReponse(@PathVariable("id") String reponseId) {
        Reponse existingReponse = this.reponseRepository.findById(reponseId)
                .orElseThrow(() -> new ResourceNotFoundException("reponse not found with id :" + reponseId));
        this.reponseRepository.delete(existingReponse);
        return ResponseEntity.ok().build();
    }





}

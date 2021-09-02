package com.digital.controller;

import com.digital.entity.Reponse;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
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

    // get reponse by questionIdd
    @GetMapping("/question/{id}")
    public List<Reponse> getReponseByQstId(@PathVariable(value = "id") String questionId){
        return this.reponseRepository.findByQstId(questionId);
    }
    
    // create Reponse
    @PostMapping
    public HashMap createReponse(@RequestBody Reponse[] reponses) {
        HashMap<String,ArrayList<Reponse>> response = new HashMap();
        ArrayList<Reponse> lr = new ArrayList<Reponse>();
        for (Reponse reponse: reponses) {
            Reponse rep = this.reponseRepository.save(reponse);
            lr.add(rep);
        }
        response.put("save",lr);
        return response;
    }

    // update Reponse
    @PutMapping("/{id}")
    public Reponse updateReponse(@RequestBody Reponse reponse, @PathVariable("id") String reponseId) {
        Reponse existingReponse = this.reponseRepository.findById(reponseId)
                .orElseThrow(() -> new ResourceNotFoundException("Reponse not found with id :" + reponseId));
        existingReponse.setPourcentage(reponse.getPourcentage());
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

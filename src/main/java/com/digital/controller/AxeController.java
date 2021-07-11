package com.digital.controller;

import com.digital.entity.Axe;

import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.AxeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/axe")
public class AxeController  {
    @Autowired AxeRepository axeRepository;

    // get all Axe
    @GetMapping
    public List<Axe> getAllAxes(){
        return this.axeRepository.findAll();
    }

    // get Axe  by id
    @GetMapping("/{id}")
    public Axe getAxeById(@PathVariable(value = "id") String axeId){
        return this.axeRepository.findById(axeId)
                .orElseThrow(()-> new ResourceNotFoundException(" axe not found whith id :" +axeId));
    }



    // create Axe
    @PostMapping
    public Axe createAxe(@RequestBody Axe axe) {
        return this.axeRepository.save(axe);
    }

    // update Axe
    @PutMapping("/{id}")
    public Axe updateAxe(@RequestBody Axe axe, @PathVariable("id") String axeId) {
        Axe existingAxe = this.axeRepository.findById(axeId)
                .orElseThrow(() -> new ResourceNotFoundException("axe not found with id :" + axeId));
        existingAxe.setNameAxe(axe.getNameAxe());
        existingAxe.setDegreImportance(axe.getDegreImportance());
        return this.axeRepository.save(axe);
    }

    // delete Axe  by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Axe> deleteAxe(@PathVariable("id") String axeId) {
        Axe existingReponse = this.axeRepository.findById(axeId)
                .orElseThrow(() -> new ResourceNotFoundException("axe not found with id :" + axeId));
        this.axeRepository.delete(existingReponse);
        return ResponseEntity.ok().build();
    }
}

package com.digital.controller;

import com.digital.entity.Axe;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.AxeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/axe")

public class AxeController {
    @Autowired
    AxeRepository axeRepository;

    // get all Axe
    @GetMapping
    public List<Axe> getAllAxes() {
        return this.axeRepository.findAll();
    }

    // get Axe  by id
    @GetMapping("/{id}")
    public Optional<Axe> getAxeById(@PathVariable(value = "id") String id) {
        return this.axeRepository.findById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            this.axeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
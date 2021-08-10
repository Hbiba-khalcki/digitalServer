package com.digital.controller;

import com.digital.entity.Recommandation;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.RecommandationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/recommandation")
public class RecommandationController {
    @Autowired
    private RecommandationRepository recommandationRepository;

    // get all recommandation
    @GetMapping
    public List<Recommandation> getAllRecommandations(){
        return this.recommandationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recommandation> getTutorialById(@PathVariable("id") String id) {
        Optional<Recommandation> tutorialData = recommandationRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // create recommandation
    @PostMapping
    public Recommandation createRecommandation(@RequestBody Recommandation recommandation) {
        return this.recommandationRepository.save(recommandation);
    }

    // update recommandation
    @PutMapping("/{id}")
    public Recommandation updateRecommandation(@RequestBody Recommandation recommandation, @PathVariable("id") String recommandationId) {
        Recommandation existingRecommandation = this.recommandationRepository.findById(recommandationId)
                .orElseThrow(() -> new ResourceNotFoundException("recommandation not found with id :" + recommandationId));
        existingRecommandation.setContenu(recommandation.getContenu());
        existingRecommandation.setPriorite(recommandation.getPriorite());
        existingRecommandation.setReference(recommandation.getReference());
        return this.recommandationRepository.save(recommandation);
    }

    // delete recommandation by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Recommandation> deleteRecommandation(@PathVariable("id") String recommandationId) {
        Recommandation existingRecommandation = this.recommandationRepository.findById(recommandationId)
                .orElseThrow(() -> new ResourceNotFoundException("recommandation not found with id :" + recommandationId));
        this.recommandationRepository.delete(existingRecommandation);
        return ResponseEntity.ok().build();
    }
}

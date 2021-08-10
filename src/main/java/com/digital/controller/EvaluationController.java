package com.digital.controller;

import com.digital.entity.Evaluation;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationRepository evaluationRepository;

    // get all evaluation
    @GetMapping
    public List<Evaluation> getAllEvaluations(){
        return this.evaluationRepository.findAll();
    }

    // get evaluation by id
    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable(value = "id") String evaluationId){
        return this.evaluationRepository.findById(evaluationId)
                .orElseThrow(()-> new ResourceNotFoundException("evaluation not found whith id :" +evaluationId));
    }

    // create evaluation
    @PostMapping
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation) {
        return this.evaluationRepository.save(evaluation);
    }

    // update evaluation
    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@RequestBody Evaluation evaluation, @PathVariable("id") String evaluationId) {
        Evaluation existingEvaluation = this.evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new ResourceNotFoundException("evaluation not found with id :" + evaluationId));

        existingEvaluation.setNb_Essai(evaluation.getNb_Essai());
        existingEvaluation.setIndex(evaluation.getIndex());
        existingEvaluation.setTaux(evaluation.getTaux());

        return this.evaluationRepository.save(evaluation);
    }

    // delete evaluation by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Evaluation> deleteEvaluation(@PathVariable("id") String evaluationId) {
        Evaluation existingEvaluation = this.evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new ResourceNotFoundException("evaluation not found with id :" + evaluationId));
        this.evaluationRepository.delete(existingEvaluation);
        return ResponseEntity.ok().build();
    }
}

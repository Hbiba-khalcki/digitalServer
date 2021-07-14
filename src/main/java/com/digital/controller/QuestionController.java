package com.digital.controller;

import com.digital.entity.Question;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    // get all question
    @GetMapping
    public List<Question> getAllQuestions(){
        return this.questionRepository.findAll();
    }

    // get question by id
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable(value = "id") String questionId){
        return this.questionRepository.findById(questionId)
                .orElseThrow(()-> new ResourceNotFoundException(" question not found whith id :" +questionId));
    }



    // create question
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return this.questionRepository.save(question);
    }

    // update question
    @PutMapping("/{id}")
    public Question updateQuestion(@RequestBody Question question, @PathVariable("id") String questionId) {
        Question existingQuestion = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("question not found with id :" + questionId));
        existingQuestion.setContenu(question.getContenu());
        existingQuestion.setNumQst(question.getNumQst());
        return this.questionRepository.save(question);
    }

    // delete question by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") String questionId) {
        Question existingQuestion = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("question not found with id :" + questionId));
        this.questionRepository.delete(existingQuestion);
        return ResponseEntity.ok().build();
    }


}

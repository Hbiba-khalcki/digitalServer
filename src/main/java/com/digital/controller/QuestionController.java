package com.digital.controller;

import com.digital.entity.Question;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.QuestionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    // get all question
    @GetMapping
    public List<Question> getAllQuestions(){

        List<Question> questions_list = this.questionRepository.findAll();
        // get degre d'importance de l'axe  et afficher les qst selon le degr deimpo de son axe
        return questions_list.stream()
                .sorted(Comparator.comparing(Question::getdegreimportance))
                .collect(Collectors.toList());
    }
    // get question by id
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable(value = "id") String questionId){
        return this.questionRepository.findById(questionId).get();
    }

    // create question
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return this.questionRepository.save(question);
    }

    // update question
   /* @PutMapping("/{id}")
    public Question updateQuestion(@RequestBody Question question, @PathVariable("id") String questionId) {
        Question existingQuestion = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("question not found with id :" + questionId));
        existingQuestion.setContenu(question.getContenu());
        existingQuestion.setNumQst(question.getNumQst());
        return this.questionRepository.save(question);
    }*/

    // delete question by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") String questionId) {
        Question existingQuestion = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("question not found with id :" + questionId));
        this.questionRepository.delete(existingQuestion);
        return ResponseEntity.ok().build();
    }


}

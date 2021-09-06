package com.digital.controller;

import com.digital.entity.Question;
import com.digital.entity.ReponseClient;
import com.digital.entity.User;
import com.digital.repository.ReponseClientRepository;
import com.digital.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/reponse-client")
public class ReponseClientController {
    @Autowired
    ReponseClientRepository reponseClientRepository;
    @Autowired
    UserRepository userRepository;

    // get reponseClient by userId and questionId
    @GetMapping("/{id}")
    public Object getreponseClient(@PathVariable(value = "id") String questionId){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User existingUser = this.userRepository.findByUsername(username).get();
        Optional<ReponseClient> existingReponseClientO = this.reponseClientRepository.findByIdUserAndIdQuestion(existingUser.getId().toString(),questionId);
        if(existingReponseClientO.isPresent()){
            return(existingReponseClientO.get());
        } else{
            return null;
        }
    }

    // create reponseClient for user-question-reponse and update if exist

    @PostMapping("/{id}")
    public ReponseClient createClientRepository(@PathVariable(value = "id") String questionId,
                                                @RequestBody ReponseClient reponseClient) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User existingUser = this.userRepository.findByUsername(username).get();
        Optional<ReponseClient> existingReponseClientO = this.reponseClientRepository.findByIdUserAndIdQuestion(existingUser.getId().toString(),questionId);
        if(existingReponseClientO.isPresent()){
            ReponseClient existingReponseClient = existingReponseClientO.get();
            existingReponseClient.setIdReponse(reponseClient.getIdReponse());
            return this.reponseClientRepository.save(existingReponseClient);
        }
        else{
            reponseClient.setIdReponse(reponseClient.getIdReponse());
            reponseClient.setIdQuestion(questionId);
            reponseClient.setIdUser(existingUser.getId().toString());
            return this.reponseClientRepository.save(reponseClient);
        }
    }
}

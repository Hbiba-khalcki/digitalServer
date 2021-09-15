package com.digital.controller;
import com.digital.entity.Question;
import com.digital.entity.Reponse;
import com.digital.entity.ReponseClient;
import com.digital.entity.User;
import com.digital.exception.ResourceNotFoundException;
import com.digital.repository.QuestionRepository;
import com.digital.repository.ReponseClientRepository;
import com.digital.repository.ReponseRepository;
import com.digital.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReponseClientRepository reponseClientRepository;
    @Autowired
    private ReponseRepository reponseRepository;
    @Autowired
    private QuestionRepository questionRepository;

    // get all user
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
   /*a=(prcqst*prcrep)
     somme de (prqst/100 * prrep/100) *100*/

    @GetMapping("getoverall")
    public HashMap<String,HashMap> getoverall(){
        HashMap<String,HashMap> result = new HashMap<>();
        HashMap<String,Double> totals = new HashMap<>();
        // get current user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User existingUser = this.userRepository.findByUsername(username).get();
        // get reponseClient of current user
        List<ReponseClient> lrc = this.reponseClientRepository.findByIdUser(existingUser.getId().toString());
        for (ReponseClient rc:lrc) {
            try {
                // get question of reponseclient
                String questionId = rc.getIdQuestion();
                Question question = this.questionRepository.getById(new ObjectId(questionId));
                // get reponse of reponseclient
                String reponseId = rc.getIdReponse();
                Reponse reponse = this.reponseRepository.getById(new ObjectId(reponseId));
                // get axename to map result from qst
                String axeId = question.getAxe().getId().toString();
                // get percentage of question and reponse
                double rep_percent = (double) reponse.getPourcentage();
                double qst_percent = (double) Integer.parseInt(question.getPourcentage());
                // edit or add the reponseclient to the calculations mapped by axe name
                if(totals.containsKey(axeId)){
                    Double newval = (totals.get(axeId)/100 + ((rep_percent/100) * (qst_percent/100)))*100;
                    totals.remove(axeId);
                    totals.put(axeId,newval);
                }else{
                    Double val = (rep_percent/100) * (qst_percent/100) * 100;
                    totals.put(axeId, val );
                }
            }catch (Exception e){
                HashMap<String,String> err = new HashMap<>();
                err.put("err",e.getMessage());
                result.put("err",err);
                return result;
            }
        }
        result.put("totals",totals);
        return result;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
        try {
            this.userRepository.deleteById(new ObjectId(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

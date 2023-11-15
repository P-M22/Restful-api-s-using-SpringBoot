package com.javaguides.springBoot.controller;

import com.javaguides.springBoot.exception.UserNotFoundException;
import com.javaguides.springBoot.model.Request;
import com.javaguides.springBoot.repository.DataRepository;
import com.javaguides.springBoot.services.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserData userData;

    @Autowired private final DataRepository dataRepository;

    @Autowired public UserController(UserData userData, DataRepository dataRepository) {
        this.userData = userData;
        this.dataRepository = dataRepository;
    }
    @GetMapping("/info")
    public ResponseEntity<List<Request>> getAllUser(){

        return ResponseEntity.ok(this.dataRepository.findAll());
    }
    @GetMapping("/info/{id}")
    public ResponseEntity<Optional<Request>> getUserById(@PathVariable String id){
        Optional<Request> request=this.dataRepository.findById(id);
        if(request.isPresent()){
            return ResponseEntity.status(200).body(request);
        }else{
            throw new UserNotFoundException("customer not found with Id "+ id);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Request> createUser(@RequestBody Request request){
        return ResponseEntity.status(201).body(this.dataRepository.save(request));
    }
    @PutMapping("/update")
    public Request updateUser(@RequestBody Request request){
        return userData.editUser(request);
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam String id){
        Optional<Request> request=this.dataRepository.findById(id);
        if(request.isPresent()){
            this.dataRepository.deleteById(id);
            return ResponseEntity.ok("User is deleted succesfully");
        }else{
            return ResponseEntity.ok("User Not Found");
        }
    }

}

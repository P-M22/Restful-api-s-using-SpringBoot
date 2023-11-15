package com.javaguides.springBoot.services;

import com.javaguides.springBoot.exception.UserNotFoundException;
import com.javaguides.springBoot.model.Request;
import com.javaguides.springBoot.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserData {

    @Autowired private final DataRepository dataRepository;

    public UserData(DataRepository dataRepository){
        this.dataRepository=dataRepository;
    }

    public Request editUser(Request request){

        Optional<Request> existingUser=this.dataRepository.findById(request.getId());

        if(existingUser.isPresent()){
            Request user=existingUser.get();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            this.dataRepository.save(user);
            return user;
        }else{
            throw new UserNotFoundException("Customer not found with "+ request.getId());
        }
    }
}

package com.apiexamples.examples.controller;

import com.apiexamples.examples.entity.Registration;
import com.apiexamples.examples.payload.RegistrationDto;
import com.apiexamples.examples.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {


    @Autowired
    private RegistrationService registrationService;

    //http://localhost:8080/api/v1/registration
    @PostMapping
    public ResponseEntity<?> addRegistration(
            @Valid  @RequestBody RegistrationDto registrationDto,
            BindingResult result){

        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/registration?id=
    @DeleteMapping
    public ResponseEntity<String> deleteRegistrationById(@RequestParam long id){
        registrationService.deleteRegistrationById(id);
        return new ResponseEntity<>("Registration deleted",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateRegistration(
            @RequestParam long id,
            @Valid @RequestBody RegistrationDto registrationDto,
            BindingResult result
          ){
        if(result.hasErrors()) {
         return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
            }
       RegistrationDto dto=registrationService.updateRegistration(id,registrationDto);
     return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/registration?pageNo=0&pageSize=5&sortBy=email&sortDir=asc

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue= "5",required = false)int pageSize,
            @RequestParam(name="sortBy",defaultValue="name",required = false)String sortBy,
            @RequestParam(name="sortDir",defaultValue="name",required = false)String sortDir
    ) {

        List<RegistrationDto> dtos = registrationService.getAllRegistrations(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    @GetMapping("/byid")
    public ResponseEntity<RegistrationDto> getRegistratioById(
            @RequestParam  long id
    ){
      RegistrationDto dto=registrationService.getRegistratioById( id);
      return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}

package com.apiexamples.examples.service;

import com.apiexamples.examples.payload.RegistrationDto;

import java.util.List;

public interface RegistrationService {
    public RegistrationDto createRegistration(RegistrationDto registrationDto);


   

    RegistrationDto updateRegistration(long id, RegistrationDto registrationDto);

    List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir);

    RegistrationDto getRegistratioById(long id);

    void deleteRegistrationById(long id);
}

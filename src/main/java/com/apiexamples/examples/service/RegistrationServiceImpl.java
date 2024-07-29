package com.apiexamples.examples.service;

import com.apiexamples.examples.entity.Registration;
import com.apiexamples.examples.exception.ResourceNotFound;
import com.apiexamples.examples.payload.RegistrationDto;
import com.apiexamples.examples.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

@Autowired
    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }
    public RegistrationServiceImpl(){

    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {

       Registration registration= mapToEntity(registrationDto);

        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        dto.setMessage("Registration saved");
        return dto;
    }

    @Override
    public void deleteRegistrationById(long id) {
        registrationRepository.findById(id);


    }

    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration registration = opReg.get();

        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        Registration savedEntity=registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(registration);
        return dto;
    }

    @Override
    //using Stream apis
    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
       // RegistrationServiceImpl r=new RegistrationServiceImpl();
       // List<Registration> registrations = registrationRepository.findAll();
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC,sortBy) : Sort.by(Sort.Direction.DESC,sortBy);


        Pageable  Pageable=PageRequest.of(pageNo,pageSize, sort);
        Page<Registration> all = registrationRepository.findAll(Pageable);
        List<Registration> registrations = all.getContent();
        List<RegistrationDto> registrationDtos = registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        System.out.println(all.getTotalPages());
        System.out.println(all.isLast());
        System.out.println(all.isFirst());
        System.out.println(Pageable.getPageSize());
        System.out.println(Pageable.getPageNumber());

        return registrationDtos;
    }

    @Override
    public RegistrationDto getRegistratioById(long id) {
        Registration registration= registrationRepository.findById(id).orElseThrow(
                 ()->new ResourceNotFound("Registration not found with id: " + id)
         );
//        Registration registration = opReg.get();
        RegistrationDto registrationDto = mapToDto(registration);
        return registrationDto;
    }

    Registration mapToEntity(RegistrationDto dto){
       Registration entity=new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;
    }
     RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto=new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }
}

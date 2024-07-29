package com.apiexamples.examples.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data

public class RegistrationDto {

    private Long id;


@Size(min=2,message="Should be more than two characters")
    private String name;

@Email(message="Invalid Email Format")
    private String email;

@Size(min=10,max=10,message="Should be  10 Digits")
    private String mobile;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
// private int pageNo{
//}
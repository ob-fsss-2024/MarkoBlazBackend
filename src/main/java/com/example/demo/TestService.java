package com.example.demo;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TestService {

    //to smo naredili svoje v application.properties
    private final String something;
    public TestService(@Value("${something.something}") String something){ //TO read from the config into moremo dati da je java vesela
        this.something = something;
    }





    public String helloWorld(String name, String id){ //MORES DAT REQUEST PARAM, into napišeš v URL
        //query param mores uporabiti ?, medtem ko pa pathvariable samo dodaš v URL
        //http://localhost:8080/hello/1?name=Marko
        return "Hello " + name + "! " + id + " " + something;
    }
}

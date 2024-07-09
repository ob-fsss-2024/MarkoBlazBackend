package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController //web endpoints
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/hello/{id}") //create a get endpoint
    //http://localhost:8080/hello?name=Marko
    public String helloWorld(
            @RequestParam String name,
            @PathVariable String id){ //MORES DAT REQUEST PARAM, into napišeš v URL
        //query param mores uporabiti ?, medtem ko pa pathvariable samo dodaš v URL
        //http://localhost:8080/hello/1?name=Marko
        //return "Hello " + name + "! " + id;
        return testService.helloWorld(name, id); //to je novo, logika je v TestService, za bolj mantainable code to je DI
    }



    @PostMapping("/hello")
    public String helloWorldPost(@RequestBody User user){ //POST request -post body
        return "Hello " + user.name() + "! " + user.age();
    }

}

record User(String name, int age){}
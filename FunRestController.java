package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FunRestController {
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/teaminfo")
    public  String getTeamInfo(){
        return  "Coach: "+ coachName + ", Team name: "+teamName;
    }
    //expose
    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }
    @GetMapping("/workout")
    public String getDailyWorkout()
    {
        return "run a hard ski";
    }
   @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is great day";
   }
}

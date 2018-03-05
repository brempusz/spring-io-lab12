package io.spring.lab.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
class GreetingController {

    public static final String GREETINGS_PATH = "/greetings/{name}";

    @Value("${greeting.template:Hello %s}")
    String template;

    @GetMapping(GREETINGS_PATH)
    Greeting greet(@PathVariable("name") String name) {
        return new Greeting(template, name);
    }
}

@lombok.Value
class Greeting {

    private String message;

    Greeting(String template, Object... args) {
        this.message = String.format(template, args);
    }

    @JsonCreator
    static Greeting of(@JsonProperty("message") String message) {
        return new Greeting(message);
    }
}

package com.edu.usermsclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserClientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClientResource.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users-client/{id}")
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public Object getUsersFromUserms(@PathVariable String id) {
        LOGGER.info("About to call userms");
        return restTemplate.getForObject("http://userms/users/" + id, Object.class);
    }

    public Object fallbackMethod() {
    	return Arrays.osList("A", "B", "C");
    }
}
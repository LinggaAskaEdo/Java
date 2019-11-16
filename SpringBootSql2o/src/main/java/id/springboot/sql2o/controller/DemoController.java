package id.springboot.sql2o.controller;

import id.springboot.sql2o.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
    private DemoService service;

    @Autowired
    public DemoController(DemoService service)
    {
        this.service = service;
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> retrieveUser(@PathVariable String id)
    {
        return service.getUser(id);
    }

    @PutMapping(value = "/user/{cuid}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable String cuid, @PathVariable String id)
    {
        return service.updateUser(cuid, id);
    }
}
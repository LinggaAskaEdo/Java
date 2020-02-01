package com.spring.webflux.controller;

import com.spring.webflux.dto.EmployeeDto;
import com.spring.webflux.model.Employee;
import com.spring.webflux.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;

@RestController
public class EmployeeController
{
    private ModelMapper modelMapper;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(ModelMapper modelMapper, EmployeeService employeeService)
    {
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }

    @PostMapping(value = { "/create", "/" })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody EmployeeDto employeeDto)
    {
        Employee employee = convertToEntity(employeeDto);

        employeeService.create(employee);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mono<EmployeeDto>> findById(@PathVariable("id") Integer id)
    {
        Mono<Employee> employeeMono = employeeService.findById(id);

        HttpStatus status = employeeMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        Mono<EmployeeDto> employeeDto = convertToDto(employeeMono);

        return new ResponseEntity<>(employeeDto, status);
    }

    @GetMapping(value = "/name/{name}")
    public Flux<EmployeeDto> findByName(@PathVariable("name") String name)
    {
        Flux<Employee> employeeFlux = employeeService.findByName(name);

        return convertToDto(employeeFlux);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeDto> findAll()
    {
        Flux<Employee> employeeFlux = employeeService.findAll();

        return convertToDto(employeeFlux);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<EmployeeDto> update(@RequestBody EmployeeDto employeeDto)
    {
        Employee employee = convertToEntity(employeeDto);

        Mono<Employee> employeeMono = employeeService.update(employee);

        return convertToDto(employeeMono);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id)
    {
        employeeService.delete(id).subscribe();
    }

    private Mono<EmployeeDto> convertToDto(Mono<Employee> employeeMono)
    {
        return modelMapper.map(employeeMono, (Type) EmployeeDto.class);
    }

    private Flux<EmployeeDto> convertToDto(Flux<Employee> employeeFlux)
    {
        return modelMapper.map(employeeFlux, (Type) EmployeeDto.class);
    }

    private Employee convertToEntity(EmployeeDto employeeDto)
    {
        return modelMapper.map(employeeDto, Employee.class);
    }
}
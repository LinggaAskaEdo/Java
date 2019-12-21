package com.lingga.java.config;

import com.lingga.java.model.Student;
import com.lingga.java.model.User;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

@Component
public class Router extends RouteBuilder
{
    private static final String APPLICATION_JSON = "application/json";
    private Environment env;

    @Autowired
    public Router(Environment env)
    {
        this.env = env;
    }

    @Override
    public void configure()
    {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .port(env.getProperty("server.port", "8080"))

                // turn on swagger api-doc
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "User & Student API")
                .apiProperty("api.version", "1.0.0");

        /*Student API*/
        rest("/student")
                .description("Student REST service")
                .produces(APPLICATION_JSON)

                .get("/hello/{name}").description("Hello Student")
                    .outType(String.class)
                    .route().transform().simple("Hello ${header.name}, Welcome to JavaOutOfBounds.com")
                    .endRest()

                .get("/records/{name}").description("Get Name of Student")
                    .outType(Student.class)
                    .to("direct:records");

        from("direct:records")
                .process(new Processor()
                {
                    final AtomicLong counter = new AtomicLong();

                    @Override
                    public void process(Exchange exchange)
                    {
                        final String name = exchange.getIn().getHeader("name", String.class);
                        exchange.getIn().setBody(new Student(counter.incrementAndGet(), name, "Camel + SpringBoot"));
                    }
                });

        /*User API*/
        rest("/users").description("User REST service")
                .consumes(APPLICATION_JSON)
                .produces(APPLICATION_JSON)

                .get().description("Find all users")
                    .outType(User[].class)
                    .responseMessage().code(200).message("All users successfully returned").endResponseMessage()
                    .to("bean:userService?method=findUsers")

                .get("/{id}").description("Find user by ID")
                    .outType(User.class)
                    .param().name("id").type(path).description("The ID of the user").dataType("integer").endParam()
                    .responseMessage().code(200).message("User successfully returned").endResponseMessage()
                    .to("bean:userService?method=findUser(${header.id})")

                .put("/{id}").description("Update a user")
                    .type(User.class)
                    .param().name("id").type(path).description("The ID of the user to update").dataType("integer").endParam()
                    .param().name("body").type(body).description("The user to update").endParam()
                    .responseMessage().code(204).message("User successfully updated").endResponseMessage()
                    .to("direct:update-user");

        from("direct:update-user")
                .to("bean:userService?method=updateUser")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
                .setBody(constant(""));
    }
}
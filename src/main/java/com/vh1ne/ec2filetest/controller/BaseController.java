package com.vh1ne.ec2filetest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController  public class BaseController {
    @Value("${com.vh1ne.filepath}")
    private String windows_filepath;
    @Value("${com.vh1ne.linux.filepath}")
    private String linux_filepath;
    @GetMapping("/hello")
    public String hello()
    {
        System.out.println("filepath "+ linux_filepath );
        try (Stream<String> stream = Files.lines(Paths.get(windows_filepath))) {

            stream.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello " ;
    }
}

package com.vh1ne.ec2filetest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController  public class BaseController {
    @Value("classpath:test1.json")
    private Resource windows_filepath;
    @Value("${com.vh1ne.linux.filepath}")
    private String linux_filepath;
    @GetMapping("/file")
    public String file() throws IOException {
        var file=Paths.get(linux_filepath);
        System.out.println("filepath "+ file );
        try (var stream = Files.lines(file)) {

            System.out.println(stream.toList().size());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello " ;
    }
    @GetMapping("/hello")
    public String hello()
    {


        return "Hello " ;
    }
    @GetMapping()
    public String base()
    {


        return "Hello " ;
    }
}

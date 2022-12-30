package com.vh1ne.ec2filetest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@RestController  public class BaseController {
    @Value("classpath:test1.json")
    private Resource windows_filepath;
    @Value("${com.vh1ne.linux.filepath}")
    private String linux_filepath;

    @Value("${com.vh1ne.msg}")
    private String profile;
    @GetMapping("/file")
    public String file()  {

        var file=Paths.get(linux_filepath);

        System.out.println("filepath "+ file );
        try (var stream = Files.lines(file)) {

            System.out.println(stream.toList().size());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "Hello " ;
    }
    @GetMapping("/hello")
    public String hello()
    {

        System.out.println("active profile : " + profile);
        return "Hello " ;
    }
    @GetMapping()
    public String base()
    {


        return "Hello " ;
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void sheduled()
    {
        System.out.println( "current time : " + LocalDateTime.now());
    }
}

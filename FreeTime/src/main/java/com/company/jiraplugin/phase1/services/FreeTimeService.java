package com.company.jiraplugin.phase1.services;


import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FreeTimeService {

    final ExampleService exampleService;

    @Inject
    public FreeTimeService(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    public void helloWorld() {
        System.out.println("Hello World");
        exampleService.helloWorld();
    }
}

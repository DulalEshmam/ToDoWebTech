package de.htwberlin.webtech.todoApp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoController {

    @GetMapping(path = "/")
    public ModelAndView showHelloWorld() {
        return new ModelAndView("helloWorld");
    }
}

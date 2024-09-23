package pro.sky.skyprospringtyulkovcw2.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.skyprospringtyulkovcw2.model.Question;
import pro.sky.skyprospringtyulkovcw2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping(path = "/get/{amount}")
    public Collection<Question> getQuestion(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
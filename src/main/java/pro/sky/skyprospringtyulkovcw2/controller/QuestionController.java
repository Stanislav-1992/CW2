package pro.sky.skyprospringtyulkovcw2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyprospringtyulkovcw2.model.Question;
import pro.sky.skyprospringtyulkovcw2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "add")
    public Question add(@RequestParam("Question") String question,
                        @RequestParam("Answer") String answer) {
        return questionService.add(new Question(question,answer));
    }

    @GetMapping(path = "remove")
    public Question remove(@RequestParam("Question") String question,
                           @RequestParam("Answer") String answer) {
        return questionService.remove(new Question(question,answer));
    }

    @GetMapping()
    public Collection<Question> getQuestion() {
        return questionService.getAll();
    }
}

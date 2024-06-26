package pro.sky.skyprospringtyulkovcw2.service.Impl;

import pro.sky.skyprospringtyulkovcw2.exeption.IncorrectAmountException;
import pro.sky.skyprospringtyulkovcw2.model.Question;
import pro.sky.skyprospringtyulkovcw2.service.ExaminerService;
import pro.sky.skyprospringtyulkovcw2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount || amount <= 0) {
            throw new IncorrectAmountException();
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }

        return questions;
    }
}

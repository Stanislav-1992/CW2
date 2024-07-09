package pro.sky.skyprospringtyulkovcw2.service.Impl;

import org.springframework.stereotype.Service;
import pro.sky.skyprospringtyulkovcw2.exeption.QuestionAlreadyAddedExeption;
import pro.sky.skyprospringtyulkovcw2.exeption.NoQuestionExeption;
import pro.sky.skyprospringtyulkovcw2.exeption.QuestionNotFoundExeption;
import pro.sky.skyprospringtyulkovcw2.model.Question;
import pro.sky.skyprospringtyulkovcw2.service.QuestionService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyAddedExeption();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundExeption();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new NoQuestionExeption();
        }
        int randomQ = ThreadLocalRandom.current().nextInt();
        return questions.stream()
                .skip(randomQ)
                .findFirst()
                .orElseThrow(QuestionNotFoundExeption::new);
    }
}

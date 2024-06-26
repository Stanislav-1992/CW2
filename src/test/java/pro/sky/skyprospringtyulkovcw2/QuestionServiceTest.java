package pro.sky.skyprospringtyulkovcw2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.skyprospringtyulkovcw2.exeption.NoQuestionExeption;
import pro.sky.skyprospringtyulkovcw2.exeption.QuestionAlreadyAddedExeption;
import pro.sky.skyprospringtyulkovcw2.exeption.QuestionNotFoundExeption;
import pro.sky.skyprospringtyulkovcw2.model.Question;
import pro.sky.skyprospringtyulkovcw2.service.Impl.QuestionServiceImpl;
import pro.sky.skyprospringtyulkovcw2.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionServiceTest {

    private final QuestionService questionService = new QuestionServiceImpl();

    private final List<Question> questions = List.of(
            new Question("Вопрос 1", "Ответ 1"),
            new Question("Вопрос 2", "Ответ 2"),
            new Question("Вопрос 3", "Ответ 3"),
            new Question("Вопрос 4", "Ответ 4"),
            new Question("Вопрос 5", "Ответ 5")
    );

    @BeforeEach
    public void before() {
        questions.forEach(questionService::remove);
    }

    @AfterEach
    public void after() {
        new ArrayList<>(questionService.getAll())
                .forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        int be = questionService.getAll().size();

        Question expected = new Question("вопрос 1", "ответ 1");
        assertThat(questionService.getAll()).doesNotContain(expected);

        Question actual = questionService.add(new Question("вопрос 1", "ответ 1"));
        assertThat(questionService.getAll()).hasSize(be + 1);
        assertThat(questionService.getAll()).contains(expected);
    }

    @Test
    public void addNegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedExeption.class)
                .isThrownBy(() -> questionService.add("вопрос 1", "ответ 1"));
    }

    @Test
    public void removeTest() {
        int be = questionService.getAll().size();

        Question expected = new Question("вопрос 1", "ответ 1");
        assertThat(questionService.getAll()).contains(expected);

        Question actual = questionService.remove(new Question("вопрос 1", "ответ 1"));
        assertThat(questionService.getAll()).hasSize(be - 1);
        assertThat(questionService.getAll()).doesNotContain(expected);
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundExeption.class)
                .isThrownBy(() -> questionService.remove(new Question("вопрос 1", "ответ 1")));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());

    }

    @Test
    public void getRandomQuestionNegativeTest() {
        after();

        assertThatExceptionOfType(NoQuestionExeption.class)
                .isThrownBy(questionService::getRandomQuestion);
    }
}



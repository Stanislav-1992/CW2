package pro.sky.skyprospringtyulkovcw2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyprospringtyulkovcw2.exeption.IncorrectAmountException;
import pro.sky.skyprospringtyulkovcw2.model.Question;
import pro.sky.skyprospringtyulkovcw2.service.Impl.ExaminerServiceImpl;
import pro.sky.skyprospringtyulkovcw2.service.QuestionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> questions = List.of(
            new Question("Вопрос 1", "Ответ 1"),
            new Question("Вопрос 2", "Ответ 2"),
            new Question("Вопрос 3", "Ответ 3"),
            new Question("Вопрос 4", "Ответ 4"),
            new Question("Вопрос 5", "Ответ 5")
    );

    @BeforeEach
    public void before() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    public void getQuestionTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 5", "Ответ 5"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"));

        assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(
                new Question("Вопрос 5", "Ответ 5"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2")
        );

    }

    @Test
    public void getQuestionNegativeTest() {

        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(7));

        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(-7));
    }
}
package pro.sky.skyprospringtyulkovcw2.service;

import pro.sky.skyprospringtyulkovcw2.model.Question;
import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}

package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@Validated
public class ControllerWebQuiz {
    @Autowired
    ServiceQuiz serviceQuiz;

    @PostMapping("api/quizzes")
    public QuizDTO createQuiz(@Valid @RequestBody Quiz quiz) {
        return serviceQuiz.saveQuiz(quiz);
    }

    @GetMapping("api/quizzes/{id}")
    public QuizDTO getQuizByID(@PathVariable int id) {
        if (!serviceQuiz.containsId(id)) {
            throw new QuizNotFoundException();
        }
        return serviceQuiz.getQuiz(id);
    }

    @GetMapping("api/quizzes")
    public List<QuizDTO> getAllQuizzes() {
        return serviceQuiz.getAllQuizzes();
    }

    @PostMapping("api/quizzes/{id}/solve")
    public AnswerCheck checkQuiz(@PathVariable int id, @RequestParam int[] answer) {
        if (!serviceQuiz.containsId(id)) {
            throw new QuizNotFoundException();
        }
        return serviceQuiz.checkQuiz(id, answer);
    }
}

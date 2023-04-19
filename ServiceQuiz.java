package engine;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ServiceQuiz {

    private static Integer counter;
    private Map<Integer, Quiz> map;

    ServiceQuiz() {
        counter = 0;
        map = new ConcurrentHashMap<>(new LinkedHashMap<>());
    }

    public QuizDTO saveQuiz(Quiz quiz) {
        quiz.setId(++counter);
        map.put(quiz.getId(), quiz);
        return Quiz.convertQuizToDTO(quiz);
    }

    public boolean containsId(int id) {
        return map.containsKey(id);
    }

    public QuizDTO getQuiz(int id) {
        return Quiz.convertQuizToDTO(map.get(id));
    }
    public List<QuizDTO> getAllQuizzes() {
        return map.values()
                .stream()
                .map(Quiz::convertQuizToDTO)
                .collect(Collectors.toList());
    }

    public AnswerCheck checkQuiz(int id, int[] answers) {
        Quiz quiz = map.get(id);
        boolean isCorrect = true;

        int[] checkArray = quiz.getAnswer();
        if (checkArray.length == 0 && answers.length == 0) {
            return new AnswerCheck(isCorrect);
        }
        if (checkArray.length != answers.length) {
            isCorrect = false;
        } else {
            for (int check : checkArray) {
                int count = 0;
                for (int answer: answers ) {
                    if (check == answer) {
                        count++;
                    }
                }
                if (count < 1) {
                    isCorrect = false;
                    break;
                }
            }
        }
        return new AnswerCheck(isCorrect);
    }

}

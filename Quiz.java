package engine;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @JsonIgnore
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @Size(min = 2)
    private String[] options;

    private int[] answer;


    public static QuizDTO convertQuizToDTO(Quiz quiz) {
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
    }


}

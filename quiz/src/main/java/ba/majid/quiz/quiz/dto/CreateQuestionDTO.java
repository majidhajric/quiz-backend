package ba.majid.quiz.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateQuestionDTO {

    @NotEmpty
    @Min(10)
    private String question;

    @NotNull
    @Size(max = 10)
    private Set<String> choices;

    @NotNull
    @Size(min=1, max=10)
    private Set<String> answers;

}

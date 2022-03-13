package ba.majid.quiz.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateQuizDTO {

    @NotEmpty
    @Min(10)
    private String name;

    @NotEmpty
    @Min(10)
    private String description;


    @Min(10)
    @Max(45)
    private int duration;

}

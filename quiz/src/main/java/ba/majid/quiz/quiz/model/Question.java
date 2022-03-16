package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public abstract class Question extends AbstractDocument {

    private String text;

    @JsonIgnore
    public abstract Set<String> getAnswers();

}

package ba.majid.quiz.quiz.model.questions;

import ba.majid.quiz.quiz.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class SingleChoiceQuestion extends Question {

    private Set<String> choices;

    @JsonIgnore
    private String answer;

    public SingleChoiceQuestion(String text, Collection<String> choices, String answer) {
        super(text);
        this.choices = new LinkedHashSet<>(choices);
        this.answer = answer;
    }

    public Set<String> getChoices() {
        return Collections.unmodifiableSet(this.choices);
    }

    @Override
    public Set<String> getAnswers() {
        return Collections.singleton(answer);
    }
}

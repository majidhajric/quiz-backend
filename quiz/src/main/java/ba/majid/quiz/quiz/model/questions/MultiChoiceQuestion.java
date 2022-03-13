package ba.majid.quiz.quiz.model.questions;

import ba.majid.quiz.quiz.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class MultiChoiceQuestion extends Question {

    private Set<String> choices;

    @JsonIgnore
    private Set<String> answers;

    public MultiChoiceQuestion(String text, Collection<String> choices, Collection<String> answers) {
        super(text);
        this.choices = new LinkedHashSet<>(choices);
        this.answers = new LinkedHashSet<>(answers);
    }

    public Set<String> getChoices() {
        return Collections.unmodifiableSet(this.choices);
    }
}

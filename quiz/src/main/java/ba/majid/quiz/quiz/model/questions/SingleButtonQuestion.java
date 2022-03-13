package ba.majid.quiz.quiz.model.questions;

import ba.majid.quiz.quiz.model.Question;
import lombok.Data;

import java.util.Collections;
import java.util.Set;

@Data
public class SingleButtonQuestion extends Question {

    private String label;

    public SingleButtonQuestion(String text, String label) {
        super(text);
        this.label = label;
    }

    @Override
    public Set<String> getAnswers() {
        return Collections.singleton(this.label);
    }
}

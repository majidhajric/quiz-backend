package ba.majid.quiz.quiz.model.questions;

import ba.majid.quiz.quiz.model.Question;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class EntryInputQuestion extends Question {

    private Set<String> answers;

    public EntryInputQuestion(String text, Collection<String> answers) {
        super(text);
        this.answers = new LinkedHashSet<>(answers);
    }

    public Set<String> getAnswers() {
        return Collections.unmodifiableSet(this.answers);
    }
}

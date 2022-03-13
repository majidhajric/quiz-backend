package ba.majid.quiz.quiz.model;

import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class Answer {

    private Question question;

    private Set<String> answers;

    public Answer(Question question, Collection<String> answers) {
        this.question = question;
        this.answers = new LinkedHashSet<>(answers);
    }

    public Set<String> getAnswers() {
        return Collections.unmodifiableSet(answers);
    }

    private void setAnswers(Set<String> answers) { throw new UnsupportedOperationException(); }
}

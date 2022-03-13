package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class ValidatedAnswer {

    private boolean hideAnswers;

    private Answer answer;

    private Set<String> answers;

    public ValidatedAnswer(Answer answer, Collection<String> answers) {
        this.answer = answer;
        this.answers = new LinkedHashSet<>(answers);
        this.hideAnswers = true;
    }

    @JsonProperty
    public Set<String> getAnswers() {
        if (hideAnswers) {
            return Collections.emptySet();
        } else {
            return Collections.unmodifiableSet(answers);
        }
    }

    private void setAnswers(Set<String> answers) { throw new UnsupportedOperationException(); }

    @JsonProperty
    public boolean isValid() {
        return this.answers.containsAll(this.answer.getAnswers()) && this.answers.size() == this.answer.getAnswers().size();
    }
}

package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.*;

@Data
public class AnsweredQuestion {

    @JsonIgnore
    private Question question;

    private Set<String> answers ;

    public AnsweredQuestion() {
        this(null, Collections.emptySet());
    }

    @PersistenceConstructor
    public AnsweredQuestion(final Question question, Collection<String> answers) {
        this.question = question;
        this.answers= new LinkedHashSet<>(answers);
    }

    public Set<String> getAnswers() {
        return Collections.unmodifiableSet(answers);
    }

    @JsonProperty
    public boolean isValid() {
        return question.getAnswers().size() == answers.size() && question.getAnswers().containsAll(answers);
    }
}

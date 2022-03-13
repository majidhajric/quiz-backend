package ba.majid.quiz.quiz.model.questions;

import ba.majid.quiz.quiz.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class YesNoQuestion extends Question {

    private Set<String> labels;

    @JsonIgnore
    private String answer;

    public YesNoQuestion(String text, Collection<String> labels, String answer) {
        super(text);
        Assert.isTrue(labels.size() == 2);
        this.labels = new LinkedHashSet<>(labels);
        this.answer = answer;
    }

    @Override
    public Set<String> getAnswers() {
        return Collections.singleton(answer);
    }
}

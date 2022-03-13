package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Document
public class QuizTemplate extends AbstractDocument {

    private static final float DEFAULT_THRESHOLD = 80.0F;

    @JsonIgnore
    private boolean showQuestions;

    private String name;

    private String description;

    private int duration;

    private Set<Question> questions;

    private float threshold;

    private QuizTemplate() {}

    public QuizTemplate(String name, String description, int duration, Question...questions) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.questions = new LinkedHashSet<>(Arrays.asList(questions));
        this.threshold = DEFAULT_THRESHOLD;
    }

    @JsonIgnore
    public Set<Question> getQuestions() {
        return Collections.unmodifiableSet(this.questions);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    @JsonProperty
    public Duration getDurationTime() {
        return Duration.ofMinutes(this.duration);
    }

    @JsonProperty
    public int getQuestionCount() {
        return this.questions.size();
    }

}

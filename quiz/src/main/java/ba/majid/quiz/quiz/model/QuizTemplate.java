package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Document(collection = "documents")
@TypeAlias("QuizTemplate")
public class QuizTemplate extends AbstractDocument {

    private String name;

    private String description;

    private int duration;

    private Set<Question> questions = new LinkedHashSet<>();

    private float threshold;

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

    private QuizTemplate() {}

    public static class Builder {
        private static final int DEFAULT_THRESHOLD = 65;
        private final QuizTemplate template = new QuizTemplate();
        {
            template.threshold = DEFAULT_THRESHOLD;
        }
        public Builder withName(String name) {
            Assert.notNull(name);
            template.setName(name);
            return this;
        }

        public Builder withDescription(String description) {
            Assert.notNull(description);
            template.setDescription(description);
            return this;
        }

        public Builder withDuration(int duration) {
            template.setDuration(duration);
            return this;
        }

        public Builder withQuestions(Set<Question> questions) {
            template.questions.addAll(questions);
            return this;
        }

        public QuizTemplate build() {
            return template;
        }
    }
}

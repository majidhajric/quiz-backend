package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quiz extends AbstractDocument {

    private final QuizTemplate template;

    private Question currentQuestion;

    @JsonIgnore
    private Set<Question> questions;

    @JsonIgnore
    private Set<AnsweredQuestion> answers;

    private QuizStatus status;

    public Quiz(QuizTemplate template) {
        this.template = template;
        this.questions = new LinkedHashSet<>(template.getQuestions());
        this.answers = new LinkedHashSet<>();
        this.currentQuestion = null;
        this.status = QuizStatus.NOT_STARTED;
    }

    public Quiz startQuiz() {
        if (this.status == QuizStatus.NOT_STARTED) {
            updateCurrentQuestion();
            this.status = QuizStatus.IN_PROGRESS;
        } else {
            this.currentQuestion = null;
            this.status = QuizStatus.NOT_STARTED;
        }
        return this;
    }

    private void updateCurrentQuestion() {
        this.currentQuestion = this.questions.stream().findFirst().orElse(null);
        if (this.currentQuestion != null) {
            this.questions.remove(this.currentQuestion);
        }
    }

    public Quiz answerQuestion(@NonNull Collection<String> answers) {
        Assert.notNull(this.currentQuestion, "Current question error");

        if (LocalDateTime.now().isAfter(this.getCreatedAt().plus(this.getTemplate().getDurationTime()))) {
            this.questions.clear();
            this.currentQuestion = null;
            this.status = QuizStatus.TIME_OUT;
            return this;
        }

        AnsweredQuestion answeredQuestion = new AnsweredQuestion(currentQuestion, answers);
        this.answers.add(answeredQuestion);
        updateCurrentQuestion();
        if (currentQuestion == null) {
            status = QuizStatus.ALL_ANSWERED;
        }
        return this;
    }

    @JsonProperty
    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    @JsonProperty
    public long getQuestionCount() {
        return this.template.getQuestionCount();
    }

    @JsonProperty
    public long getAnsweredCount() {
        return this.answers.size();
    }

    @JsonProperty
    public int getCorrectAnswersCount() {
        return (int) this.answers.stream().filter(AnsweredQuestion::isValid).count();
    }

    @JsonProperty
    public float getScore() {
        return 100.0f / getQuestionCount() * getCorrectAnswersCount();
    }

    private boolean isTimeEnd() {
        return super.getCreatedAt().plus(this.template.getDurationTime()).isBefore(LocalDateTime.now());
    }

    private boolean isAllAnswered() {
        return this.currentQuestion == null && this.questions.size() == 0;
    }

    @JsonIgnore
    public Set<AnsweredQuestion> getAnswers() {
        return Collections.unmodifiableSet(answers);
    }

    @JsonIgnore
    public Set<Question> getQuestions() {
        return Collections.unmodifiableSet(questions);
    }

    @JsonProperty
    public QuizState getState() {
        switch (this.status) {
            case NOT_STARTED:
                return QuizState.INITIAL;
            case IN_PROGRESS:
                return QuizState.RUNNING;
            case CANCELED:
            case TIME_OUT:
            case ALL_ANSWERED:
                return QuizState.FINISHED;
            default:
                return QuizState.INVALID;
        }
    }

}

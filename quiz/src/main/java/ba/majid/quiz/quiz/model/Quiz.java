package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static ba.majid.quiz.quiz.model.QuizStatus.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Document
public class Quiz extends AbstractDocument {

    private QuizTemplate template;

    private Question currentQuestion;

    @JsonIgnore
    private List<Question> questions;

    @JsonIgnore
    private List<ValidatedAnswer> answers;

    private QuizStatus status;

    private Quiz() {}

    public Quiz(QuizTemplate template) {
        this.template = template;
        this.questions = new LinkedList<>(template.getQuestions());
        this.answers = new LinkedList<>();
        this.currentQuestion = null;
        this.start();
    }

    public void start() {
        shuffleQuestions();
        this.currentQuestion = this.questions.get(0);
        this.status = IN_PROGRESS;
    }

    @JsonProperty
    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    public ValidatedAnswer answerCurrentQuestion(Collection<String> answers, Collection<String> correctAnswers) {
        Assert.notNull(currentQuestion, "Current question is null");
        this.questions.remove(currentQuestion);
        Answer userAnswer = new Answer(currentQuestion, answers);
        ValidatedAnswer validatedAnswer = new ValidatedAnswer(userAnswer, correctAnswers);
        this.answers.add(validatedAnswer);
        shuffleQuestions();
        this.currentQuestion = this.questions.stream().findFirst().orElse(null);
        this.updateStatus();
        return validatedAnswer;
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
    public long getCorrectAnswersCount() {
        return this.answers.stream().filter(ValidatedAnswer::isValid).count();
    }

    @JsonProperty
    public float getScore() {
        return 100.0f / getQuestionCount() * getCorrectAnswersCount();
    }

    @JsonProperty
    public QuizStatus getStatus() {
        updateStatus();
        return status;
    }

    private boolean isTimeEnd() {
        return super.getCreatedAt().plus(this.template.getDurationTime()).isBefore(LocalDateTime.now());
    }

    private boolean isAllAnswered() {
        return this.currentQuestion == null && this.questions.size() == 0;
    }

    private void updateStatus() {
        if (status == IN_PROGRESS) {
            if (isAllAnswered()) {
                status = ALL_ANSWERED;
            }
            if (isTimeEnd()) {
                status = TIME_OUT;
            }
        }
    }

    private void shuffleQuestions() {
        Collections.shuffle(questions);
    }
}

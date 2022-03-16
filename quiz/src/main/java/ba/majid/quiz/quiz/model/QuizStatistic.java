package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuizStatistic implements Content {

    private String id;

    private String name;

    private QuizStatus status;

    private QuizState state;

    private int totalQuestions;

    private int unansweredQuestions;

    private int answeredQuestions;

    private int correctAnswers;

    private double score;

    private double threshold;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int duration;

    private int time;

    @JsonProperty
    public boolean isPassed() {
        return score >= threshold;
    }

    public QuizStatistic(Quiz quiz) {
        this.id = quiz.getId();
        this.name = quiz.getTemplate().getName();
        this.status = quiz.getStatus();
        this.state = quiz.getState();
        this.totalQuestions = quiz.getTemplate().getQuestionCount();
        this.unansweredQuestions = quiz.getQuestions().size();
        this.answeredQuestions = quiz.getAnswers().size();
        this.correctAnswers = quiz.getCorrectAnswersCount();
        this.score = quiz.getScore();
        this.threshold = quiz.getTemplate().getThreshold();
        this.startTime = quiz.getCreatedAt();
        this.endTime = LocalDateTime.now();
        this.duration = quiz.getTemplate().getDuration();
        this.time = this.endTime.minusMinutes(this.startTime.getMinute()).getMinute();
    }
}

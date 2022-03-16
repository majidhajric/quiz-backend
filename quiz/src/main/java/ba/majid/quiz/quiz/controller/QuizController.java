package ba.majid.quiz.quiz.controller;

import ba.majid.quiz.quiz.model.Quiz;
import ba.majid.quiz.quiz.model.QuizStatistic;
import ba.majid.quiz.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @GetMapping(path = "/{quizId}")
    public Mono<Quiz> findQuiz(@PathVariable String quizId) {
        return quizService.findQuiz(quizId);
    }

    @GetMapping(path = "/{quizId}/statistic")
    public Mono<QuizStatistic> getQuizStatistic(@PathVariable String quizId) {
        return quizService.getQuizStatistic(quizId);
    }

    @PostMapping(path = "/{quizId}/start")
    public Mono<Quiz> startQuiz(@PathVariable String quizId) {
        return quizService.startQuiz(quizId);
    }

    @PutMapping(path = "/{quizId}/answer-current")
    public Mono<Quiz> answerQuestion(@PathVariable String quizId, @RequestParam List<String> answers) {
        return quizService.answerQuestion(quizId, answers);
    }
}

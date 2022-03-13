package ba.majid.quiz.quiz.controller;

import ba.majid.quiz.quiz.model.Quiz;
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

    @PostMapping(path = "/{templateId}")
    public Mono<Quiz> startQuiz(@PathVariable String templateId) {
        return quizService.startQuiz(templateId);
    }

    @GetMapping(path = "/{quizId}")
    public Mono<Quiz> findQuiz(@PathVariable String quizId) {
        return quizService.findQuiz(quizId);
    }

    @PutMapping(path = "/{quizId}/current")
    public Mono<Quiz> answerCurrentQuestion(@PathVariable String quizId, @RequestParam List<String> answers) {
        return quizService.answerCurrentQuestion(quizId, answers);
    }
}

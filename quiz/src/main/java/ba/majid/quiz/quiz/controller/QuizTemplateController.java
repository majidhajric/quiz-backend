package ba.majid.quiz.quiz.controller;

import ba.majid.quiz.quiz.model.QuizTemplate;
import ba.majid.quiz.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/quiz/templates")
public class QuizTemplateController {

    private final QuizService quizService;

    @GetMapping
    public Flux<QuizTemplate> getQuizTemplates() {
        return quizService.findAllQuizTemplates();
    }

    @GetMapping(path = "/{templateId}")
    public Mono<QuizTemplate> getQuizTemplate(@PathVariable String templateId) {
        return quizService.findQuizTemplate(templateId);
    }

    @GetMapping(path = "/{templateId}/details")
    public Mono<QuizTemplate> getQuizDetails(@PathVariable String templateId) {
        return quizService.getQuizDetails(templateId);
    }

    @PutMapping(path = "/{templateId}/questions/{questionId}")
    public Mono<QuizTemplate> assignQuestionToTemplate(@PathVariable String templateId, @PathVariable String questionId) {
        return quizService.addQuestionToQuizTemplate(templateId, questionId);
    }
}

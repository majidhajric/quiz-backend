package ba.majid.quiz.quiz.controller;

import ba.majid.quiz.quiz.dto.CreateQuestionDTO;
import ba.majid.quiz.quiz.model.Question;
import ba.majid.quiz.quiz.model.QuestionFactory;
import ba.majid.quiz.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/cms/quiz/questions")
public class QuestionTemplateController {

    private final QuestionService questionService;

    @GetMapping
    public Flux<Question> getAllQuestion() {
        return questionService.findAllQuestions();
    }

    @PostMapping
    public Mono<Question> createQuestion(@Valid @RequestBody CreateQuestionDTO createQuestionDTO) {
        Question question = QuestionFactory.createQuestion(createQuestionDTO.getQuestion(), createQuestionDTO.getChoices(), createQuestionDTO.getAnswers());
        log.debug("New question: {}", question);
        return questionService.saveQuestion(question);
    }
}

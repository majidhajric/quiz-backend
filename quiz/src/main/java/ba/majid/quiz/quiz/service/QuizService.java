package ba.majid.quiz.quiz.service;

import ba.majid.quiz.quiz.model.*;
import ba.majid.quiz.quiz.repository.QuizRepository;
import ba.majid.quiz.quiz.repository.QuizTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class QuizService {

    private final QuestionService questionService;

    private final QuizRepository quizRepository;

    private final QuizTemplateRepository quizTemplateRepository;

    public Mono<QuizTemplate> createQuizTemplate(QuizTemplate template) {
        return quizTemplateRepository.save(template);
    }

    public Mono<QuizTemplate> findQuizTemplate(String id) {
        return quizTemplateRepository.findById(id);
    }

    public Flux<QuizTemplate> findAllQuizTemplates() {
        return quizTemplateRepository.findAll();
    }

    public Mono<QuizTemplate> addQuestionToQuizTemplate(String templateId, String questionId) {
        return questionService
                .findQuestion(questionId)
                .flatMap(question -> quizTemplateRepository.findById(templateId)
                .doOnNext(qt -> qt.addQuestion(question)));
    }

    public Mono<Quiz> createQuiz(String templateId) {
        return quizTemplateRepository.findById(templateId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("No template found");
                    return Mono.error(new RuntimeException());
                }))
                .map(Quiz::new)
                .flatMap(quizRepository::save);
    }

    public Mono<Quiz> startQuiz(String quizId) {
        return quizRepository.findById(quizId)
                .map(Quiz::startQuiz)
                .flatMap(quizRepository::save);
    }

    public Mono<Quiz> saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Mono<Quiz> findQuiz(String quizId) {
        return quizRepository.findById(quizId);
    }

    public Mono<QuizTemplate> getQuizTemplate(String templateId) {
        return quizTemplateRepository.findById(templateId);
    }

    public Mono<QuizStatistic> getQuizStatistic(String quizId) {
        return quizRepository.findById(quizId).map(QuizStatistic::new);
    }

    public Mono<Quiz> answerQuestion(String quizId, List<String> answers) {
        return quizRepository.findById(quizId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("No quiz found");
                    return Mono.error(new RuntimeException());
                }))
                .flatMap(quiz -> {
                    quiz.answerQuestion(answers);
                    return quizRepository.save(quiz);
                });
    }
}

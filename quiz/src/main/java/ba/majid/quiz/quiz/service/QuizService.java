package ba.majid.quiz.quiz.service;

import ba.majid.quiz.quiz.model.Question;
import ba.majid.quiz.quiz.model.Quiz;
import ba.majid.quiz.quiz.model.QuizTemplate;
import ba.majid.quiz.quiz.model.ValidatedAnswer;
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
        return quizTemplateRepository.findById(id)
                .map(quizTemplate -> {
                            quizTemplate.setShowQuestions(true);
                            return quizTemplate;
                        }
                );
    }

    public Flux<QuizTemplate> findAllQuizTemplates() {
        return quizTemplateRepository.findAll()
                .map(quizTemplate -> {
                            quizTemplate.setShowQuestions(true);
                            return quizTemplate;
                        }
                );
    }

    public Mono<QuizTemplate> addQuestionToQuizTemplate(String templateId, String questionId) {
        return questionService
                .findQuestion(questionId)
                .flatMap(question -> quizTemplateRepository.findById(templateId)
                .doOnNext(qt -> qt.addQuestion(question)));
    }

    public Mono<Quiz> startQuiz(String templateId) {
        return quizTemplateRepository.findById(templateId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("No template found");
                    return Mono.error(new RuntimeException());
                }))
                .map(Quiz::new)
                .flatMap(quizRepository::save);
    }

    public Mono<Quiz> answerCurrentQuestion(String quizId, List<String> answers) {
        return quizRepository
                .findById(quizId)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("No quiz found");
                    return Mono.error(new RuntimeException());
                }))
                .flatMap(quiz -> {
                    Question currentQuestion = quiz.getCurrentQuestion();
                    if (currentQuestion == null) {
                        return Mono.error(new RuntimeException("Quiz already ended."));
                    }
                    ValidatedAnswer validatedAnswer = quiz.answerCurrentQuestion(answers, currentQuestion.getAnswers());
                    return quizRepository.save(quiz);
                });
    }

    public Mono<Quiz> saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Mono<Quiz> findQuiz(String quizId) {
        return quizRepository.findById(quizId);
    }

    public Mono<QuizTemplate> getQuizDetails(String templateId) {
        return quizTemplateRepository.findById(templateId)
                .map(quizTemplate -> {
                            quizTemplate.setShowQuestions(false);
                            return quizTemplate;
                        }
                );
    }
}

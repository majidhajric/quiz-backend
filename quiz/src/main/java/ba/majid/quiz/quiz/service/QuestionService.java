package ba.majid.quiz.quiz.service;

import ba.majid.quiz.quiz.model.Question;
import ba.majid.quiz.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Mono<Question> saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Mono<Question> findQuestion(String id) {
        return questionRepository.findById(id);
    }

    public Flux<Question> findAllQuestions() {
        return questionRepository.findAll();
    }
}

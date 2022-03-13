package ba.majid.quiz.quiz.repository;

import ba.majid.quiz.quiz.model.QuizTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTemplateRepository extends ReactiveMongoRepository<QuizTemplate, String> {
}

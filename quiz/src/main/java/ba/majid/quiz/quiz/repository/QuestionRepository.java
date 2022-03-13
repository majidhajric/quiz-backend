package ba.majid.quiz.quiz.repository;

import ba.majid.quiz.quiz.model.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {
}

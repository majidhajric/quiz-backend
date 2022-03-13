package ba.majid.quiz.quiz.repository;

import ba.majid.quiz.quiz.model.Quiz;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends ReactiveMongoRepository<Quiz, String> {
}

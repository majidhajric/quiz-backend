package ba.majid.quiz.quiz.demo;

import ba.majid.quiz.quiz.model.Question;
import ba.majid.quiz.quiz.model.QuestionFactory;
import ba.majid.quiz.quiz.model.QuizTemplate;
import ba.majid.quiz.quiz.service.QuestionService;
import ba.majid.quiz.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Profile("!test")
@Component
public class DemoRunner implements CommandLineRunner {

    private final QuizService quizService;

    private final QuestionService questionService;

    @Override
    public void run(String...args) throws Exception {
        log.debug("Creating demo questions and quizzes...");

        Question entry = QuestionFactory.createQuestion("Type 'yes' to continue?", null, List.of("yes"));
        Question button = QuestionFactory.createQuestion("Click on 'Next' to continue...", List.of("Next"), List.of("Next"));
        Question yesNo = QuestionFactory.createQuestion("Is it easy? :)", Arrays.asList("No", "Yes"), List.of("Yes"));
        Question single = QuestionFactory.createQuestion("What about weather in Sahara?", Arrays.asList("Sunny", "Cloudy", "Rainy", "Storm"), List.of("Sunny"));
        Question multi = QuestionFactory.createQuestion("Select 2 and 4", Arrays.asList("1", "2", "3", "4"), Arrays.asList("2", "4"));
        Question question1 = questionService.saveQuestion(entry).block();
        Question question2 = questionService.saveQuestion(button).block();
        Question question3 = questionService.saveQuestion(yesNo).block();
        Question question4 = questionService.saveQuestion(single).block();
        Question question5 = questionService.saveQuestion(multi).block();
        assert question1 != null;
        assert question2 != null;
        assert question3 != null;
        assert question4 != null;
        assert question5 != null;
        QuizTemplate quizTemplate = new QuizTemplate.Builder()
                .withName("Demo")
                .withDescription("Nice description here.")
                .withQuestions(Set.of(question1, question2, question3, question4, question5))
                .withDuration(45)
                .build();
        quizService.createQuizTemplate(quizTemplate).block();
    }
}

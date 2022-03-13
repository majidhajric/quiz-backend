package ba.majid.quiz;

import ba.majid.quiz.quiz.model.QuizTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@RequiredArgsConstructor
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuizTest {

    @Test
    public void testDocument() {
        QuizTemplate quizTemplate = new QuizTemplate("Demo", "Nothing", 45);
        Assertions.assertNotNull(quizTemplate);
    }

}

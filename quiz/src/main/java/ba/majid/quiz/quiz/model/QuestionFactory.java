package ba.majid.quiz.quiz.model;

import ba.majid.quiz.quiz.model.questions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;

@Slf4j
public class QuestionFactory {

    @NonNull
    public static Question createQuestion(@NonNull String text, @Nullable Collection<String> possibleChoices, @NonNull Collection<String> validAnswers) {
        int possibleChoicesCount = possibleChoices != null ? possibleChoices.size() : 0;
        int validAnswersCount = validAnswers.size();
        if (possibleChoicesCount == 0) {
            return new EntryInputQuestion(text, validAnswers);
        }
        if (possibleChoicesCount == 1) {
            return new SingleButtonQuestion(text, possibleChoices.stream().findFirst().orElseThrow());
        }

        if (possibleChoicesCount == 2 && validAnswersCount == 1) {
            return new YesNoQuestion(text, possibleChoices, validAnswers.stream().findFirst().orElseThrow());
        }

        if (possibleChoicesCount > 2) {
            if (validAnswersCount == 1) {
                return new SingleChoiceQuestion(text, possibleChoices, validAnswers.stream().findFirst().orElseThrow());
            } else if (validAnswersCount > 1) {
                return new MultiChoiceQuestion(text, possibleChoices, validAnswers);
            }
        }
        log.error("Illegal question type");
        return new SingleButtonQuestion("Skip this!", "Skip");
    }
}

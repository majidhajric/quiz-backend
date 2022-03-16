package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface Content {

    @JsonProperty
    default String getType() {
        return this.getClass().getSimpleName();
    }
}

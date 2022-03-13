package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public interface Content extends Serializable {

    @JsonProperty
    default String getId() { return UUID.randomUUID().toString(); }

    @JsonProperty
    default String getType() {
        return this.getClass().getSimpleName();
    }
}

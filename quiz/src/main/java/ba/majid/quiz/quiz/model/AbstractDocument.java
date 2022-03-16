package ba.majid.quiz.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractDocument implements Content {

    @JsonProperty
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreatedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();;
}

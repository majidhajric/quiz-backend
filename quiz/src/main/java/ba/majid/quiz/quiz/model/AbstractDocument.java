package ba.majid.quiz.quiz.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Document
public abstract class AbstractDocument {

    @NotEmpty
    @Id
    private final String id = UUID.randomUUID().toString();

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreatedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();

}

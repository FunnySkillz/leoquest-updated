package at.htl.control;

import at.htl.entity.Question;
import at.htl.entity.Questionnaire;
import at.htl.entity.QuestionType;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
public class QuestionnaireTest {


    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AgroalDataSource ds;

    @Test
    @Order(1)
    void t001_insertQuestion() {
        // arrange
        Question question = new Question("xy", 1,
                QuestionType.SingleChoice, questionnaireRepository.findById(1L));

        // act
        questionRepository.save(question);

        // assert
        Table table = new Table(ds, "LQ_QUESTION");
        output(table).toConsole();

        assertThat(table).row(table.getRowsList().size() - 1)
                .value("Q_ID").isNotNull()
                .value("Q_TEXT").isEqualTo("xy")
                .value("Q_SEQ_NO").isEqualTo(1);
        questionRepository.remove(question);
    }
}

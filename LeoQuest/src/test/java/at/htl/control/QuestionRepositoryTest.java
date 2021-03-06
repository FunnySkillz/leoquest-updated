package at.htl.control;

import at.htl.entity.Question;
import at.htl.entity.QuestionType;
import at.htl.entity.Questionnaire;
import at.htl.entity.Teacher;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class QuestionRepositoryTest {

    @Inject
    QuestionRepository questionRepository;

    @Inject
    AgroalDataSource ds;

    @Order(100)
    @Test
    void persistAQuestion() {
        Teacher teacher = new Teacher("Max");

        Questionnaire questionnaire = new Questionnaire(1L,"Quest1",
                "TestQuestionnaire 1", teacher);

        Question question = new Question(
                "This is a question",
                1,
                QuestionType.SingleChoice,
                questionnaire);

        questionRepository.save(question);

        Table table = new Table(ds,"LQ_QUESTION");
        Assertions.assertThat(table)
                .column("Q_TEXT").value().isEqualTo("This is a question")
                .column("Q_QUESTIONNAIRE_ID").value().isEqualTo(questionnaire.getId())
                .column("Q_TYPE_ID").value().isEqualTo("SingleChoice");
    }
}

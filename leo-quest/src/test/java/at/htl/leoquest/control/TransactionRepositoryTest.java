package at.htl.leoquest.control;

import at.htl.leoquest.entities.Questionnaire;
import at.htl.leoquest.entities.Survey;
import at.htl.leoquest.entities.Transaction;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionRepositoryTest {

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    SurveyRepository surveyRepository;

    Table transactions = new Table(DataSource.getDataSource(), "s_transaction");

    @PersistenceContext
    EntityManager em;

    @Test
    @Order(10)
    void createTransactionTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey survey = new Survey(dt, q);

        questionnaireRepository.save(q);
        surveyRepository.save(survey);
        transactionRepository.save(new Transaction("abc", false, (Survey) em.createQuery("select s from Survey s where s.id = 1").getSingleResult()));
        assertThat(transactions).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(false)
                .value().isEqualTo("abc")
                .value().isEqualTo(1);
    }

}

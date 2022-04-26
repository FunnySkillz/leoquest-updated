package at.htl.leoquest.control;

import at.htl.leoquest.entities.Questionnaire;
import at.htl.leoquest.entities.Survey;
import at.htl.leoquest.entities.Teacher;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherRepositoryTest {

    @Inject
    TeacherRepository teacherRepository;
    @Inject
    AgroalDataSource ds;

    Table teacher = new Table(ds, "lq_teacher");

    @Test
    @Order(10)
    void createTeacherTest(){
        Questionnaire q = new Questionnaire(1L, "Test", "Test of the Questionnaire");
        LocalDate dt = LocalDate.now();
        Survey s = new Survey(dt, q);
        teacherRepository.save(new Teacher("Teach", s));
        assertThat(teacher).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo("Teach")
                .value().isEqualTo(1);
    }
}

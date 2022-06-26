package at.htl.entity;

import at.htl.entity.Questionnaire;
import at.htl.entity.Teacher;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NamedQueries({

        @NamedQuery(
                name = "Survey.findAll",
                query = "select s from Survey s"
        ),
        @NamedQuery(
                name = "Survey.findQuestion",
                query = "SELECT s FROM Survey s WHERE s.questionnaire.id = :questionnaire_id"
        ),
        @NamedQuery(
                name = "Survey.findById",
                query = "select s from Survey s where s.id = :id order by s.date"
        )
})
@Entity
@Table(name = "LQ_SURVEY")
public class Survey {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID")
    public Long id;

    @Column(name = "S_TITLE")
    public String title;

    @Column(name = "S_DESCRIPTION")
    public String description;

    @Column(name = "S_I_ID")
    public String interviewer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "S_QN_ID")
    public Questionnaire questionnaire;

    @Column(name = "S_DATE")
    public LocalDate date;

    public Survey() {
    }

    public Survey(String title, String description, String interviewer, Questionnaire questionnaire, LocalDate date) {
        this.title = title;
        this.description = description;
        this.interviewer = interviewer;
        this.questionnaire = questionnaire;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Survey survey = (Survey) o;
        return Objects.equals(id, survey.id)
                && Objects.equals(title, survey.title)
                && Objects.equals(description, survey.description)
                && Objects.equals(interviewer, survey.interviewer)
                && Objects.equals(questionnaire, survey.questionnaire)
                && Objects.equals(date, survey.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, interviewer, questionnaire, date);
    }

    @Override
    public String toString() {
        return "Survey{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", interviewer=" + interviewer +
                ", questionnaire=" + questionnaire +
                ", date=" + date +
                '}';
    }
}

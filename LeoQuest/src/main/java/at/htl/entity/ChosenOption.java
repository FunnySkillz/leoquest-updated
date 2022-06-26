package at.htl.entity;

import at.htl.entity.Answer;
import at.htl.entity.AnswerOption;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "ChosenOption.findAll",
                query = "select co from ChosenOption co"
        )
})
@Entity
@Table(name = "LQ_CHOSEN_OPTION")
public class ChosenOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "co_answerOption_id")
    private AnswerOption answerOption;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "co_answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "co_question_id")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ChosenOption() {
    }

}

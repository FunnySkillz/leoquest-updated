package at.htl.entity;

import javax.persistence.*;

@NamedQueries({

        @NamedQuery(
                name = "Transaction.findAll",
                query = "select ta from Transaction ta"
        ),
        @NamedQuery(
                name = "Transaction.findById",
                query = "select t from Transaction t where t.id = :id order by t.code"
        )
})
@Entity
@Table(name = "LQ_TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_code")
    private String code;

    @Column(name = "t_password")
    private String password;

    @Column(name = "t_isUsed")
    public boolean isUsed;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "t_survey_id")
    private Survey survey;

    public Transaction() {
    }

    public Transaction(String code, Survey survey) {
        this.code = code;
        this.survey = survey;
    }

    public Transaction(String code, String password, boolean isUsed, Survey survey) {
        this.code = code;
        this.password = password;
        this.isUsed = isUsed;
        this.survey = survey;
    }

    public Transaction(Long id, String code, String password, boolean isUsed, Survey survey) {
        this.id = id;
        this.code = code;
        this.password = password;
        this.isUsed = isUsed;
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long t_id) {
        this.id = t_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String t_code) {
        this.code = t_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String t_password) {
        this.password = t_password;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean t_isUsed) {
        this.isUsed = t_isUsed;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey t_survey) {
        this.survey = t_survey;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", isUsed=" + isUsed +
                ", survey=" + survey +
                '}';
    }
}

package at.htl.leoquest.control;

import at.htl.leoquest.entities.Answer;
import at.htl.leoquest.entities.AnswerOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AnswerRepository implements PanacheRepository<Answer> {

    @Transactional
    public Answer save(Answer answer){
        return getEntityManager().merge(answer);
    }
}

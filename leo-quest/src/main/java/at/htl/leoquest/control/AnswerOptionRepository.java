package at.htl.leoquest.control;

import at.htl.leoquest.entities.AnswerOption;
import at.htl.leoquest.entities.Question;
import com.sun.tools.jconsole.JConsoleContext;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AnswerOptionRepository implements PanacheRepository<AnswerOption> {

    @Transactional
    public AnswerOption save(AnswerOption answerOption){
        return getEntityManager().merge(answerOption);
    }

    public List<AnswerOption> findAllOptions(){
        return listAll();
    }
}

import static org.fest.assertions.Assertions.*;

import models.Disciplina;
import models.Tarefa;
import models.dao.GenericDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by nazareno on 14/11/14.
 */
public class MinhasTarefasTest {

    private GenericDAO dao = new GenericDAO();

    @Test
    public void deveIniciarSemDisciplinas() throws Exception {
        List<Disciplina> disciplinas = dao.findAllByClassName(Disciplina.class.getName());
        assertThat(disciplinas).isEmpty();
    }

    @Test
    public void deveSalvarDisciplinaNoBD() throws Exception {
        Disciplina disciplina = new Disciplina("SI1", "Sistemas de informação 1");
        dao.persist(disciplina);

        List<Disciplina> disciplinas = dao.findAllByClassName(Disciplina.class.getName());
        assertThat(disciplinas.size()).isEqualTo(1);
        assertThat(disciplinas.get(0).getCodigo()).isEqualTo("SI1");
    }

    @Test
    public void deveSalvarTarefaNoBD() throws Exception {
        Disciplina disciplina = new Disciplina("SI1", "Sistemas de informação 1");
        dao.persist(disciplina);

        Tarefa tarefa = new Tarefa("Lab 2", disciplina);

        dao.persist(tarefa);

        List<Tarefa> disciplinas = dao.findAllByClassName(Tarefa.class.getName());
        assertThat(disciplinas.size()).isEqualTo(1);
        assertThat(disciplinas.get(0).getNome()).isEqualTo("Lab 2");
    }

    public EntityManager em;

    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }

}

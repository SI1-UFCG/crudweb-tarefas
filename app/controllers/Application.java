package controllers;

import models.Disciplina;
import models.dao.GenericDAO;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    private static final GenericDAO dao = new GenericDAO();

    @Transactional
    public static Result index() {
        List<Disciplina> disciplinas = dao.findAllByClassName(Disciplina.class.getName());
        return ok(index.render(disciplinas));
    }

    @Transactional
    public static Result criaDisciplina(){
        DynamicForm form = Form.form().bindFromRequest();

        String codigo = form.get("codigo");
        String nome = form.get("nome");

        Disciplina disciplina = new Disciplina(codigo, nome);
        dao.persist(disciplina);

        List<Disciplina> disciplinas = dao.findAllByClassName(Disciplina.class.getName());
        return ok(index.render(disciplinas));
    }
}

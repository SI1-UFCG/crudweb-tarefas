import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void deveTerPaginaInicalSemDisciplinas() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/");
                assertThat(browser.pageSource()).contains("Nenhuma disciplina cadastrada");
            }
        });
    }


    @Test
    public void deveCadastrarUmaDisciplinaNaPagina() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/");

                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("codigo", "SI1");
                parameters.put("nome", "Sistemas de Informação 1");

                FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(parameters);

                Result result = Helpers.callAction(controllers.routes.ref.Application.criaDisciplina(), fakeRequest);

                int responseCode = status(result);
                assertThat(responseCode).isEqualTo(OK);
            }
        });
    }
}

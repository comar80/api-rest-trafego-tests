package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroUsuarioSteps {
        CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService();

        @Dado("que eu tenha os seguintes dados do usuário:")
        public void queEuTenhaOsSeguintesDadosDoUsuário(List<Map<String, String>> rows) {
                for (Map<String, String> columns : rows) {
                        cadastroUsuarioService.setFieldsUsuario(columns.get("campo"), columns.get("valor"));
                }
        }

        @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuários")
        public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuários(String endPoint) {
                cadastroUsuarioService.createUsuario(endPoint);
        }

        @Então("o status code da resposta do usuário deve ser {int}")
        public void oStatusCodeDaRespostaDoUsuárioDeveSer(int statusCode) {
                Assert.assertEquals(statusCode, cadastroUsuarioService.response.statusCode());
        }

        @E("a resposta de erro da api do usuário deve retornar o json {string}")
        public void aRespostaDeErroDaApidodoUsuárioDeveRetornarOJson(String message) {
                String errorMessageModel = cadastroUsuarioService.response.getBody().asString();
                Assert.assertEquals(message, errorMessageModel);
        }

        @Dado("que eu recupere o ID do usuário criado no contexto")
        public void queEuRecupereOIDDoUsuárioCriadoNoContexto() {
                cadastroUsuarioService.retrieveIdUsuario();
        }

        @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de usuário")
        public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeUsuário(String endpoint) {
                cadastroUsuarioService.deleteUsuario(endpoint);
        }

        @E("que o arquivo de contrato esperado é o {string}")
        public void queOArquivoDeContratoEsperadoÉO(String contrato) throws IOException {
                cadastroUsuarioService.setContract(contrato);
        }

        @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
        public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
                Set<ValidationMessage> validateResponse = cadastroUsuarioService.validateResponseAgainstSchema();
                Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
        }

        @Quando("eu enviar a requisição com o ID para o endpoint {string} de busca de usuário")
        public void euEnviarARequisiçãoComOIDParaOEndpointDeBuscaDeUsuário(String endpoint) {
                cadastroUsuarioService.getUsuario(endpoint);
        }
}
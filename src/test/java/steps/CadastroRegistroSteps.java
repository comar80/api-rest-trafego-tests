package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroRegistroService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroRegistroSteps {
    CadastroRegistroService cadastroRegistroService = new CadastroRegistroService();

    @Dado("que eu tenha os seguintes dados do registro:")
    public void queEuTenhaOsSeguintesDadosDoRegistro(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroRegistroService.setFieldsRegistro(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de registro")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeRegistro(String endPoint) {
        cadastroRegistroService.createRegistro(endPoint);
    }

    @Então("o status code da resposta do registro deve ser {int}")
    public void oStatusCodeDaRespostaDoRegistroDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroRegistroService.response.statusCode());
    }

    @E("a resposta de erro da api do registro deve retornar o json {string}")
    public void aRespostaDeErroDaApiDoRegistroDeveRetornarOJson(String message) {
        String errorMessageModel = cadastroRegistroService.response.getBody().asString();
        Assert.assertEquals(message, errorMessageModel);
    }

    @Dado("que eu recupere o ID do registro criado no contexto")
    public void queEuRecupereOIDDoRegistroCriadoNoContexto() {
        cadastroRegistroService.retrieveIdRegistro();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de registro")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeRegistro(String endpoint) {
        cadastroRegistroService.deleteRegistro(endpoint);
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de busca de registro")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeBuscaDeRegistro(String endpoint) {
        cadastroRegistroService.getRegistro(endpoint);
    }

    @E("que o arquivo de contrato do registro esperado é o {string}")
    public void queOArquivoDeContratoDoRegistroEsperadoÉO(String contrato) throws IOException {
        cadastroRegistroService.setContract(contrato);
    }

    @Então("a resposta da requisição do registro deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDoRegistroDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroRegistroService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroMotoristaService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroMotoristaSteps {
    CadastroMotoristaService cadastroMotoristaService = new CadastroMotoristaService();

    @Dado("que eu tenha os seguintes dados do motorista:")
    public void queEuTenhaOsSeguintesDadosDoMotorista(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroMotoristaService.setFieldsMotorista(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de motoristas")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeMotoristas(String endPoint) {
        cadastroMotoristaService.createMotorista(endPoint);
    }

    @Então("o status code da resposta do motorista deve ser {int}")
    public void oStatusCodeDaRespostaDoMotoristaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroMotoristaService.response.statusCode());
    }

    @E("a resposta de erro da api do motorista deve retornar o json {string}")
    public void aRespostaDeErroDaApiDoMotoristaDeveRetornarOJson(String message) {
        String errorMessageModel = cadastroMotoristaService.response.getBody().asString();
        Assert.assertEquals(message, errorMessageModel);
    }

    @Dado("que eu recupere o ID do motorista criado no contexto")
    public void queEuRecupereOIDDoMotoristaCriadoNoContexto() {
        cadastroMotoristaService.retrieveIdMotorista();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de motorista")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeMotorista(String endpoint) {
        cadastroMotoristaService.deleteMotorista(endpoint);
    }

    @E("que o arquivo de contrato do motorista esperado é o {string}")
    public void queOArquivoDeContratoDoMotoristaEsperadoÉO(String contrato) throws IOException {
        cadastroMotoristaService.setContract(contrato);
    }

    @Então("a resposta da requisição do motorista deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDoMotoristaDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroMotoristaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de busca de motorista")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeBuscaDeMotorista(String endpoint) {
        cadastroMotoristaService.getMotorista(endpoint);
    }
}

package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroIncidenteService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroIncidenteSteps {
    CadastroIncidenteService cadastroIncidenteService = new CadastroIncidenteService();

    @Dado("que eu tenha os seguintes dados do incidente:")
    public void queEuTenhaOsSeguintesDadosDoIncidente(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroIncidenteService.setFieldsIncidente(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de incidentes")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeIncidentes(String endPoint) {
        cadastroIncidenteService.createIncidente(endPoint);
    }

    @Então("o status code da resposta do incidente deve ser {int}")
    public void oStatusCodeDaRespostaDoIncidenteDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroIncidenteService.response.statusCode());
    }

    @E("a resposta de erro da api do incidente deve retornar o json {string}")
    public void aRespostaDeErroDaApiDoIncidenteDeveRetornarOJson(String message) {
        String errorMessage = cadastroIncidenteService.response.getBody().asString();
        Assert.assertEquals(message, errorMessage);
    }

    @Dado("que eu recupere o ID do incidente criado no contexto")
    public void queEuRecupereOIDDoIncidenteCriadoNoContexto() {
        cadastroIncidenteService.retrieveIdIncidente();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de incidente")
    public void euEnviarARequisicaoComOIDParaOEndpointDeDelecaoDeIncidente(String endpoint) {
        cadastroIncidenteService.deleteIncidente(endpoint);
    }

    @E("que o arquivo de contrato do incidente esperado é o {string}")
    public void queOArquivoDeContratoDoIncidenteEsperadoE(String contrato) throws IOException {
        cadastroIncidenteService.setContract(contrato);
    }

    @Então("a resposta da requisição do incidente deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisicaoDoIncidenteDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroIncidenteService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de busca de incidente")
    public void euEnviarARequisicaoComOIDParaOEndpointDeBuscaDeIncidente(String endpoint) {
        cadastroIncidenteService.getIncidente(endpoint);
    }
}

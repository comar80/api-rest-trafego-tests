package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroVeiculoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroVeiculoSteps {
    CadastroVeiculoService cadastroVeiculoService = new CadastroVeiculoService();

    @Dado("que eu tenha os seguintes dados do veículo:")
    public void queEuTenhaOsSeguintesDadosDoVeículo(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroVeiculoService.setFieldsVeiculo(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de veículos")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeVeículos(String endPoint) {
        cadastroVeiculoService.createVeiculo(endPoint);
    }

    @Então("o status code da resposta do veículo deve ser {int}")
    public void oStatusCodeDaRespostaDoVeículoDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroVeiculoService.response.statusCode());
    }

    @E("a resposta de erro da api do veículo deve retornar o json {string}")
    public void aRespostaDeErroDaApidoVeículoDeveRetornarOJson(String message) {
        String errorMessageModel = cadastroVeiculoService.response.getBody().asString();
        Assert.assertEquals(message, errorMessageModel);
    }

    @Dado("que eu recupere o ID do veículo criado no contexto")
    public void queEuRecupereOIDDoVeículoCriadoNoContexto() {
        cadastroVeiculoService.retrieveIdVeiculo();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de veículo")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeVeículo(String endpoint) {
        cadastroVeiculoService.deleteVeiculo(endpoint);
    }

    @E("que o arquivo de contrato do veículo esperado é o {string}")
    public void queOArquivoDeContratoDoVeículoEsperadoÉO(String contrato) throws IOException {
        cadastroVeiculoService.setContract(contrato);
    }

    @Então("a resposta da requisição do veículo deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDoVeículoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroVeiculoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de busca de veículo")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeBuscaDeVeículo(String endpoint) {
        cadastroVeiculoService.getVeiculo(endpoint);
    }
}
package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroRotaService;
import services.CadastroVeiculoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroRotaSteps {
    CadastroRotaService cadastroRotaService = new CadastroRotaService();

    @Dado("que eu tenha os seguintes dados da rota:")
    public void queEuTenhaOsSeguintesDadosDaRota(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroRotaService.setFieldsRota(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de rotas")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeRotas(String endPoint) {
        cadastroRotaService.createRota(endPoint);
    }

    @Então("o status code da resposta da rota deve ser {int}")
    public void oStatusCodeDaRespostaDaRotaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroRotaService.response.statusCode());
    }

    @E("a resposta de erro da api da rota deve retornar o json {string}")
    public void aRespostaDeErroDaApidaRotaDeveRetornarOJson(String message) {
        String errorMessageModel = cadastroRotaService.response.getBody().asString();
        Assert.assertEquals(message, errorMessageModel);
    }

    @Dado("que eu recupere o ID da rota criada no contexto")
    public void queEuRecupereOIDDaRotaCriadaNoContexto() {
        cadastroRotaService.retrieveIdRota();
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de rota")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeRota(String endpoint) {
        cadastroRotaService.deleteRota(endpoint);
    }

    @E("que o arquivo de contrato da rota esperado é o {string}")
    public void queOArquivoDeContratoDaRotaEsperadoÉO(String contrato) throws IOException {
        cadastroRotaService.setContract(contrato);
    }

    @Então("a resposta da requisição da rota deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDaRotaDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroRotaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de busca de rota")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeBuscaDeRota(String endpoint) {
        cadastroRotaService.getRota(endpoint);
    }
}
package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.MotoristaModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroMotoristaService {

    String idMotorista;
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    final MotoristaModel motoristaModel = new MotoristaModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "https://api-trafego-dev-dda3bhdng8gtaqhk.eastus2-01.azurewebsites.net";

    public void setFieldsMotorista(String field, String value) {
        switch (field) {
            case "idMotorista" -> motoristaModel.setIdMotorista(Integer.parseInt(value));
            case "nome" -> motoristaModel.setNome(value);
            case "cpf" -> motoristaModel.setCpf(value);
            case "endereco" -> motoristaModel.setEndereco(value);
            case "telefone" -> motoristaModel.setTelefone(value);
            case "cnhNumero" -> motoristaModel.setCnhNumero(value);
            case "cnhCategoria" -> motoristaModel.setCnhCategoria(value);
            case "cnhValidade" -> motoristaModel.setCnhValidade(value);
            default -> throw new IllegalStateException("Unexpected field: " + field);
        }
    }

    public void createMotorista(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(motoristaModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveIdMotorista() {
        idMotorista = String.valueOf(gson.fromJson(response.jsonPath().prettify(), MotoristaModel.class).getIdMotorista());
    }

    public void deleteMotorista(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idMotorista);
        response = given()
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void getMotorista(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idMotorista);
        response = given()
                .accept(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            JSONTokener tokener = new JSONTokener(inputStream);
            return new JSONObject(tokener);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de motorista" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-motorista.json");
            default -> throw new IllegalStateException("Unexpected contract: " + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        return schema.validate(jsonResponseNode);
    }
}


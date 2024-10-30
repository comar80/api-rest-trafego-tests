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
import model.IncidenteModel;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroIncidenteService {

    String idIncidente;
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    final IncidenteModel incidenteModel = new IncidenteModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";

    public void setFieldsIncidente(String field, String value) {
        switch (field) {
            case "idIncidente" -> incidenteModel.setIdIncidente(Integer.parseInt(value));
            case "tipoIncidente" -> incidenteModel.setTipoIncidente(value);
            case "localizacao" -> incidenteModel.setLocalizacao(value);
            case "descricao" -> incidenteModel.setDescricao(value);
            case "dataHora" -> incidenteModel.setDataHora(value);
            case "status" -> incidenteModel.setStatus(value);
            default -> throw new IllegalStateException("Unexpected field " + field);
        }
    }

    public void createIncidente(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(incidenteModel);
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

    public void retrieveIdIncidente() {
        idIncidente = String.valueOf(gson.fromJson(response.jsonPath().prettify(), IncidenteModel.class).getIdIncidente());
    }

    public void deleteIncidente(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idIncidente);
        response = given()
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void getIncidente(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idIncidente);
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
            case "Cadastro bem-sucedido de incidente" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-incidente.json");
            default -> throw new IllegalStateException("Unexpected contract " + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}

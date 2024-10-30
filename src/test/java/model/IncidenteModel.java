package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class IncidenteModel {
    @Expose
    private int idIncidente;

    @Expose
    private String tipoIncidente;

    @Expose
    private String localizacao;

    @Expose
    private String descricao;

    @Expose
    private String dataHora;

    @Expose
    private String status;
}
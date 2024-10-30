package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class RotaModel {
    @Expose
    private int idRota;
    @Expose
    private String origem;
    @Expose
    private String destino;
    @Expose
    private int distancia;
    @Expose
    private int tempoMedio;
    @Expose
    private String condicoesEspeciais;
    @Expose
    private int velocidadeMaximaPermitida;
}
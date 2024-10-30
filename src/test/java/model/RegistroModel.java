package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class RegistroModel {
    @Expose
    private int idRegistroTrafego;

    @Expose
    private int idVeiculo;

    @Expose
    private int idMotorista;

    @Expose
    private int idRota;

    @Expose
    private String dataHora;

    @Expose
    private int velocidadeVeiculo;

    @Expose
    private String condicoesMetereologicas;

    @Expose
    private String outrosDados;
}
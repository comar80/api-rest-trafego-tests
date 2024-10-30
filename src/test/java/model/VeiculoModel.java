package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class VeiculoModel {
    @Expose
    private int idVeiculo;
    @Expose
    private String tipoVeiculo;
    @Expose
    private String modelo;
    @Expose
    private int anoFabricacao;
    @Expose
    private String placa;
    @Expose
    private String outrosDetalhes;
}
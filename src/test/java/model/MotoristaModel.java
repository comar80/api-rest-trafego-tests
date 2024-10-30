package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MotoristaModel {
    @Expose
    private int idMotorista;

    @Expose
    private String nome;

    @Expose
    private String cpf;

    @Expose
    private String endereco;

    @Expose
    private String telefone;

    @Expose
    private String cnhNumero;

    @Expose
    private String cnhCategoria;

    @Expose
    private String cnhValidade;
}
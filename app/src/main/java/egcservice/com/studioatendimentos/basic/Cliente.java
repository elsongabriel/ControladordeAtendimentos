package egcservice.com.studioatendimentos.basic;

import java.io.Serializable;

/**
 * Created by elson on 21/06/2016.
 */
@SuppressWarnings("serial")
public class Cliente implements Serializable {
    private int Id;
    private String Nome;
    private String Telefone;
    private String Email;
    private String DataNasc;
    private String DataCad;
    private boolean Ativo;

    public Cliente(int id, String nome, String telefone, String email, String dataNasc, String dataCad, boolean ativo) {
        Id = id;
        Nome = nome;
        Telefone = telefone;
        Email = email;
        DataNasc = dataNasc;
        DataCad = dataCad;
        Ativo = ativo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDataNasc() {
        return DataNasc;
    }

    public void setDataNasc(String dataNasc) {
        DataNasc = dataNasc;
    }

    public String getDataCad() {
        return DataCad;
    }

    public void setDataCad(String dataCad) {
        DataCad = dataCad;
    }

    public boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(boolean ativo) {
        Ativo = ativo;
    }

    public Cliente() {
    }
}

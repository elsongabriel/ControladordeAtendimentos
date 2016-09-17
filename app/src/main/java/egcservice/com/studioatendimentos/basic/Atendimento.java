package egcservice.com.studioatendimentos.basic;

import java.io.Serializable;

/**
 * Created by elson on 21/06/2016.
 */
@SuppressWarnings("serial")
public class Atendimento implements Serializable {
    private int Id;
    private String DataAgnd, Subtotal, Desconto, Total;

    public Atendimento(int id, String dataAgnd, String subtotal, String desconto, String total) {
        Id = id;
        DataAgnd = dataAgnd;
        Subtotal = subtotal;
        Desconto = desconto;
        Total = total;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDataAgnd() {
        return DataAgnd;
    }

    public void setDataAgnd(String dataAgnd) {
        DataAgnd = dataAgnd;
    }

    public String getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(String subtotal) {
        Subtotal = subtotal;
    }

    public String getDesconto() {
        return Desconto;
    }

    public void setDesconto(String desconto) {
        Desconto = desconto;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public Atendimento() {
    }
}

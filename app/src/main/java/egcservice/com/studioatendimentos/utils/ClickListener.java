package egcservice.com.studioatendimentos.utils;

import egcservice.com.studioatendimentos.basic.Atendimento;
import egcservice.com.studioatendimentos.basic.Cliente;

/**
 * Created by elson on 21/06/2016.
 */
public interface ClickListener {
    void cliClick(Cliente cliente);

    void atdClick(Atendimento atendimento);
}

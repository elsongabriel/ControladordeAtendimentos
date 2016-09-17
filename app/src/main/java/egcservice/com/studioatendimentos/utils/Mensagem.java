package egcservice.com.studioatendimentos.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by elson on 21/06/2016.
 */
public class Mensagem {
    public static void exibir(Activity tela, String message) {
        Toast.makeText(tela.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void exibirLong(Activity tela, String message) {
        Toast.makeText(tela.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}

package egcservice.com.studioatendimentos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Elson on 21/02/2016.
 */
public class DetailClienteActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cliente);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new DetailClienteFragment()).commit();
    }
}

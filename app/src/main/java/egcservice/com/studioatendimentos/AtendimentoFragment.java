package egcservice.com.studioatendimentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AtendimentoFragment extends Fragment {

    TextView txtInfo;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_atendimento, null);

//        txtInfo = (TextView) layout.findViewById(R.id.txtAtd);
//        txtInfo.setText("atd");

        return layout;
    }

}

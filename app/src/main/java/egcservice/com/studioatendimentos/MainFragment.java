package egcservice.com.studioatendimentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {

    private static final String COD_TELA = "codTela";
    private String codTela;

    public void newInstance(String tela) {
        Bundle args = new Bundle();
        args.putString(COD_TELA, tela);
        codTela = tela;

        switch (tela) {
            case "0":
                new AtendimentoFragment().setArguments(args);
                break;
            case "1":
                new ListClienteFragment().setArguments(args);
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null) {
            codTela = getArguments().getString(COD_TELA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return retornarViewFragment(inflater, codTela);
    }

    private View retornarViewFragment(LayoutInflater inflater, String cod) {
        View layout;

        if (cod.equals("0")) {

            layout = inflater.inflate(R.layout.fragment_atendimento, null);
        } else if (cod.equals("1")) {

            layout = inflater.inflate(R.layout.fragment_list_cliente, null);
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame_principal, new ListClienteFragment())
                    .commit();
        } else {

            layout = inflater.inflate(R.layout.fragment_home, null);
        }

        TextView txtHome = (TextView) getActivity().findViewById(R.id.txtHome);
        txtHome.setText("");
        return layout;
    }
}

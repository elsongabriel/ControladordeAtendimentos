package egcservice.com.studioatendimentos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import egcservice.com.studioatendimentos.basic.Cliente;
import egcservice.com.studioatendimentos.utils.ClickListener;
import egcservice.com.studioatendimentos.utils.ConexaoHttp;

public class ListClienteFragment extends ListFragment {

    AutoCompleteTextView actClientes;
    ConexaoHttp conexaoHttp = new ConexaoHttp();
    ProgressDialog pDialog;
    private static List<Cliente> mListaClientes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iniciarDownload();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cliente, null);

        actClientes = (AutoCompleteTextView) view.findViewById(R.id.actClientes);
        actClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getActivity() instanceof ClickListener) {
                    Cliente cli = mListaClientes.get(position);
                    ((ClickListener) getActivity())
                            .cliClick(cli);
                }
            }
        });
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (getActivity() instanceof ClickListener) {
            Cliente cli = mListaClientes.get(position);
            ((ClickListener) getActivity()).cliClick(cli);
        }
    }

    public void iniciarDownload() {
        if (conexaoHttp.temConexao(getActivity()))
            new ListarEmpresasPorTipoTask().execute();
    }

    class ListarEmpresasPorTipoTask extends AsyncTask<Void, Void, List<Cliente>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Carregando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected List<Cliente> doInBackground(Void... params) {
            try {
                mListaClientes = conexaoHttp.listarClientes();
                return mListaClientes;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Cliente> result) {
            super.onPostExecute(result);
            if (result != null) {
                refreshList();
            }
            pDialog.dismiss();
        }
    }

    public void refreshList() {
        List<String> clis = new ArrayList<>();
        //setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mListaClientes);
        //setListAdapter(new EmpresasAdapter(getActivity(), mListaClientes));
        //atualizar autocompletetextview
        for (int i = 0; i < mListaClientes.size(); i++) {
            clis.add(mListaClientes.get(i).getNome());
        }
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, clis));
        actClientes.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, clis));
    }

}

package egcservice.com.studioatendimentos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import egcservice.com.studioatendimentos.adapters.DetailAtendimentosAdapter;
import egcservice.com.studioatendimentos.basic.Atendimento;
import egcservice.com.studioatendimentos.basic.Cliente;
import egcservice.com.studioatendimentos.utils.ConexaoHttp;

public class DetailClienteFragment extends ListFragment {

    private TextView lblId;
    private EditText txtNome, txtTel;
    private Button btnAltSalv;

    ConexaoHttp conexaoHttp = new ConexaoHttp();
    public Cliente mCliente;
    private static List<Atendimento> mAtendimentos = new ArrayList<>();
    private boolean isSalvar = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Intent it = getActivity().getIntent();
        if (it.hasExtra("cliente")) {
            mCliente = (Cliente) it.getSerializableExtra("cliente");
            iniciarDownloadAtendimentos(mCliente.getId());
        }
    }

    public void iniciarDownloadAtendimentos(int cliente) {
        if (conexaoHttp.temConexao(getActivity()))
            new ListarAtendimentosPorClienteTask().execute(cliente);
    }

    class ListarAtendimentosPorClienteTask extends AsyncTask<Integer, Void, List<Atendimento>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Atendimento> doInBackground(Integer... params) {
            try {
                mAtendimentos = conexaoHttp.listarAtendimentosPorCliente(params[0]);
                return mAtendimentos;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Atendimento> result) {
            super.onPostExecute(result);
            if (result != null) {
                setListAdapter(new DetailAtendimentosAdapter(getActivity(), result, mCliente));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_cliente, container, false);
        lblId = (TextView) view.findViewById(R.id.lblClienteId);
        txtNome = (EditText) view.findViewById(R.id.txtClienteNome);
        txtTel = (EditText) view.findViewById(R.id.txtClienteTel);
        btnAltSalv = (Button) view.findViewById(R.id.btnAltSalvarCliente);
        btnAltSalv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSalvar) {
                    isSalvar = true;
                    txtNome.setEnabled(true);
                    txtTel.setEnabled(true);
                    btnAltSalv.setText("Salvar");
                } else {
                    isSalvar = false;
                    txtNome.setEnabled(false);
                    txtTel.setEnabled(false);
                    btnAltSalv.setText("Alterar");
                }
//                Mensagem.exibirLong(getActivity(), "Clicou no botão Alterar/Salvar");
            }
        });
        lblId.setText(String.valueOf(mCliente.getId()));
        txtNome.setText(mCliente.getNome());
        txtTel.setText(mCliente.getTelefone());
        return view;
    }
}
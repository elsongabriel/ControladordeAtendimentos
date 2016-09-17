package egcservice.com.studioatendimentos.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import egcservice.com.studioatendimentos.basic.Atendimento;
import egcservice.com.studioatendimentos.basic.Cliente;

/**
 * Created by elson on 21/06/2016.
 */
public class ConexaoHttp {
    //LINKS
    private String DIRETORIO = "http://egcservices.com.br/webservices/studioatendimentos/";
    private String URL_JSON_LISTAR_CLIENTES = DIRETORIO + "listar_clientes.php";
    private String URL_JSON_LISTAR_ATENDIMENTOS = DIRETORIO + "listar_atendimentos.php?clienteId=";

    static InputStream is = null;
    static HttpURLConnection conexao = null;

    private static HttpURLConnection conectar(String urlArquivo) throws IOException {
        URL url = new URL(urlArquivo);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(10000);
        conexao.setConnectTimeout(15000);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();
        return conexao;
    }

    public static boolean temConexao(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public List<Cliente> listarClientes() {
        //Áreas das Variaveis
        Cliente mCliente;
        List<Cliente> mClientes = new ArrayList<>();
        try {
            conexao = conectar(URL_JSON_LISTAR_CLIENTES);
            if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conexao.getInputStream();
                JSONObject reader = new JSONObject(bytesToString(is));
                JSONArray jsonClientes = reader.getJSONArray("clientes");
                for (int i = 0; i < jsonClientes.length(); i++) {
                    JSONObject jsonCliente = jsonClientes.getJSONObject(i);
                    mCliente = new Cliente();
                    mCliente.setId(Integer.valueOf(jsonCliente.getString("id")));
                    mCliente.setNome(String.valueOf(jsonCliente.getString("nome")));
                    mCliente.setTelefone(String.valueOf(jsonCliente.getString("telefone")));
                    mCliente.setEmail(String.valueOf(jsonCliente.getString("email")));
                    mCliente.setDataCad(String.valueOf(jsonCliente.getString("data_cadastro")));
                    mCliente.setDataNasc(String.valueOf(jsonCliente.getString("data_nascimento")));
                    mCliente.setAtivo((Integer.parseInt(jsonCliente.getString("ativo")) == 1) ? true : false);
                    mClientes.add(mCliente);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return mClientes;
    }

    public List<Atendimento> listarAtendimentosPorCliente(int cli) {
        //Áreas das Variaveis
        Atendimento mAtendimento;
        List<Atendimento> mAtendimentos = new ArrayList<>();
        try {
            conexao = conectar(URL_JSON_LISTAR_ATENDIMENTOS + cli);
            if (conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conexao.getInputStream();
                JSONObject reader = new JSONObject(bytesToString(is));
                JSONArray jsonAtends = reader.getJSONArray("atendimentos");
                for (int i = 0; i < jsonAtends.length(); i++) {
                    JSONObject jsonAtend = jsonAtends.getJSONObject(i);
                    mAtendimento = new Atendimento();
                    mAtendimento.setId(Integer.valueOf(jsonAtend.getString("id")));
                    mAtendimento.setDataAgnd(jsonAtend.getString("data_agendada"));
                    mAtendimento.setSubtotal(jsonAtend.getString("subtotal"));
                    mAtendimento.setDesconto(jsonAtend.getString("desconto"));
                    mAtendimento.setTotal(jsonAtend.getString("total"));
                    mAtendimentos.add(mAtendimento);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return mAtendimentos;
    }

    private static String bytesToString(InputStream is) throws IOException {
        byte[] buf = new byte[1024];
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesLidos;
        while ((bytesLidos = is.read(buf)) != -1) {
            buffer.write(buf, 0, bytesLidos);
        }
        return new String(buffer.toByteArray());
    }
}

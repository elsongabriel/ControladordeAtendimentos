package egcservice.com.studioatendimentos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import egcservice.com.studioatendimentos.R;
import egcservice.com.studioatendimentos.basic.Atendimento;
import egcservice.com.studioatendimentos.basic.Cliente;

public class DetailAtendimentosAdapter extends ArrayAdapter<Atendimento> {

    private Atendimento mAtendimento;
    private TextView lblAtendId, lblAtendData, lblAtendSubtotal, lblAtendDesc, lblAtendTotal;
    public Cliente mCliente;

    public DetailAtendimentosAdapter(Context ctx, List<Atendimento> atnds, Cliente cliente) {
        super(ctx, 0, atnds);
        mCliente = cliente;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mAtendimento = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_detail_atendimentos, null);
        }

        lblAtendId = (TextView) convertView.findViewById(R.id.lblAtendId);
        lblAtendData = (TextView) convertView.findViewById(R.id.lblDataAgend);
        lblAtendSubtotal = (TextView) convertView.findViewById(R.id.lblSubtotal);
        lblAtendDesc = (TextView) convertView.findViewById(R.id.lblDesconto);
        lblAtendTotal = (TextView) convertView.findViewById(R.id.lblTotal);

        lblAtendId.setText("Id: " + String.valueOf(mAtendimento.getId()));
        String data = mAtendimento.getDataAgnd();
        String newData = data.substring(8, 10) + "/" + data.substring(5, 7) + "/" + mAtendimento.getDataAgnd().substring(0, 4);
        String newHora = data.substring(11, 16);
        lblAtendData.setText("Em: " + newData + " às " + newHora);
        lblAtendSubtotal.setText("Subtotal: R$ " + mAtendimento.getSubtotal());
        lblAtendDesc.setText("Desconto: R$ " + mAtendimento.getDesconto());
        lblAtendTotal.setText("Total: R$ " + mAtendimento.getTotal());

        return convertView;
    }
}

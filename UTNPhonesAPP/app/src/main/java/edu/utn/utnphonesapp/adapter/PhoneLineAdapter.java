package edu.utn.utnphonesapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.model.Line;

public class PhoneLineAdapter extends RecyclerView.Adapter<PhoneLineAdapter.PhoneLineViewHolder> {

    private List<Line> lineList;

    public PhoneLineAdapter(List<Line> lineList) {
        this.lineList = lineList;
    }

    public static class PhoneLineViewHolder extends RecyclerView.ViewHolder {
        public TextView phoneLineNumber;
        public TextView phoneLineType;
        public TextView phoneLineStatus;


        public PhoneLineViewHolder(@NonNull View itemView) {
            super(itemView);
            this.phoneLineNumber = itemView.findViewById(R.id.textValueNumber);
            this.phoneLineStatus = itemView.findViewById(R.id.textValueStatus);
            this.phoneLineType = itemView.findViewById(R.id.textValueType);
        }
    }

    @NonNull
    @Override
    public PhoneLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_line_card_view, parent, false);
        return new PhoneLineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneLineViewHolder holder, int position) {
        Line line = lineList.get(position);

        holder.phoneLineNumber.setText(line.getCity().getPrefix() + "-" + line.getPhoneNumber());
        holder.phoneLineStatus.setText(line.getLineStatus().name().equals("ACTIVE") ? "ACTIVA" : "INACTIVA");
        if (line.getLineStatus().name().equals("ACTIVE"))
            holder.phoneLineStatus.setTextColor(Color.GREEN);
        else
            holder.phoneLineStatus.setTextColor(Color.RED);
        holder.phoneLineType.setText(line.getLineType().name().equals("RESIDENTIAL") ? "RESIDENCIAL" : "MOVIL");
    }


    @Override
    public int getItemCount() {
        return lineList.size();
    }
}

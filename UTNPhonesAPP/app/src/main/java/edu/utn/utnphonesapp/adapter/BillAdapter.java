package edu.utn.utnphonesapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.model.Bill;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private List<Bill> billList;

    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    public static class BillViewHolder extends RecyclerView.ViewHolder {
        public TextView billLine;
        public TextView billQtyOfCalls;
        public TextView billIssueDate;
        public TextView billExpirationDate;
        public TextView billStatus;


        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            this.billExpirationDate = itemView.findViewById(R.id.billExpirationDate);
            this.billIssueDate = itemView.findViewById(R.id.billIssueDate);
            this.billLine = itemView.findViewById(R.id.billLine);
            this.billQtyOfCalls = itemView.findViewById(R.id.billQtyCalls);
            this.billStatus = itemView.findViewById(R.id.billStatus);
        }
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_card_view, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = billList.get(position);
        String billStatus = null;
        int billColor;
        Date issuedate = new Date(bill.getIssueDate().getTime());
        Date expirationDate = new Date(bill.getIssueDate().getTime());


        if (bill.getPaid().name().equals("PAID")){
            billStatus = "PAGA";
            billColor = Color.GREEN;
        }else if(bill.getPaid().name().equals("UNPAID")){
            billStatus = "IMPAGA";
            billColor = Color.YELLOW;
        }else if (bill.getPaid().name().equals("EXPIRED")){
            billStatus = "EXPIRADA";
            billColor = Color.RED;
        }


        holder.callDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
        holder.callTime.setText(new SimpleDateFormat("HH:mm:ss").format(date));

        holder.billStatus.setText(billStatus);
        holder.billStatus.setTextColor(billColor);
        holder.billQtyOfCalls.setText(bill.getQtyOfCalls().toString());
        holder.billLine.setText(bill.getLine().getCity().getPrefix + "-" + bill.getLine().getPhoneNumber());
        holder.billIssueDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(issuedate));
        holder.billIssueDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(expirationDate));

    }


    @Override
    public int getItemCount() {
        return billList.size();
    }
}

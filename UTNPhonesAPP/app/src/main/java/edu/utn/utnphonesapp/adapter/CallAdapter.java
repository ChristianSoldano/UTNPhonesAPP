package edu.utn.utnphonesapp.adapter;


import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.model.Line;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.CallViewHolder> {

    private List<Call> callList;

    public CallAdapter(List<Call> callList) {
        this.callList = callList;
    }

    public static class CallViewHolder extends RecyclerView.ViewHolder {
        public TextView callDestination;
        public TextView callDate;
        public TextView callDuration;
        public TextView callPrice;


        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            this.callDestination = itemView.findViewById(R.id.callDestination);
            this.callDate = itemView.findViewById(R.id.callDate);
            this.callDuration = itemView.findViewById(R.id.callDuration);
            this.callPrice = itemView.findViewById(R.id.callPrice);
        }
    }

    @NonNull
    @Override
    public CallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_card_view, parent, false);
        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallViewHolder holder, int position) {
        Call call = callList.get(position);
        Integer duration = call.getCallDuration()/60;
        holder.callDestination.setText(call.getDestinationLine().getPhoneNumber);
        holder.callDuration.setText(duration.toString() + " min");
        holder.callPrice.setText("$" + call.getCallPrice().toString());
        holder.callDate.setText(call.getCallDate().toString());
    }


    @Override
    public int getItemCount() {
        return callList.size();
    }
}


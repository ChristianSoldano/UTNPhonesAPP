package edu.utn.utnphonesapp.adapter;

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
import edu.utn.utnphonesapp.model.Call;

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
        public TextView callOrigin;
        public TextView callTime;


        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            this.callDestination = itemView.findViewById(R.id.callDestination);
            this.callDate = itemView.findViewById(R.id.callDate);
            this.callDuration = itemView.findViewById(R.id.callDuration);
            this.callPrice = itemView.findViewById(R.id.callPrice);
            this.callTime = itemView.findViewById(R.id.callTime);
            this.callOrigin = itemView.findViewById(R.id.callOrigin);
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
        Integer duration = call.getCallDuration() / 60;
        holder.callDestination.setText(call.getDestinationLine().getCity().getPrefix() + "-" + call.getDestinationLine().getPhoneNumber());
        holder.callOrigin.setText(call.getOriginLine().getCity().getPrefix() + "-" + call.getOriginLine().getPhoneNumber());
        holder.callDuration.setText(duration.toString() + " min");
        holder.callPrice.setText("$" + call.getCallPrice().toString());

        Date date = new Date(call.getCallDate().getTime());
        holder.callDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(date));
        holder.callTime.setText(new SimpleDateFormat("HH:mm:ss").format(date));

    }


    @Override
    public int getItemCount() {
        return callList.size();
    }
}


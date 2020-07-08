package edu.utn.utnphonesapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.utn.utnphonesapp.Interface.JsonApi;
import edu.utn.utnphonesapp.LoginActivity;
import edu.utn.utnphonesapp.MainActivity;
import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.adapter.BillAdapter;
import edu.utn.utnphonesapp.model.Bill;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.utn.utnphonesapp.MainActivity.session;
import static edu.utn.utnphonesapp.config.Constants.API_ROOT_URL;

public class BillFragment extends Fragment {


    private ConstraintLayout progressBar;
    private ConstraintLayout noContent;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.loadingScreen);
        noContent = view.findViewById(R.id.noContent);
        recyclerView = view.findViewById(R.id.phoneLineRecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());


        getBills();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_line, container, false);
    }


    public void getBills() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<List<Bill>> call = jsonApi.getBills(session.getUserId(), session.getToken());

        call.enqueue(new Callback<List<Bill>>() {

            @Override
            public void onResponse(Call<List<Bill>> call, Response<List<Bill>> response) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);

                if (response.code() == 204) {
                    adapter = new BillAdapter(new ArrayList<Bill>());
                    noContent.setVisibility(View.VISIBLE);
                } else
                    adapter = new BillAdapter(response.body());

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Bill>> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("connection_error", "connection error");
                startActivity(intent);
                ((MainActivity) getActivity()).finish();
            }
        });

    }
}
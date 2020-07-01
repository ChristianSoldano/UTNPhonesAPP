package edu.utn.utnphonesapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.utn.utnphonesapp.Interface.JsonApi;
import edu.utn.utnphonesapp.MainActivity;
import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.adapter.PhoneLineAdapter;
import edu.utn.utnphonesapp.dto.LoginResponseDto;
import edu.utn.utnphonesapp.model.City;
import edu.utn.utnphonesapp.model.Line;
import edu.utn.utnphonesapp.model.User;
import edu.utn.utnphonesapp.model.enums.LineStatus;
import edu.utn.utnphonesapp.model.enums.LineType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.utn.utnphonesapp.MainActivity.session;
import static edu.utn.utnphonesapp.config.Constants.API_ROOT_URL;


public class PhoneLineFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Line> lineList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.phoneLineRecycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());

        getLines();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_line, container, false);
    }


    public void getLines() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<List<Line>> call = jsonApi.getLines(session.getUserId(), session.getToken());

        call.enqueue(new Callback<List<Line>>() {

            @Override
            public void onResponse(Call<List<Line>> call, Response<List<Line>> response) {
                if (response.code() == 204) {
                    adapter = new PhoneLineAdapter(new ArrayList<Line>());
                } else
                    adapter = new PhoneLineAdapter(response.body());

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Line>> call, Throwable t) {
                System.out.println("ERROR FATAL");
            }
        });

    }

}
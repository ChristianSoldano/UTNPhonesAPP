package edu.utn.utnphonesapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import edu.utn.utnphonesapp.Interface.JsonUserApi;
import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.utn.utnphonesapp.MainActivity.session;
import static edu.utn.utnphonesapp.config.Constants.API_ROOT_URL;


public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<TextView> textViewList = new ArrayList<>();

        TextView tvUsername = view.findViewById(R.id.textUserTitle);

        TextView tvName = view.findViewById(R.id.textValueName);
        TextView tvLastname = view.findViewById(R.id.textValueLastname);
        TextView tvClientNumber = view.findViewById(R.id.textValueClientNumber);
        TextView tvDni = view.findViewById(R.id.textValueDni);

        TextView tvAdress = view.findViewById(R.id.textValueAdress);
        TextView tvCity = view.findViewById(R.id.textValueCity);
        TextView tvProvince = view.findViewById(R.id.textValueProvince);

        textViewList.add(tvUsername);
        textViewList.add(tvName);
        textViewList.add(tvLastname);
        textViewList.add(tvClientNumber);
        textViewList.add(tvDni);
        textViewList.add(tvAdress);
        textViewList.add(tvCity);
        textViewList.add(tvProvince);

        getUser(textViewList);

    }

    private void getUser(final List<TextView> textViewList) {
        session.setUserId(2);
        session.setToken("eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIyIiwic3ViIjoiU29rZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX0NMSUVOVCJdLCJpYXQiOjE1OTI0MzM3ODR9.JuuZjZFVtL0gRrvl5p--F8c8EHRJtIFMatPTie0iIHdqx9sdAgMRR3yxstXE_B8yY2-8eVi4-IuBmx81pwP28w");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonUserApi jsonUserApi = retrofit.create(JsonUserApi.class);

        //TODO CAMBIAAR EL USERID POR EL DE LA SESION
        Call<User> call = jsonUserApi.getUser(session.getUserId(), session.getToken());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    //TODO "Codigo de respuesta: " + response.code()
                    System.out.println("CODIGO DE RESPUESTA: " + response.code());
                    return;
                }
                User user = response.body();

                textViewList.get(0).setText(user.getUsername());
                textViewList.get(1).setText(user.getName());
                textViewList.get(2).setText(user.getLastname());
                textViewList.get(3).setText(user.getIdUser().toString());
                textViewList.get(4).setText(user.getDni().toString());
                textViewList.get(5).setText(user.getAddress());
                textViewList.get(6).setText(user.getCity().getCityName());
                textViewList.get(7).setText(user.getCity().getProvince().getProvinceName());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //textViewHome.setText(t.getMessage());
                System.out.println(t.getMessage());
            }
        });
    }
}

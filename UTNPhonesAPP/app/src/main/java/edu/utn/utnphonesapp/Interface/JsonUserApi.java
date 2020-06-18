package edu.utn.utnphonesapp.Interface;

import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JsonUserApi {


    @GET("api/user/{userId}")
    Call<User> getUser(@Path("userId") Integer userId,@Header("Authorization") String token);

}

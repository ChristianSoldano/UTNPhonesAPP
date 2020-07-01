package edu.utn.utnphonesapp.Interface;

import java.util.List;

import edu.utn.utnphonesapp.dto.LoginResponseDto;
import edu.utn.utnphonesapp.model.Bill;
import edu.utn.utnphonesapp.model.City;
import edu.utn.utnphonesapp.model.Line;
import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApi {
    @GET("api/user/{userId}")
    Call<User> getUser(@Path("userId") Integer userId, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/api/user/login")
    Call<LoginResponseDto> login(@Field("username") String username, @Field("password") String password);

    @GET("api/city/")
    Call<List<City>> getCities();

    @POST("/api/user/")
    Call<Void> register(@Body User user);

    @GET("/api/line/user/{userId}")
    Call<List<Line>> getLines(@Path("userId") Integer userId, @Header("Authorization") String token);

    @GET("/api/call/{userId}")
    Call<List<edu.utn.utnphonesapp.model.Call>> getCalls(@Path("userId") Integer userId, @Header("Authorization") String token);

    @GET("/api/bill/{userId}")
    Call<List<Bill>> getBills(@Path("userId") Integer userId, @Header("Authorization") String token);

}

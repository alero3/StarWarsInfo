package com.example.arota.starwarsinfo.main.retrofit;

import com.example.arota.starwarsinfo.main.models.PeopleWrapper;
import com.example.arota.starwarsinfo.main.models.Person;
import com.example.arota.starwarsinfo.main.models.Planet;
import com.example.arota.starwarsinfo.main.models.PlanetsWrapper;
import com.example.arota.starwarsinfo.main.models.StarshipsWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("api/people")
    Call<Person> doGetPerson();

    @GET("api/people/")
    Call<PeopleWrapper> doGetPeople();

    @GET("api/people/")
    Call<PeopleWrapper> doGetPeopleAtPage(@Query("page") String page);

    @GET("api/planets/")
    Call<PlanetsWrapper> doGetPlanetsAtPage(@Query("page") String page);

    @GET("api/starships/")
    Call<StarshipsWrapper> doGetStarshipsAtPage(@Query("page") String page);





    /*
    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
    */
}

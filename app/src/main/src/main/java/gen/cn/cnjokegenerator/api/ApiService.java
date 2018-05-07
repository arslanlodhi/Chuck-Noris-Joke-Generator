package gen.cn.cnjokegenerator.api;


import java.lang.reflect.Array;
import java.util.ArrayList;

import gen.cn.cnjokegenerator.models.CategoriesResponse;
import gen.cn.cnjokegenerator.models.JokesModel;
import gen.cn.cnjokegenerator.models.JokesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/jokes/random/20")
    Call<JokesResponse> getJokes(
            @Query("firstName") String firstName,
            @Query("lastName") String lastName,
            @Query("limitTo") ArrayList<String> categories,
            @Query("escape") String escapse);


    @GET("/categories")
    Call<CategoriesResponse> getCategories();

}
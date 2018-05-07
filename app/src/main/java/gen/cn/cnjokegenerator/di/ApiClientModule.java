package gen.cn.cnjokegenerator.di;


import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gen.cn.cnjokegenerator.api.ApiService;
import gen.cn.cnjokegenerator.api.api7.ApiHttpClient;
import gen.cn.cnjokegenerator.utils.ApiUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiClientModule {





    @Provides
    @Singleton
    public ApiService getApiService() {

        Retrofit api7Client  = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new ApiHttpClient().getHTTPClient(new HashMap<String, String>()))
                .build();
        return api7Client.create(ApiService.class);
    }


}

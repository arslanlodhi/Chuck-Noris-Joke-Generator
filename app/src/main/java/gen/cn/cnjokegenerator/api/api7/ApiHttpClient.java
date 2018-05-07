package gen.cn.cnjokegenerator.api.api7;

import java.util.Map;

import gen.cn.cnjokegenerator.utils.ApiUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Nauman.ashraf on 3/14/2018.
 */

public class ApiHttpClient {

    public OkHttpClient getHTTPClient(final Map<String, String> headers)
    {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        if(!ApiUtils.isLoggingEnabled)
            httpClient.addInterceptor(logging);

        return httpClient.build();

    }
}
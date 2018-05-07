package gen.cn.cnjokegenerator.di;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gen.cn.cnjokegenerator.AppApplication;
import gen.cn.cnjokegenerator.di.modules.ViewModelModule;


@Module(includes = ViewModelModule.class)
public class AppModule {


    Application application;

    @Inject
    AppModule(Application application){
        this.application=application;
    }


    public  boolean isConnectedToInternet(){
        ConnectivityManager connec =
                (ConnectivityManager)application.getSystemService(application.CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {


            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {


            return false;
        }
        return false;

    }


}

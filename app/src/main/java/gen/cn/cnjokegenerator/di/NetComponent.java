package gen.cn.cnjokegenerator.di;


import android.app.Application;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import gen.cn.cnjokegenerator.AppApplication;
import gen.cn.cnjokegenerator.di.modules.ActivityBuilderModule;


@Singleton
@Component(modules={AppModule.class,ApiClientModule.class,AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface NetComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        NetComponent build();
    }

    void inject(AppApplication app);

}
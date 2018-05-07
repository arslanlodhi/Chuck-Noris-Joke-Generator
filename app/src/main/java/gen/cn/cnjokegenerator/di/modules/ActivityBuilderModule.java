package gen.cn.cnjokegenerator.di.modules;



import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gen.cn.cnjokegenerator.views.JokesActivity;
import gen.cn.cnjokegenerator.views.MainActivity;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract JokesActivity jokesActivity();


}
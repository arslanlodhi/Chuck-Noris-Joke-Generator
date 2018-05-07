package gen.cn.cnjokegenerator.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gen.cn.cnjokegenerator.views.fragments.JokesFragment;

/**
 * Created by arslanlodhi on 1/25/18.
 */

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract JokesFragment contributeJokesFragment();
}
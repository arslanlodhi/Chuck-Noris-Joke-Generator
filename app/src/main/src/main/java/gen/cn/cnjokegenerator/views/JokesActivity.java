package gen.cn.cnjokegenerator.views;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import gen.cn.cnjokegenerator.R;
import gen.cn.cnjokegenerator.base.BaseActivity;
import gen.cn.cnjokegenerator.databinding.FragmentJokesBinding;
import gen.cn.cnjokegenerator.enums.CallTypeEnums;
import gen.cn.cnjokegenerator.enums.ViewModelEventsEnum;
import gen.cn.cnjokegenerator.models.JokesModel;
import gen.cn.cnjokegenerator.viewmodels.JokesActivityViewModel;
import gen.cn.cnjokegenerator.views.fragments.JokesFragment;

public class JokesActivity extends BaseActivity<JokesActivityViewModel, ViewDataBinding> {

    private static final String ARG_JOKES_LIST = "jokesList";

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Toolbar toolbar;

    public static void openJokesActivity(Activity activity, ArrayList<JokesModel> jokesList) {
        Intent jokesActivityIntent = new Intent(activity, JokesActivity.class);
        jokesActivityIntent.putParcelableArrayListExtra(ARG_JOKES_LIST, jokesList);
        activity.startActivity(jokesActivityIntent);
    }


    @Override
    public void onObserve(CallTypeEnums callType, ViewModelEventsEnum event, Object eventMessage) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_jokes;
    }

    @Override
    public Class getViewModel() {
        return JokesActivityViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<JokesModel> jokes = getIntent().getParcelableArrayListExtra(ARG_JOKES_LIST);
        viewModel.setJokeList(jokes);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setViewPager();


    }

    public void nextJoke(View view) {
        if (mViewPager.getCurrentItem() + 1 < viewModel.getJokeList().size())
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1,true);
    }

    public void previousJoke(View view) {
        if (mViewPager.getCurrentItem() - 1 >= 0)
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1,true);
    }

    public void shareJoke(View view) {
        String shareBody = viewModel.getJokeList().get(mViewPager.getCurrentItem()).getJoke() + "\n\n Via Chuck Norris Joke Generator";
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Via Chuck Norris Joke");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Chuck Norris Joke sharing"));
    }


    public void setViewPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), viewModel.getJokeList());
        toolbar.setTitle(viewModel.getTitle(1));
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(viewModel.getTitle(position + 1));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        ArrayList<JokesModel> jokes;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<JokesModel> jokes) {
            super(fm);
            this.jokes = jokes;
        }


        @Override
        public Fragment getItem(int position) {
            return JokesFragment.newInstance(jokes.get(position));
        }

        @Override
        public int getCount() {
            return this.jokes.size();
        }
    }
}

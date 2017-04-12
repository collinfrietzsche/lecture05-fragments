package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";

    private SearchFragment searchFragment;
    private MoviesFragment moviesFragment;
    private DetailFragment detailFragment;

    private ViewPager pager;
    private MoviePagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchFragment = SearchFragment.newInstance();

        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new MoviePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        Log.v(TAG, "Search Term: "+searchTerm);
        moviesFragment = MoviesFragment.newInstance(searchTerm);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(1);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Log.v(TAG, "Movie details: "+movie);
        detailFragment = DetailFragment.newInstance(movie.toString(), movie.imdbId);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(2);
    }

    protected class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            if (pos == 0) return searchFragment;
            if (pos == 1) return moviesFragment;
            if (pos == 2) return detailFragment;

            switch(pos) {
                case 0: return searchFragment;
                case 1: return moviesFragment;
                case 2: return detailFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            if (moviesFragment == null) {
                return 1;
            } else if (detailFragment == null) {
                return 2;
            } else {
                return 3;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }
}

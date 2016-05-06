package com.serveza.lepet.serveza.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Bar;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Fragments.BarActivityFragment.BarCommentFragment;
import com.serveza.lepet.serveza.Fragments.BarActivityFragment.BarHomeFragment;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerActivityMapFragment;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerCommentFragment;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerHomeFragment;

/**
 * Created by lepet on 5/5/2016.
 */
public class BarFragmentAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Info", "Comment"};
    private Context context;
    private Core core;
    private Bar bar;

    public BarFragmentAdapter(FragmentManager fm, Context context, Core core, Bar bar) {
        super(fm);
        this.context = context;
        this.bar = bar;
        this.core = core;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("BeerFragmentsAdapter position", String.valueOf(position));

        switch (position) {
            case 0:
                return BarHomeFragment.newInstance(core, bar);
            case 1:
                return BarCommentFragment.newInstance(core, bar);
        }
        return BarHomeFragment.newInstance(core, bar);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}

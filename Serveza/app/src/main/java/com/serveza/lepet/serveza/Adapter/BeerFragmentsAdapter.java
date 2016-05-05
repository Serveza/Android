package com.serveza.lepet.serveza.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.serveza.lepet.serveza.Classes.Core;
import com.serveza.lepet.serveza.Classes.Data.Beer;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerActivityMapFragment;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerCommentFragment;
import com.serveza.lepet.serveza.Fragments.BeerActivityFragment.BeerHomeFragment;
import com.serveza.lepet.serveza.Fragments.BeerFragment;

/**
 * Created by lepet on 5/5/2016.
 */
public class BeerFragmentsAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Info", "Bar", "Comment" };
    private Context context;
    private Core core;
    private Beer beer;

    public BeerFragmentsAdapter(FragmentManager fm, Context context, Core core, Beer beer)
    {
        super(fm);
        this.context = context;
        this.beer = beer;
        this.core = core;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("BeerFragmentsAdapter position", String.valueOf(position));

        switch (position)
        {
            case 0 :
                return BeerHomeFragment.newInstance(core, beer);
            case 1 :
                return BeerActivityMapFragment.newInstance(core, beer);
            case 2:
                return BeerCommentFragment.newInstance(core, beer);
        }
        return BeerHomeFragment.newInstance(core, beer);
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return tabTitles[position];
    }

}

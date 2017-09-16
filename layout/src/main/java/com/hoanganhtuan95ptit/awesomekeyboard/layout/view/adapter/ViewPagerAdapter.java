package com.hoanganhtuan95ptit.awesomekeyboard.layout.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by HOANG ANH TUAN on 7/16/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public void add(Fragment t) {
        synchronized (this) {
            fragments.add(t);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        synchronized (this) {
            fragments.clear();
            notifyDataSetChanged();
        }
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }
}

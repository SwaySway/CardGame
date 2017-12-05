package com.example.josue.cardgame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kor3a on 12/5/17.
 */

public class SectionsPageAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

//    private static int TAB_COUNT = 3;
//
//    public SectionsPageAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//
//        switch (position) {
//            case 0:
//                return Tab1Fragment.newInstance();
//            case 1:
//                return Tab2Fragment.newInstance();
//            case 2:
//                return Tab3Fragment.newInstance();
//        }
//        return null;
//    }
//
//    @Override
//    public int getCount() {
//        return TAB_COUNT;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return Tab1Fragment.TITLE;
//
//            case 1:
//                return Tab2Fragment.TITLE;
//
//            case 2:
//                return Tab3Fragment.TITLE;
//        }
//        return super.getPageTitle(position);
//    }
    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}

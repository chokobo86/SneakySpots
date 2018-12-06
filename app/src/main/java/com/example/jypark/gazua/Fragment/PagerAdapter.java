package com.example.jypark.gazua.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
<탭 간 터치슬라이드용>
두개의 탭(PageOneFragment.java, PageTwoFragment.java)을 연결해주는 컨드롤 페이지


*/


public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm){
        super(fm);
    }
    /*탭 클릭 실행시 화면 전환*/
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               return PageOneFragment.newInstance();
           case 1:
               return PageTwoFragment.newInstance();
            default:
                return null;

       }

    }
    /*탭 페이지 수*/
    private static int PAGE_NUMBER = 2;
    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }


    /*탭 이름*/
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Collection";
            case 1:
                return "Achievment";
                default:
                    return null;
        }
    }




}

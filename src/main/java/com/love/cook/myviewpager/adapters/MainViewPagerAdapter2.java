package com.love.cook.myviewpager.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghaixin on 16/11/30.
 */
public class MainViewPagerAdapter2 extends PagerAdapter {

    private List<ImageView> data;

    public MainViewPagerAdapter2(List<ImageView> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }

    }


    @Override
    public ImageView instantiateItem(ViewGroup container, int position) {

        position %= data.size();

        ImageView imageView = data.get(position);
        if (imageView.getParent() != null) {
            ViewGroup viewParent = (ViewGroup) imageView.getParent();
            viewParent.removeView(imageView);
        }
        container.addView(imageView);
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {


        //container.removeView(data.get(newPosition));

    }

    @Override
    public int getCount() {
        return data != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
package com.example.ramesh.WalllY;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by L080047 on 26/08/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {
    Context mContext;
    private PageSelectedListener listener;
    LayoutInflater mLayoutInflater;

    public int[] getDrawableList() {
        return DrawableList;
    }

    public int[] DrawableList = {
            R.drawable.blast,
            R.drawable.abnimal,
            R.drawable.ball,
            R.drawable.ball,
            R.drawable.bliss,
            R.drawable.blisss,
            R.drawable.blissss,
            R.drawable.box,
            R.drawable.cat,
            R.drawable.deeer,
            R.drawable.deer,
            R.drawable.gogs,
            R.drawable.jumbo,
            R.drawable.random,
            R.drawable.spec,
            R.drawable.spect,
            R.drawable.oreo
    };



    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setPageChangeListener(PageSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return DrawableList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.liner, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.Image1);
        imageView.setImageResource(DrawableList[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onPageSelected(position);

                }
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
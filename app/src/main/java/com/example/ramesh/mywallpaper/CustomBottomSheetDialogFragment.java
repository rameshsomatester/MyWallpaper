package com.example.ramesh.mywallpaper;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


/**
 * Created by L080047 on 22/08/2017.
 */

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment{
    public String mString;
    public TextView SetWallpaper , Share;
    public int position;
    static CustomBottomSheetDialogFragment newInstance(String string) {
        CustomBottomSheetDialogFragment f = new CustomBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mString = getArguments().getString("string");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout, container, false);

        SetWallpaper =(TextView) v.findViewById(R.id.Set);
        Share = (TextView)v.findViewById(R.id.Share);
        CustomPagerAdapter test = new CustomPagerAdapter(getContext());
        final int [] images=test.getDrawableList();
        SetWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    WallpaperManager MyWall = WallpaperManager.getInstance(getContext());
                    MyWall.setResource(images[getPosition()]);
                    Toast.makeText(getContext(),"Wallpaper changed",Toast.LENGTH_LONG);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

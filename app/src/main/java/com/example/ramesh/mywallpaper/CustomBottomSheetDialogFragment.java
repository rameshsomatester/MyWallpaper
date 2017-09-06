package com.example.ramesh.mywallpaper;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by L080047 on 22/08/2017.
 */

public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment{
    public String mString;
    public TextView SetWallpaper , Share;
    public static int position;
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
                    Toast.makeText(getContext(),"Wallpaper changed",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap;
                OutputStream output;

                // Retrieve the image from the res folder
                bitmap = BitmapFactory.decodeResource(getResources(),
                        images[getPosition()]);

                // Find the SD Card path

                File filepath = Environment.getExternalStorageDirectory();

                // Create a new folder AndroidBegin in SD Card
                File dir = new File(filepath.getAbsolutePath() + "/Wallpaper/");
                dir.mkdirs();

                // Create a name for the saved image
                File file = new File(dir, "sample_wallpaper.png");

                try {

                    // Share Intent
                    Intent share = new Intent(Intent.ACTION_SEND);

                    // Type of file to share
                    share.setType("image/jpeg");

                    output = new FileOutputStream(file);

                    // Compress into png format image from 0% - 100%
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                    output.flush();
                    output.close();

                    // Locate the image to Share
                    Uri uri = Uri.fromFile(file);

                    // Pass the image into an Intnet
                    share.putExtra(Intent.EXTRA_STREAM, uri);

                    // Show the social share chooser list
                    startActivity(Intent.createChooser(share, "Share Image Tutorial"));

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }});
        return v;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

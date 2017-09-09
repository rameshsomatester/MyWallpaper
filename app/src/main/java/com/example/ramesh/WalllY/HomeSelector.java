package com.example.ramesh.WalllY;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class HomeSelector extends AppCompatActivity implements PageSelectedListener{
        private  BottomSheetDialogFragment myBottomSheet;
        public ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_selector);
        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this);
        mCustomPagerAdapter.setPageChangeListener(this);
        mViewPager = (ViewPager) findViewById(R.id.PAger);
        mViewPager.setAdapter(mCustomPagerAdapter);
        myBottomSheet = CustomBottomSheetDialogFragment.newInstance("tes");
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        alertBuild("You wanna quit?");
    }
    public void  alertBuild(String Message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(Message);
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
                dialog.cancel();
            }
        });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        setContentView(R.layout.activity_home_selector);
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void onPageSelected(int page) {
        ImageSend(mViewPager.getCurrentItem());
        myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());
    }

    public void ImageSend(int position){
        CustomBottomSheetDialogFragment test = new CustomBottomSheetDialogFragment();
        test.setPosition(position);
    }
}

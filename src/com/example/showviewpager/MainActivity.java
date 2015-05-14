package com.example.showviewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.view.showviewpager.ShowPager;

public class MainActivity extends Activity {
	private ShowPager adshow;
	private int imageids[]={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4};
	private List<ImageView> images=new ArrayList<ImageView>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adshow=(ShowPager) findViewById(R.id.adshowpager);
		for(int i=0;i<5;i++){
			ImageView iv=new ImageView(MainActivity.this);
			iv.setImageResource(imageids[i%imageids.length]);
			images.add(iv);
		}
		adshow.setViews(images);
	}


}

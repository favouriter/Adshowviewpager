package com.example.view.showviewpager;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.showviewpager.R;


public class ShowPager extends RelativeLayout{
	private View mainview;
	private ViewPager showpager;
	private RadioGroup  curtgroup;
//	private List<? extends View> views;
	public ShowPager(Context context) {
		super(context);
		initview(context);
	}

	public ShowPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initview(context);
		// TODO Auto-generated constructor stub
	}

	public ShowPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initview(context);
		// TODO Auto-generated constructor stub
	}

	private void initview(Context context) {
		mainview = LayoutInflater.from(context).inflate(R.layout.showpager, this,true);
		showpager=(ViewPager) mainview.findViewById(R.id.showpager);
		curtgroup=(RadioGroup) mainview.findViewById(R.id.curtgroup);
		curtgroup.removeAllViews();
//		curtgroup.setOnCheckedChangeListener(curchange);
		showpager.setOnPageChangeListener(pagerchange);
	}
	
	public void setViews(List<? extends View> views){
		for(int i=0;i<views.size();i++){
			RadioButton rb=new RadioButton(getContext());
			rb.setOnCheckedChangeListener(radiocheange);
			rb.setTag(i);
			curtgroup.addView(rb);
		}
		showpager.setAdapter(new ShowPagerAdapter(views));
		((CompoundButton) curtgroup.getChildAt(0)).setChecked(true);
	}
	
	OnCheckedChangeListener radiocheange=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				showpager.setCurrentItem((Integer) buttonView.getTag());
			}
		}
	};
	
	//ҳ��ı�����¼�
	OnPageChangeListener pagerchange=new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int index) {
			// TODO Auto-generated method stub
			System.out.println("�ı䰴ť");
			((RadioButton) curtgroup.getChildAt(index)).setChecked(true);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};

	class ShowPagerAdapter extends PagerAdapter{
		private List<? extends View> views;

		public ShowPagerAdapter(List<? extends View> views) {
			super();
			this.views = views;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(views.get(position));
//			super.destroyItem(container, position, object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(views.get(position));
			return views.get(position);
		}
		
	}
}

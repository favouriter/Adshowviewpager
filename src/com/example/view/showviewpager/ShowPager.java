package com.example.view.showviewpager;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.showviewpager.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;


public class ShowPager extends RelativeLayout{
	private View mainview;
	private ViewPager showpager;
	private LinearLayout points;
	private int Current;
	private Handler handler;
	private Timer timer;
	private CubeTransformer cubetransformer;
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
		inithandler();
		cubetransformer=new CubeTransformer();
		mainview = LayoutInflater.from(context).inflate(R.layout.showpager, this,true);
		showpager=(ViewPager) mainview.findViewById(R.id.showpager);
		points=(LinearLayout) mainview.findViewById(R.id.point);
		showpager.setOnPageChangeListener(pagerchange);
	}

	private void inithandler() {
		handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				showpager.setCurrentItem(msg.what);
				super.handleMessage(msg);
			}
			
		};
	}
	
	private void prointcheck(int index){
		for(int i=0;i<points.getChildCount();i++){
			if(index==i){
				((ImageView) points.getChildAt(i)).setImageResource(R.drawable.en_rb);
			}else{
				((ImageView) points.getChildAt(i)).setImageResource(R.drawable.un_rb);
			}
		}
	}
	
	/**
	 * @param imageids 控件集合
	 * @param size 标记点尺寸大小(像素)
	 * @param auto 是否自动播放
	 * @param space 自动播放间隔时间(单位秒)
	 */
	public void setViews(List<?extends View> imageids,int size,boolean auto,int space,boolean tran){
		LayoutParams mParams = new LayoutParams(size, size);
		for(int i=0;i<imageids.size();i++){
			ImageView iv=new ImageView(getContext());
			iv.setLayoutParams(mParams);
			iv.setImageResource(R.drawable.un_rb);
			points.addView(iv);
		}
		if(tran){
			showpager.setPageTransformer(true, cubetransformer);
			try {
                Field field = ViewPager.class.getDeclaredField("mScroller");
                field.setAccessible(true);
                FixedSpeedScroller scroller = new FixedSpeedScroller(showpager.getContext(),
                        new AccelerateInterpolator());
                field.set(showpager, scroller);
                scroller.setmDuration(500);
            } catch (Exception e) {
            }
		}
		showpager.setAdapter(new ShowPagerAdapter(imageids));
		Current=Integer.MAX_VALUE/2-(Integer.MAX_VALUE/2%imageids.size());
		showpager.setCurrentItem(Current);
		if(auto){
			timer=new Timer();
			TimerTask task=new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Current++;
					handler.sendEmptyMessage(Current);
				}
			};
			timer.schedule(task, 1000,1000*space);
		}
	}
	
	public void stoptimer(){
		timer.cancel();
	}
	
	OnPageChangeListener pagerchange=new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int index) {
			// TODO Auto-generated method stub
			Current=index;
			prointcheck(index%points.getChildCount());
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
//		private List<? extends View> views;
		private List<? extends View> views;

		public ShowPagerAdapter(List<? extends View> views) {
			super();
			this.views = views;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(views.get(position%views.size()));
//			super.destroyItem(container, position%views.size(), object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			ViewParent parent = views.get(position%views.size()).getParent();
			if(parent!=null){
				container.removeView(views.get(position%views.size()));
			}
			container.addView(views.get(position%views.size()));
			return views.get(position%views.size());
		}
		
	}
	
	public class FixedSpeedScroller extends Scroller {
	    private int mDuration = 1500;
	 
	    public FixedSpeedScroller(Context context) {
	        super(context);
	    }
	 
	    public FixedSpeedScroller(Context context, Interpolator interpolator) {
	        super(context, interpolator);
	    }
	 
	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	    }
	 
	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	    }
	 
	    public void setmDuration(int time) {
	        mDuration = time;
	    }
	 
	    public int getmDuration() {
	        return mDuration;
	    }
	}
}
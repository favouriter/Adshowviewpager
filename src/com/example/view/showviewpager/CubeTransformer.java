package com.example.view.showviewpager;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * ViewPager立方体切换样式
 * @author xym
 *
 */
public class CubeTransformer implements PageTransformer {

	@Override
	public void transformPage(View view, float position) {
		if(position <= 0) {//从左边进入
			//立方体切换
			//设置旋转中心点
			ViewHelper.setPivotX(view, view.getMeasuredWidth());
			ViewHelper.setPivotY(view, view.getMeasuredHeight()*0.5f);
			//只在Y轴做旋转操作
			ViewHelper.setRotationY(view, 90f*position);
			
			//旋转平移缩放切换
//			ViewHelper.setPivotX(view, view.getMeasuredWidth()*0.5f);
//			ViewHelper.setPivotY(view, view.getMeasuredHeight()*0.5f);
//			if(position>=-0.5f){
//				ViewHelper.setScaleX(view, 1f+1f*position);
//				ViewHelper.setScaleY(view, 1f+1f*position);
//			}else{
//				ViewHelper.setScaleX(view, 1f/2f);
//				ViewHelper.setScaleY(view, 1f/2f);
//			}
//			ViewHelper.setRotation(view, 360f*position);
		} else if(position <= 1) {//从右边进入
			//立方体切换
			//设置旋转中心点
			ViewHelper.setPivotX(view, 0);
			ViewHelper.setPivotY(view, view.getMeasuredHeight()*0.5f);
			//只在Y轴做旋转操作
			ViewHelper.setRotationY(view, 90f*position);
			
			//旋转平移缩放切换
//			ViewHelper.setPivotX(view, view.getMeasuredWidth()*0.5f);
//			ViewHelper.setPivotY(view, view.getMeasuredHeight()*0.5f);
//			if(position<=0.5f){
//				ViewHelper.setScaleX(view, 1-1f*position);
//				ViewHelper.setScaleY(view, 1-1f*position);
//			}else{
//				ViewHelper.setScaleX(view, 1f/2f);
//				ViewHelper.setScaleY(view, 1f/2f);
//			}
//			ViewHelper.setRotation(view, 360f*position);
		}
	}

}

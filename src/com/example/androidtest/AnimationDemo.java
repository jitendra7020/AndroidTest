package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;

public class AnimationDemo extends Activity {
	
	private boolean isHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.slide_testing1);
        
        
        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				View toolbar = findViewById(R.id.layout1);
				
				if(isHidden){
					expand(toolbar,1000);
					isHidden = false;
				}
				else{
					collapse(toolbar,1000);
					isHidden = true;
				}
			}
		});
    }
    
    
    
    public static void expand(final View v, int duration) {
        v.measure(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        final int targtetWidth = v.getMeasuredWidth();

        v.getLayoutParams().width = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().width = interpolatedTime == 1 ? LayoutParams.WRAP_CONTENT : (int)(targtetWidth * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(duration);
        v.startAnimation(a);
    }

    public static void collapse(final View v,int duration) {
        final int initialWidth = v.getMeasuredWidth();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().width = initialWidth - (int)(initialWidth * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(duration);
        v.startAnimation(a);
    }
}

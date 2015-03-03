package kelp.pagetransformer.transformer;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class TurnstilePageTransformer implements PageTransformer {
	private static final float DEGREE = 180.0f;
	private float rotation;
	
	@Override
	public void transformPage(View view, float position) {
		int pageWidth = view.getMeasuredWidth();
		int pageHeight = view.getMeasuredHeight();
		if (position < -1) { // [-Infinity,-1)
			 // This page is way off-screen to the left.
	         view.setAlpha(0);
	     } else if (position <= 0) { // [-1,0]
	    	 view.setAlpha(1);
	    	 if (position <= -0.5) view.setAlpha(0);
	         // Calculate the rotation degree
	         rotation = DEGREE * position;
	         // Set pivot point
	         view.setPivotX(pageWidth);
	         view.setPivotY(pageHeight / 2);
	         view.setRotationY(rotation);	         
	     } else if (position <= 1) { // (0,1]
		     view.setAlpha(0);
		     if (position <= 0.5) view.setAlpha(1);
		     // Calculate the rotation degree
		     rotation = DEGREE * position;
	         // Set pivot point
	         view.setPivotX(0);
	         view.setPivotY(pageHeight / 2);
	         view.setRotationY(rotation);
	      } else { // (1,+Infinity]
	          // This page is way off-screen to the right.
	          view.setAlpha(0);
	      }
	}

}

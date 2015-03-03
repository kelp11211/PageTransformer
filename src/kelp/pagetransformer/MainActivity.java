package kelp.pagetransformer;

import java.util.ArrayList;

import kelp.pagetransformer.transformer.*;
import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
	private Resources resource;
    private ActionBar actionBar;
    private ViewPager mViewPager;
    private Button footer_button0, footer_button1, footer_button2;
    private ArrayList<String> page_title;
    private int lastPosition;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        resource = getResources();
		actionBar = getActionBar();
		page_title = getPageTitle();
		actionBar.setBackgroundDrawable(resource.getDrawable(R.drawable.actionbar));
		
		mViewPager = (ViewPager) findViewById(R.id.view_pager);
		mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), page_title));
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {
        		doPageChangedSetting(position);
        		setButtonDrawerResource(lastPosition, resource.getDrawable(R.drawable.footer_item_unselected));
        		lastPosition =  mViewPager.getCurrentItem();
            }
			@Override
			public void onPageScrollStateChanged(int state) {
			}
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
        });
		// This three buttons are displayed at bottom of the activity so as to remind user presently is which page
		footer_button0 = (Button) findViewById(R.id.footer_button_page0);
		footer_button1 = (Button) findViewById(R.id.footer_button_page1);
		footer_button2 = (Button) findViewById(R.id.footer_button_page2);
		
		doPageChangedSetting(0);
		lastPosition =  mViewPager.getCurrentItem();
    }
    
    private void doPageChangedSetting(int position) {
    	mViewPager.setCurrentItem(position);
		actionBar.setTitle(page_title.get(position));
		setButtonDrawerResource(position, resource.getDrawable(R.drawable.footer_item_selected));
    }
	
	private void setButtonDrawerResource(int index, Drawable bg) {
		switch(index) {
			case 0:
				footer_button0.setBackground(bg);
				break;
			case 1:
				footer_button1.setBackground(bg);
				break;
			case 2:
				footer_button2.setBackground(bg);
				break;
		}
	}
	
	private ArrayList<String> getPageTitle() {
		ArrayList<String> titles = new ArrayList<String>();
		titles.add(resource.getString(R.string.title_page0));
		titles.add(resource.getString(R.string.title_page1));
		titles.add(resource.getString(R.string.title_page2));
		return titles;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    // Select different effects of page transformer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
        	case R.id.action_transformer_default:
        		mViewPager.setPageTransformer(true, new DefaultPageTransformer());
        		break;
        	case R.id.action_transformer_depth:
        		mViewPager.setPageTransformer(true, new DepthPageTransformer());
        		break;
        	case R.id.action_transformer_zoomout:
        		mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        		break;
        	case R.id.action_transformer_upperfan:
        		mViewPager.setPageTransformer(true, new UpperFanPageTransformer());
        		break;
        	case R.id.action_transformer_lowerfan:
        		mViewPager.setPageTransformer(true, new LowerFanPageTransformer());
        		break;
        	case R.id.action_transformer_cube:
        		mViewPager.setPageTransformer(true, new CubePageTransformer());
        		break;
        	case R.id.action_transformer_turnstile:
        		mViewPager.setPageTransformer(true, new TurnstilePageTransformer());
        		break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
	public void onBackPressed() {
		if (mViewPager.getCurrentItem() == 0) {
			super.onBackPressed();
		} else {
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
		}
	}
}

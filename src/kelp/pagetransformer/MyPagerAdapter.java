package kelp.pagetransformer;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
	private ArrayList<String> page_title;
	
	public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
	
	public MyPagerAdapter(FragmentManager fm, ArrayList<String> page_title) {
		this(fm);
		this.page_title = page_title;
	}
	
	@Override
	public int getCount() {
		if (page_title.size() != 0)
			return page_title.size();
		else
			return 0;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		
		switch(position) {
			case 0:
				fragment = new Page0();
				break;
			case 1:
				fragment = new Page1();
				break;
			case 2:
				fragment = new Page2();
				break;
		}
		
		return fragment;
	}
}

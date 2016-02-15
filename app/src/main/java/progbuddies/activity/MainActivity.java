package progbuddies.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import progbuddies.fragment.DecodeFragment;
import progbuddies.fragment.EncodeFragment;
import progbuddies.fragment.MapFragment;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 * @author Matteo Filia <matteo.compsci@gmail.com>
 */
public class MainActivity extends AppCompatActivity  {

	public static final int CUSTOM_TOP_BAR_COLOR_API = 21;

	ViewPager viewPager;
	Pager pager;
	ResourcesCompat rc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Create resources compat in order to get colors
		rc = new ResourcesCompat();

		//Create a FragmentStatePagerAdapter for switching tab content
		pager = new Pager(getSupportFragmentManager());

		//Get the ViewPager to display tab content
		viewPager = (ViewPager)findViewById(R.id.view_pager);
		viewPager.setAdapter(pager);

		//Add tabs and view pager to TabLayout
		TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

		//Setup tabs and tab fragment content
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.getTabAt(0).setText("Encode");
		tabLayout.getTabAt(1).setText("Decode");
		tabLayout.getTabAt(2).setText("Map");

		//Set top bar and navigation bar color to match theme, if the device  is at a high enough API to support this
		if(Build.VERSION.SDK_INT >= CUSTOM_TOP_BAR_COLOR_API){
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(rc.getColor(getResources(), R.color.bold, null));
			window.setNavigationBarColor(rc.getColor(getResources(), R.color.dull, null));
		}

	}

	class Pager extends FragmentStatePagerAdapter {

		public static final int FRAGMENTS = 3;

		public Pager(FragmentManager fm){
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position){
				case 0:
					return new EncodeFragment();
				case 1:
					return new DecodeFragment();
				case 2:
					return new MapFragment();
				default:
					return null; //Error, item position out of bounds
			}
		}

		@Override
		public int getCount() {
			return FRAGMENTS;
		}
	}
}



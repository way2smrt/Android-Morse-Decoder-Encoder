package progbuddies.morse;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

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

		//Set top bar and navigation bar color to match theme, if the device  is at a high enough API to support this
		if(Build.VERSION.SDK_INT >= CUSTOM_TOP_BAR_COLOR_API){
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(rc.getColor(getResources(), R.color.bold, null));
			window.setNavigationBarColor(rc.getColor(getResources(), R.color.dull, null));
		}

		//Setup toolbar (or action bar, as it was formely known)
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		toolbar.setTitle("Morse Decoder/Encoder");
		toolbar.setTitleTextColor(rc.getColor(getResources(), R.color.text_light, null));

		//Align EditText box top to center of title. This is needed to be done programmatically as we need both the title margin and text height
		EditText editText = (EditText)findViewById(R.id.edit_text);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				(int)getResources().getDimension(R.dimen.edit_text_height)
		);
		params.setMargins((int)getResources().getDimension(R.dimen.edit_text_margin_sides),
				(int)(getResources().getDimension(R.dimen.edit_text_title_margin_top)+(getResources().getDimension(R.dimen.edit_text_title_height)/2)),
				(int)getResources().getDimension(R.dimen.edit_text_margin_sides), 0);
		editText.setLayoutParams(params);
	}


	class Pager extends FragmentStatePagerAdapter {

		public static final int FRAGMENTS = 2;

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



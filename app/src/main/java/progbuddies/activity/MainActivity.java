package progbuddies.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import progbuddies.fragment.DecodeFragment;
import progbuddies.fragment.EncodeFragment;
import progbuddies.fragment.MapFragment;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 * @author Matteo Filia <matteo.compsci@gmail.com>
 */
public class MainActivity extends AppCompatActivity  {

	public static final int CUSTOM_TOP_BAR_COLOR_API = 21;

	Toolbar toolbar;
	ViewPager viewPager;
	Pager pager;
	ResourcesCompat rc;

	Dialog mapDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Setup toolbar
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

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

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_settings:
				Toast toast = Toast.makeText(this, "Setting activity will go here, with option to change speed", Toast.LENGTH_SHORT);
				toast.show();

				return true;
			case R.id.action_show_morse_map:
				mapDialog = new Dialog(this, R.style.AppTheme);

				//Get screen display metrics
				DisplayMetrics dm = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(dm);

				//Create proportionally size dialog
				WindowManager.LayoutParams params = mapDialog.getWindow().getAttributes();
				params.width = (dm.widthPixels/5)*3;
				params.height = (dm.heightPixels/5)*3;
				params.gravity = Gravity.CENTER;

				mapDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				mapDialog.setContentView(R.layout.layout_map);

				//Make sure that dialog hides on touch outside
				mapDialog.setCanceledOnTouchOutside(true);

				mapDialog.show();

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);

		return true;
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



package progbuddies.morse;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 *
 */
public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Create tabs
		FragmentTabHost tabHost = (FragmentTabHost)findViewById(R.id.tabHost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Encode"), Encode.class, null);
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Decode"), Decode.class, null);
	}
}

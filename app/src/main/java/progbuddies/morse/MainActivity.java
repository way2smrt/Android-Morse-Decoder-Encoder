package progbuddies.morse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 *
 */
public class MainActivity extends AppCompatActivity {

	Button encodeButton;
	Button decodeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		encodeButton = (Button) findViewById(R.id.encodeButton);
		decodeButton = (Button) findViewById(R.id.decodeButton);
	}

    public void beginEncodeActivity(View view) {
        Intent intent = new Intent(this, Encode.class);
        startActivity(intent);
    }

    public void beginDecodeActivity(View view) {
        Intent intent = new Intent(this, Decode.class);
        startActivity(intent);
    }

}

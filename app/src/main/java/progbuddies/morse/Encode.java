package progbuddies.morse;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import progbuddies.morsecode.Encoder;

public class Encode extends AppCompatActivity {


    Encoder encoder;
    EditText editText;
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode);
        encoder = new Encoder();
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.decodeButton);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void encodeText(View view) {
        String text = editText.getText().toString().toLowerCase();
        String encoded = encoder.encode(text);
        textView.setText(encoded);
    }


}

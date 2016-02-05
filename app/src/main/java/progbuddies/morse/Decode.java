package progbuddies.morse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import progbuddies.morsecode.Decoder;

public class Decode extends AppCompatActivity {

    Decoder decoder;
    EditText editText;
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decoder = new Decoder();
        setContentView(R.layout.activity_decode);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.decodeButton);
        textView = (TextView) findViewById(R.id.textView);
    }


    public void decodeText(View view) {
        String encodedText = editText.getText().toString().toLowerCase();
        String decodedText = decoder.decode(encodedText);
        textView.setText(decodedText);
    }
}

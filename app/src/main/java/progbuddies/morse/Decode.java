package progbuddies.morse;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import progbuddies.morsecode.Decoder;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 *
 */
public class Decode extends android.support.v4.app.Fragment {

    Decoder decoder;
    EditText editText;
    Button button;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decode, container, false);

        decoder = new Decoder();
        editText = (EditText) view.findViewById(R.id.editText);
        button = (Button) view.findViewById(R.id.decodeButton);
        textView = (TextView) view.findViewById(R.id.textView);

        return view;
    }

    public void decodeText(View view) {
        String encodedText = editText.getText().toString().toLowerCase();
        String decodedText = decoder.decode(encodedText);
        textView.setText(decodedText);
    }
}

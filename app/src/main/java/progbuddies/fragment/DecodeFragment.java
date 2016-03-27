package progbuddies.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import progbuddies.activity.R;
import progbuddies.morsecode.C;
import progbuddies.morsecode.Decoder;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 *
 */
public class DecodeFragment extends android.support.v4.app.Fragment {

    Decoder decoder;

    EditText morseInput;
    TextView textOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decode, container, false);
        decoder = new Decoder();


        textOutput = (TextView) view.findViewById(R.id.text_output);

        morseInput = (EditText) view.findViewById(R.id.morse_input);
        morseInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateOutput(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    public void updateOutput(String text){
        //Update output every character or every deleted character
        textOutput.setText(decoder.decode(text));
    }
}

package progbuddies.fragment;

import android.os.Bundle;
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
    TextView textWindow;
    Button dotButton;
    Button dashButton;
    Button deleteButton;
    Button nextWordButton;
    Button nextCharacterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decode, container, false);
        decoder = new Decoder();
        textWindow = (TextView) view.findViewById(R.id.edit_text);

        dotButton = (Button)view.findViewById(R.id.dotButton);

        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dot(v);
            }
        });

        dashButton = (Button) view.findViewById(R.id.dashButton);

        dashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dash(v);
            }
        });

        deleteButton = (Button) view.findViewById(R.id.delete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });

        nextCharacterButton = (Button) view.findViewById(R.id.nextCharacterButton);

        nextCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextCharacterButton(v);
            }
        });

        nextWordButton = (Button) view.findViewById(R.id.nextWordButton);

        nextWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextWord(v);
            }
        });

        return view;
    }


    //TODO: The way the following is implemented is not the most optimal way, however it is fine for our testing purposes.
    public void dot(View view){
        String current = textWindow.getText().toString();
        String updated = current.concat(".");
        textWindow.setText(updated);
    }

    public void dash(View view) {
        String current = textWindow.getText().toString();
        String updated = current.concat("-");
        textWindow.setText(updated);
    }


    public void nextCharacterButton(View view) {
        String current = textWindow.getText().toString();
        String updated = current.concat(" ");
        textWindow.setText(updated);
    }

    public void nextWord(View view) {
        String current = textWindow.getText().toString();
        String updated = current.concat(C.WORD_SEPERATOR);
        textWindow.setText(updated);
    }


    public void delete(View view) {
        String current = textWindow.getText().toString();
        String updated = "";
        if(current.length() <= 1) {
            textWindow.setText("");
            return;
        } else {
             updated = textWindow.getText().toString().substring(0, current.length()-1);
        }
        updated = updated.trim();
        textWindow.setText(updated);
    }


}

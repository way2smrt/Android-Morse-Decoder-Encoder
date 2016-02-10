package progbuddies.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import progbuddies.activity.R;
import progbuddies.morsecode.Encoder;
/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 *
 */
public class EncodeFragment extends android.support.v4.app.Fragment {

    Encoder encoder;
    EditText editText;
    Button button;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encode, container, false);
        encoder = new Encoder();
        editText = (EditText) view.findViewById(R.id.editText);

        button = (Button) view.findViewById(R.id.encodeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encodeText(v);
            }
        });

        textView = (TextView) view.findViewById(R.id.textView);

        return view;
    }


    //TODO: Either remove all special characters from text while encoding or add the mapping currently only a-z and 0-9 are supported characters

    public void encodeText(View view) {
        String text = editText.getText().toString().toLowerCase();
        String encoded = encoder.encode(text);
        textView.setText(encoded);
    }


}

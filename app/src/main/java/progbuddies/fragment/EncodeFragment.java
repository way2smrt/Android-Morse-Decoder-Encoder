package progbuddies.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import progbuddies.activity.R;
import progbuddies.flashlight.FlashExecutor;
import progbuddies.morsecode.Encoder;
/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 *
 */
public class EncodeFragment extends android.support.v4.app.Fragment {

    Encoder encoder;
    EditText editText;
    ImageButton encodeMessageButton;
    CheckBox flashMessageButton;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encode, container, false);

        encoder = new Encoder();

        editText = (EditText) view.findViewById(R.id.editText);

        textView = (TextView) view.findViewById(R.id.text_output);

        encodeMessageButton = (ImageButton) view.findViewById(R.id.playButton);

        encodeMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encodeText();
            }
        });

        flashMessageButton = (CheckBox) view.findViewById(R.id.flashMessageButton);

        //Check that the current device does have flash, if not don't set a callback for the button and hide it.
        if(FlashExecutor.doesDeviceHaveFlash(getContext())){
            flashMessageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flashMessage();
                }
            });
        } else {
            flashMessageButton.setVisibility(View.INVISIBLE);
        }

        return view;
    }


    //TODO: Either remove all special characters from text while encoding or add the mapping currently only a-z and 0-9 are supported characters
    private void encodeText() {
        String text = editText.getText().toString().toLowerCase();
        String encoded = encoder.encode(text);
        textView.setText(encoded);
    }


    private void flashMessage(){
        FlashExecutor executor = new FlashExecutor();
        executor.setContext(getContext()); //Set the current context to this activity
        executor.setStringToEncode(editText.getText().toString().trim());
        Thread T = new Thread(executor);
        T.start();
    }
}

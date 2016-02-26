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
    ImageButton stopMessageButton;

    CheckBox flashToggle;
    CheckBox vibrationToggle;

    FlashExecutor executor;

    boolean flashEnabled = false;

    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encode, container, false);

        encoder = new Encoder();

        editText = (EditText) view.findViewById(R.id.editText);
        textView = (TextView) view.findViewById(R.id.text_output);

        encodeMessageButton = (ImageButton) view.findViewById(R.id.playButton);
        stopMessageButton = (ImageButton) view.findViewById(R.id.stopButton);

        flashToggle = (CheckBox) view.findViewById(R.id.flashMessageButton);
        vibrationToggle = (CheckBox) view.findViewById(R.id.vibrateMessageButton);

        //Check that the current device does have flash, if not hide it.
        if(FlashExecutor.doesDeviceHaveFlash(getContext())){
            flashEnabled = true;
        } else {
            flashEnabled = false;
            flashToggle.setVisibility(View.INVISIBLE);
        }

        //Set callback for when message is to encoded and possibly sent via flash or vibration
        encodeMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encodeText();
                sendMessage();
            }
        });

        //Set callback to stop sending a message
        stopMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMessage();
            }
        });

        return view;
    }


    //TODO: Either remove all special characters from text while encoding or add the mapping currently only a-z and 0-9 are supported characters
    private void encodeText() {
        String text = editText.getText().toString().toLowerCase();
        String encoded = encoder.encode(text);
        textView.setText(encoded);
    }

	/**
     * Stops sending the message via flash or vibration, if it is active
     */
    private void stopMessage(){
        if(executor != null){
            executor.stop();
            executor = null;
        }

        //TODO implement vibration message stopping
    }

	/**
	 * Encodes the message, and also sends it via flash or vibration if it is toggled.
     */
    private void sendMessage(){
        if(!flashToggle.isChecked() && flashEnabled){
            executor = new FlashExecutor();
            executor.setContext(getContext()); //Set the current context to this activity
            executor.setStringToEncode(editText.getText().toString().trim());
            Thread T = new Thread(executor);
            T.start();
        }

        if(!vibrationToggle.isChecked()) {
            //TODO implement vibration message sending
        }
    }
}

package progbuddies.fragment;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import progbuddies.activity.R;
import progbuddies.flashlight.MessageSender;
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

    Vibrator vibrator;

    MessageSender sender;

    boolean flashEnabled = false;

    TextView textView;
    int encodedTextLen = 0;
    String encodedText;

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

        vibrator = (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);

        //Check that the current device does have flash, if not hide it.
        if(MessageSender.doesDeviceHaveFlash(getContext())){
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
                textView.setText(encodedText);
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
        encodedText = encoder.encode(text);
    }

	/**
     * Stops sending the message via flash or vibration, if it is active
     */
    private void stopMessage(){
        if(sender != null){
            sender.stop();
            sender = null;
        }
    }

	/**
	 * Encodes the message, and also sends it via flash or vibration if it is toggled.
     */
    private void sendMessage(){
        if(!flashToggle.isChecked() || !vibrationToggle.isChecked()){
            if(sender != null){
                //To ensure we don't have multiple threads running at once, stop any previous one
                sender.stop();
            }

            encodedTextLen = 0;

            sender = new MessageSender();
            sender.setVibrator(vibrator);
            sender.setContext(getContext()); //Set the current context to this activity
            sender.setStringToEncode(editText.getText().toString().trim());
            sender.nextChar();
            Thread T = new Thread(sender);

            if(!flashToggle.isChecked() && vibrationToggle.isChecked()){
                //Flash only
                sender.setMode(MessageSender.Mode.FLASH);
            } else if(flashToggle.isChecked() && !vibrationToggle.isChecked()){
                //Vibration only
                sender.setMode(MessageSender.Mode.VIBRATE);
            } else {
                //Both
                sender.setMode(MessageSender.Mode.BOTH);
            }
            //Start sending message
            T.start();
        }
    }
}

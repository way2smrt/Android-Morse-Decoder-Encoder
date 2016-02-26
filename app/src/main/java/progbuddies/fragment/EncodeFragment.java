package progbuddies.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
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
    ImageButton stopExecutionButton;
    CheckBox flashToggle;
    CheckBox vibrationToggle;
    FlashExecutor executor;
    TextView textOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encode, container, false);
        encoder = new Encoder();
        editText = (EditText) view.findViewById(R.id.editText);
        textOutput = (TextView) view.findViewById(R.id.text_output);
        encodeMessageButton = (ImageButton) view.findViewById(R.id.playButton);
        stopExecutionButton = (ImageButton) view.findViewById(R.id.stopButton);
        flashToggle = (CheckBox) view.findViewById(R.id.flashMessageButton);
        vibrationToggle = (CheckBox) view.findViewById(R.id.vibrateMessageButton);
        executor = new FlashExecutor();

        if(!deviceHasFlash(getContext())){
            flashToggle.setVisibility(View.INVISIBLE);
        }

        encodeMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encode();
            }
        });

        stopExecutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopExecution();
            }
        });

        return view;
    }


    private void encode() {

        String text = editText.getText().toString().toLowerCase();

        String encoded = encoder.encode(text);

        textOutput.setText(encoded);

        if(deviceShouldFlash()){
           flashMessage();
        }

        if(deviceShouldVibrate()) {
            vibrateMessage();
        }

    }


    private void flashMessage() {
        executor.setStringToEncode(editText.getText().toString().trim());
        executor.start();
    }


    private void stopExecution(){
        executor.stop();
    }


    private void vibrateMessage() {

    }


    private boolean deviceShouldFlash() {
      return !flashToggle.isChecked() && deviceHasFlash(getContext());
    }

    private boolean deviceShouldVibrate() {
        return !vibrationToggle.isChecked();
    }


    private boolean deviceHasFlash(Context context){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }


}

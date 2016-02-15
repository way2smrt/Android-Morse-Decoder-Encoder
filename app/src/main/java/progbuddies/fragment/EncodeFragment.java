package progbuddies.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
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

    Vibrator v;
    CameraManager cm;

    static Camera cameraOld;

    boolean hasFlashlight;
    boolean usesNewAPI;

    public static final int NEW_CAMERA_API = 21;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encode, container, false);
        encoder = new Encoder();

        //TODO:Implement vibrator and flash encoder
        /*
        //Get system vibrator and camera service
        v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);

        //Check if device is running high enough version for new camera API
        usesNewAPI = (Build.VERSION.SDK_INT >= NEW_CAMERA_API);

        if(usesNewAPI) {
            hasFlashlight = getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

            if (hasFlashlight) {
                cm = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
            }
        } else {
            Camera cameraOld = Camera.open();
            Camera.Parameters p = cameraOld.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        }
        */

        return view;
    }


    //TODO: Either remove all special characters from text while encoding or add the mapping currently only a-z and 0-9 are supported characters

    public void encodeText(View view) {
        String text = editText.getText().toString().toLowerCase();
        String encoded = encoder.encode(text);
        textView.setText(encoded);
    }
}

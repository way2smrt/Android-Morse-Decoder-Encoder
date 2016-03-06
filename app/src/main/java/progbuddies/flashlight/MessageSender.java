package progbuddies.flashlight;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Vibrator;
import android.util.Log;

import progbuddies.morsecode.C;
import progbuddies.morsecode.Encoder;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 */
public class MessageSender implements Runnable{

    private static final String TAG = "FlashExecutor";

    public enum Mode{VIBRATE, FLASH, BOTH};
    Mode mode = Mode.BOTH;

    private static STATE state = STATE.AVAILABLE;

    private String stringToEncode = "";
    private Context context;

    boolean continueFlash = true;

    Vibrator vibrator;

    public void setStringToEncode(String stringToEncode) {
        this.stringToEncode = stringToEncode;
    }

	/**
     * Checks whether or not the device has flash, and thus can or cannot use the FlashExecutor.
     */
    public static boolean doesDeviceHaveFlash(Context context){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    public void run() {
        /*
        Make sure to call doesDeviceHaveFlash() if you want to ensure that the current device can use the FlashExecutor.
         */

        if(state == STATE.EXECUTING) {
            return;
        }
        state = STATE.EXECUTING;

        Camera cam = Camera.open();

        char[] stringToEncodeCharArray = getStringToEncodeCharArray();

        for(char c : stringToEncodeCharArray) {
            if(continueFlash){
                try{
                    if(c == C.DOT) {
                        if(mode == Mode.VIBRATE){
                            vibrator.vibrate(C.DOT_TIME_INTERVAL);
                            Thread.sleep(C.DASH_TIME_INTERVAL);
                        } else if(mode == Mode.BOTH){
                            vibrator.vibrate(C.DOT_TIME_INTERVAL);
                            
                            flashOn(cam);
                            Thread.sleep(C.DOT_TIME_INTERVAL);
                            flashOff(cam);
                        } else if(mode == Mode.FLASH){
                            flashOn(cam);
                            Thread.sleep(C.DOT_TIME_INTERVAL);
                            flashOff(cam);
                        }
                    } else if(c == C.DASH) {
                        if(mode == Mode.VIBRATE){
                            vibrator.vibrate(C.DASH_TIME_INTERVAL);
                            Thread.sleep(C.DASH_TIME_INTERVAL);
                        } else if(mode == Mode.BOTH){
                            vibrator.vibrate(C.DASH_TIME_INTERVAL);

                            flashOn(cam);
                            Thread.sleep(C.DASH_TIME_INTERVAL);
                            flashOff(cam);
                        } else if(mode == Mode.FLASH){
                            flashOn(cam);
                            Thread.sleep(C.DASH_TIME_INTERVAL);
                            flashOff(cam);
                        }
                    } else if(c == C.CHARACTER_SEPERATOR) {
                        Thread.sleep(C.CHARACTER_SEPERATOR_TIME_INTERVAL);
                    } else if(c == C.WORD_SEPERATOR_PLACEHOLDER) {
                        Thread.sleep(C.WORD_SEPERATOR_TIME_INTERVAL);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();

                    //Stop encoding via flashlight when interrupted
                    break;
                }
            } else {
                //Stop encoding via flashlight if something tells us to stop
                break;
            }
        }
        cam.release();
        state = STATE.AVAILABLE;
    }

    public void stop(){
        continueFlash = false;
    }

    public void setContext(Context context){
        this.context = context;
    }

    private void flashOn(Camera cam) {
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();

    }

    private void flashOff(Camera cam) {
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        cam.setParameters(p);
        cam.stopPreview();
    }

    public void setVibrator(Vibrator vibrator){
        this.vibrator = vibrator;
    }

    public void setMode(Mode mode){
        this.mode = mode;
    }

    private char[] getStringToEncodeCharArray() {
        Encoder encoder = new Encoder();
        stringToEncode = encoder.encode(stringToEncode);
        stringToEncode = stringToEncode.replace(C.WORD_SEPERATOR, String.valueOf(C.WORD_SEPERATOR_PLACEHOLDER));
        return stringToEncode.toCharArray();
    }

    private enum STATE {
        AVAILABLE, EXECUTING
    }




}

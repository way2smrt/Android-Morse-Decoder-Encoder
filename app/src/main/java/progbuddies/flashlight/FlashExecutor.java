package progbuddies.flashlight;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

import progbuddies.morsecode.C;
import progbuddies.morsecode.Encoder;

/**
 * @author Bilal Tahir <bilal@bilaltahir.com>
 */
public class FlashExecutor implements Runnable{

    private Thread executionThread;
    private static final String TAG = "FlashExecutor";
    private static STATE state = STATE.AVAILABLE;
    private String stringToEncode = "";

    public void start() {
        if(state == STATE.EXECUTING) {
            return;
        }
        executionThread = new Thread(this);
        executionThread.start();
    }


    public void setStringToEncode(String stringToEncode) {
        this.stringToEncode = stringToEncode;
    }

    @Override
    public void run() {

        if(state == STATE.EXECUTING) {
            return;
        }

        state = STATE.EXECUTING;
        Camera cam = Camera.open();

        char[] stringToEncodeCharArray = getStringToEncodeCharArray();

        for(char c : stringToEncodeCharArray) {

            if(!executionThread.isInterrupted()){
                try{
                    if(c == C.DOT) {
                        flashOn(cam);
                        Thread.sleep(C.DOT_TIME_INTERVAL);
                        flashOff(cam);
                    } else if(c == C.DASH) {
                        flashOn(cam);
                        Thread.sleep(C.DASH_TIME_INTERVAL);
                        flashOff(cam);
                    } else if(c == C.CHARACTER_SEPERATOR) {
                        Thread.sleep(C.CHARACTER_SEPERATOR_TIME_INTERVAL);
                    } else if(c == C.WORD_SEPERATOR_PLACEHOLDER) {
                        Thread.sleep(C.WORD_SEPERATOR_TIME_INTERVAL);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Broke out of loop");
                    break;
                }
            }
        }
        cam.release();
        state = STATE.AVAILABLE;
    }

    public void stop(){
        executionThread.interrupt();
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

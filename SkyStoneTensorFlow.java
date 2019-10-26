package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;


@Autonomous(name="SkyStoneTensorFlow", group="Vision Iterative Opmode")
@Disabled
public abstract class SkyStoneTensorFlow extends OpMode{

    private final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private final String LABEL_SKYSTONE = "Skystone";
    private final String LABEL_STONE = "Stone";

    private final String VUFORIA_KEY = "\"\\u000BAX0izzj/////AAABmVgryeGlbUKks9dMznAo70xgFtGB7Wf3e2Z2K22yhzmjQm6jgNG5UPMmlm7W6hmaQrMYRGeDeAzdz\" +\n" +
            "                    \"7duhjFD81BoU0BhNyuJm6r2uDa/xJSfCZstPdmi2kgN7BS8lOViax7sXaWO6/2hl1de3u5/nXQv+EYuwE4gbN3zvodRrKNjdwcJ3g\" +\n" +
            "                    \"7rgJbsh2zzi0UZsULVscwyFOOI5paqcaToG026eSAc2AHK9Mbv+6NesBprHhZTOGKpSAcS8zR3l4xfP0Kx8FiwERSe\" +\n" +
            "                    \"+lcLqVWaLQje2C/JACW2l8tv9Mib6xhd8pQodBOFrvqkVphATu0zxOEINJyIh6ZRWbgErHwUt/J3zetStCkIKhA5+Ut6bcdO\\n\";\n";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    private double stoneLocation = 0;

    private boolean isStoneDetected = false;

    private String objectLabel;


    public SkyStoneTensorFlow(HardwareMap hwMap){

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = "\u000BAX0izzj/////AAABmVgryeGlbUKks9dMznAo70xgFtGB7Wf3e2Z2K22yhzmjQm6jgNG5UPMmlm7W6hmaQrMYRGeDeAzdz" +
                "7duhjFD81BoU0BhNyuJm6r2uDa/xJSfCZstPdmi2kgN7BS8lOViax7sXaWO6/2hl1de3u5/nXQv+EYuwE4gbN3zvodRrKNjdwcJ3g" +
                "7rgJbsh2zzi0UZsULVscwyFOOI5paqcaToG026eSAc2AHK9Mbv+6NesBprHhZTOGKpSAcS8zR3l4xfP0Kx8FiwERSe" +
                "+lcLqVWaLQje2C/JACW2l8tv9Mib6xhd8pQodBOFrvqkVphATu0zxOEINJyIh6ZRWbgErHwUt/J3zetStCkIKhA5+Ut6bcdO\n";
        ;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        //parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1"); //comment out cameraDirection if you are using webcame

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        if(ClassFactory.getInstance().canCreateTFObjectDetector()){
            int tfodMonitorViewId = hwMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hwMap.appContext.getPackageName());

            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfodParameters.minimumConfidence = 0.8;
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_STONE, LABEL_SKYSTONE);
        }

        if(tfod != null) tfod.activate();
    }

    public void updateDetector(){
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        if(updatedRecognitions != null) {
            for (Recognition recognition : updatedRecognitions) {
                objectLabel = recognition.getLabel();
                if(recognition.getLabel().equals(LABEL_SKYSTONE)) {
                    stoneLocation = (recognition.getLeft() + recognition.getRight()) / 2;
                    isStoneDetected = true;
                }
                else isStoneDetected = false;
            }
        }
    }

    public boolean isStoneDetected(){return isStoneDetected;}

    public double getStoneLocation(){return stoneLocation;}

    public String getLabel(){return objectLabel;}

    public void shutdown(){
        tfod.shutdown();
    }

}





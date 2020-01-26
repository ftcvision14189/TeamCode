package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;


@Autonomous(name = "Skystone Detection Test", group = "Tensorflow Test")
public class TensorFlowTelemetryTest extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    private static final String VUFORIA_KEY =
            "AfKn1r7/////AAABmWYJKR8IEUVuvhd1CRaKnow6zzuk0sRb17iqxYCGooYanQKgP4a1bRlsj1ITJaNFgwZUgQ7DDIdTaLtrMUVoLYgZVL4a2AwuDWFUSCKDeh8aRj47751pt6FH/Za3PcdZxEUsSbrwlt/kGmJjULFctVYZk71/PhChEfbeEpk0sFUQqutbxSnZPObYhF6NzliuZQ512oAtCsI/VKP7ctDl6ySRF8DO+1RBDwE9vvN4NlUeDf+wgFwhoEtZ+dRef8TPLXGDC8xHMFRTrsZhTfl7DLGQjkLCVOx40RtUqcFNOl46qgtM5liNuEAE6NcE+/096Q3Q5wqpIBOyimHFa9DeOIAOo8IyD7dD6amjAXtBJsl2";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {

        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
        }

        telemetry.addData("Vuforia + Tensorflow initialized.", "Press play to begin test.");
        telemetry.update();
        waitForStart();



        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData("Label", recognition.getLabel());
                            telemetry.addData("Center Coordinates:", "(%.03f, %.03f)",
                                ((recognition.getLeft() + recognition.getRight()) / 2),
                                ((recognition.getBottom() + recognition.getTop()) / 2));
                        telemetry.addData("Confidence:", recognition.getConfidence());
                      }
                      telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }


    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }


    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
       tfodParameters.minimumConfidence = 0.8;
       tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
       tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}

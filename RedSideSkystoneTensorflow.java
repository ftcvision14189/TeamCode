package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;


@Autonomous(name = "Red Side Skystone Delivery", group = "Tensorflow Auto")
public class RedSideSkystoneTensorflow extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    private static final String VUFORIA_KEY =
            "AfKn1r7/////AAABmWYJKR8IEUVuvhd1CRaKnow6zzuk0sRb17iqxYCGooYanQKgP4a1bRlsj1ITJaNFgwZUgQ7DDIdTaLtrMUVoLYgZVL4a2AwuDWFUSCKDeh8aRj47751pt6FH/Za3PcdZxEUsSbrwlt/kGmJjULFctVYZk71/PhChEfbeEpk0sFUQqutbxSnZPObYhF6NzliuZQ512oAtCsI/VKP7ctDl6ySRF8DO+1RBDwE9vvN4NlUeDf+wgFwhoEtZ+dRef8TPLXGDC8xHMFRTrsZhTfl7DLGQjkLCVOx40RtUqcFNOl46qgtM5liNuEAE6NcE+/096Q3Q5wqpIBOyimHFa9DeOIAOo8IyD7dD6amjAXtBJsl2";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    float LEFT_BLOCK_X = 312;
    float LEFT_BLOCK_Y = 243;
    float MIDDLE_BLOCK_X = 318;
    float MIDDLE_BLOCK_Y = 242;
    float RIGHT_BLOCK_X = 446;
    float RIGHT_BLOCK_Y = 249;

    int BLOCK_LOCATION_TOLERANCE = 20;

    @Override
    public void runOpMode() {

        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
        }

        telemetry.addData("Status", "Ready to begin auto.");
        telemetry.update();
        waitForStart();

        telemetry.update();

        if (tfod != null) {
            float centerX;
            float centerY;
            int skystonePosition;
            List<Recognition> updatedRecognitions = null;
            int i = 0;
            while (updatedRecognitions == null) {
                updatedRecognitions = tfod.getUpdatedRecognitions();
                telemetry.addData("Status", "Scanning for Skystone (%i)", i);
                telemetry.addData("TFOD", tfod.getUpdatedRecognitions());
                telemetry.update();
                i++;
            }

                /*i = 0;
                while (i < 50) {
                    updatedRecognitions = tfod.getUpdatedRecognitions();
                    telemetry.addData("Status", "Improving recognition (%i)", i);
                    telemetry.addData("TFOD", tfod.getUpdatedRecognitions());
                    telemetry.update();
                    i++;
                }*/

            for (Recognition recognition : updatedRecognitions) {
                if (recognition.getLabel().equals("Skystone")) {
                    centerX = ((recognition.getRight() + recognition.getLeft()) / 2);
                    centerY = ((recognition.getTop() + recognition.getBottom()) / 2);
                    if ((Math.abs(centerX - LEFT_BLOCK_X) < BLOCK_LOCATION_TOLERANCE) && ((Math.abs(centerY - LEFT_BLOCK_Y) > BLOCK_LOCATION_TOLERANCE))) {
                        telemetry.addData("Skystone Detected", "Position 1");
                        skystonePosition = 0;
                    } else if ((Math.abs(centerX - MIDDLE_BLOCK_X) < BLOCK_LOCATION_TOLERANCE) && ((Math.abs(centerY - MIDDLE_BLOCK_Y) > BLOCK_LOCATION_TOLERANCE))) {
                        telemetry.addData("Skystone Detected", "Position 2");
                        skystonePosition = 1;
                    } else if ((Math.abs(centerX - RIGHT_BLOCK_X) < BLOCK_LOCATION_TOLERANCE) && ((Math.abs(centerY - RIGHT_BLOCK_Y) > BLOCK_LOCATION_TOLERANCE))) {
                        telemetry.addData("Skystone Detected", "Position 3");
                        skystonePosition = 2;
                    } else {
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

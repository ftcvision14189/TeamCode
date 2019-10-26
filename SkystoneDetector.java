package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class SkystoneDetector {

    private final String TFOD_MODEL = "Skystone.tflite";
    private final String LABEL_SKYSTONE = "Skystone";
    private final String LABEL_STONE = "Stone";

    private final String VUFORIA_KEY = "AfKn1r7/////AAABmWYJKR8IEUVuvhd1CRaKnow6zzuk0sRb17iqxYCGooYanQKgP4a1bRlsj1ITJaNFgwZUgQ7DDIdTaLtrMUVoLYgZVL4a2AwuDWFUSCKDeh8aRj47751pt6FH/Za3PcdZxEUsSbrwlt/kGmJjULFctVYZk71/PhChEfbeEpk0sFUQqutbxSnZPObYhF6NzliuZQ512oAtCsI/VKP7ctDl6ySRF8DO+1RBDwE9vvN4NlUeDf+wgFwhoEtZ+dRef8TPLXGDC8xHMFRTrsZhTfl7DLGQjkLCVOx40RtUqcFNOl46qgtM5liNuEAE6NcE+/096Q3Q5wqpIBOyimHFa9DeOIAOo8IyD7dD6amjAXtBJsl2";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private double stoneLocation = 0;
    private boolean isStoneDetected = false;

    public SkystoneDetector(HardwareMap hwMap) {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamNAme.class, "Webcam 1");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        if(ClassFactory.getInstance().canCreateTFObjectDetector()) {
            int tfodMonitorViewId = hwMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hwMap.appContext.getPackageName());
        }

    }

}


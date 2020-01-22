package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class TensorFlowSkystone {

    /**
     * This 2019-2020 OpMode illustrates the basics of using the TensorFlow Object Detection API to
     * determine the position of the Skystone game elements.
     *
     * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
     * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
     *
     * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
     * is explained below.
     */
    @Autonomous(name = "Object Detection Webcam", group = "Concept")
  //  @Disabled
    public class Vision_ConceptTensorFlowObjectDetectionWebcam1 extends LinearOpMode
    {
        DcMotor leftFrontMotor = null;
        DcMotor rightFrontMotor = null;
        DcMotor leftRearMotor = null;
        DcMotor rightRearMotor = null;
        DcMotor liftMotor = null;
        Servo claw = null;
        Servo rightFang = null;
        Servo leftFang = null;

        private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
        private static final String LABEL_FIRST_ELEMENT = "Stone";
        private static final String LABEL_SECOND_ELEMENT = "Skystone";
        private static final String VUFORIA_KEY =
                "\u000BAX0izzj/////AAABmVgryeGlbUKks9dMznAo70xgFtGB7Wf3e2Z2K22yhzmjQm6jgNG5UPMmlm7W6hmaQrMYRGeDeAzdz" +
                        "7duhjFD81BoU0BhNyuJm6r2uDa/xJSfCZstPdmi2kgN7BS8lOViax7sXaWO6/2hl1de3u5/nXQv+EYuwE4gbN3zvodRrKNjdwcJ3g" +
                        "7rgJbsh2zzi0UZsULVscwyFOOI5paqcaToG026eSAc2AHK9Mbv+6NesBprHhZTOGKpSAcS8zR3l4xfP0Kx8FiwERSe" +
                        "+lcLqVWaLQje2C/JACW2l8tv9Mib6xhd8pQodBOFrvqkVphATu0zxOEINJyIh6ZRWbgErHwUt/J3zetStCkIKhA5+Ut6bcdO\n";
        private VuforiaLocalizer vuforia;
        private TFObjectDetector tfod;

        /**
         * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
         * Detection engine.
         */


        @Override
        public void runOpMode() {

            initVuforia();

            if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
                initTfod();
            } else {
                telemetry.addData("Sorry!", "This device is not compatible with TFOD");
            }

            /**
             * Activate TensorFlow Object Detection before we wait for the start command.
             * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
             **/
            if (tfod != null) {
                tfod.activate();
            }

            leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
            rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
            leftRearMotor = hardwareMap.dcMotor.get("leftRear");
            rightRearMotor = hardwareMap.dcMotor.get("rightRear");
            liftMotor = hardwareMap.dcMotor.get("lift");
            claw = hardwareMap.servo.get("claw");
            rightFang = hardwareMap.servo.get("rightServo");
            leftFang = hardwareMap.servo.get("leftServo");

            /** Wait for the game to begin */
            telemetry.addData(">", "Press Play to start op mode");
            telemetry.update();
            waitForStart();

            leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
            rightRearMotor.setDirection(DcMotor.Direction.FORWARD);

            leftFront.setPower(-0.75);
            rightFront.setPower(0.75);
            leftRear.setPower(-0.75);
            rightRear.setPower(0.75);
            sleep(700);

            leftFront.setPower(-0.75);
            rightFront.setPower(-0.75);
            leftRear.setPower(0.75);
            rightRear.setPower(0.75);
            sleep(700);



            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
            // first.
            initVuforia();

            if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
                initTfod();
            } else {
                telemetry.addData("Sorry!", "This device is not compatible with TFOD");
            }

            /**
             * Activate TensorFlow Object Detection before we wait for the start command.
             * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
             **/
            if (tfod != null) {
                tfod.activate();
            }

            /** Wait for the game to begin */
            telemetry.addData(">", "Press Play to start op mode");
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
                            int i = 0;
                            for (Recognition recognition : updatedRecognitions) {
                                telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                                telemetry.addData(String.format("left,top (%d)", i), "%.03f , %.03f",
                                        recognition.getLeft(), recognition.getTop());
                                leftFront.setPower(-0.75);
                                rightFront.setPower(0.75);
                                leftRear.setPower(-0.75);
                                rightRear.setPower(0.75);
                                sleep(700);
                                telemetry.addData(String.format("right,bottom (%d)", i), "%.03f , %.03f",
                                        recognition.getRight(), recognition.getBottom());

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

        /**
         * Initialize the Vuforia localization engine.
         */
        private void initVuforia() {
            /*
             * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
             */
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam1");

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

            // Loading trackables is not necessary for the TensorFlow Object Detection engine.
        }

        /**
         * Initialize the TensorFlow Object Detection engine.
         */
        private void initTfod() {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfodParameters.minimumConfidence = 0.8;
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
        }
    }
}

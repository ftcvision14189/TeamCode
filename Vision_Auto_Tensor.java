package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

  @Autonomous(name="Vision_Auto_Tensor", group="Exercises")
@Disabled
    public class Vision_Auto_Tensor extends LinearOpMode {
        private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
        private static final String LABEL_FIRST_ELEMENT = "Stone";
        private static final String LABEL_SECOND_ELEMENT = "Skystone";
        DcMotor leftMotor;
        DcMotor rightMotor;


        private static final String VUFORIA_KEY =
                "\u000BAX0izzj/////AAABmVgryeGlbUKks9dMznAo70xgFtGB7Wf3e2Z2K22yhzmjQm6jgNG5UPMmlm7W6hmaQrMYRGeDeAzdz" +
                        "7duhjFD81BoU0BhNyuJm6r2uDa/xJSfCZstPdmi2kgN7BS8lOViax7sXaWO6/2hl1de3u5/nXQv+EYuwE4gbN3zvodRrKNjdwcJ3g" +
                        "7rgJbsh2zzi0UZsULVscwyFOOI5paqcaToG026eSAc2AHK9Mbv+6NesBprHhZTOGKpSAcS8zR3l4xfP0Kx8FiwERSe" +
                        "+lcLqVWaLQje2C/JACW2l8tv9Mib6xhd8pQodBOFrvqkVphATu0zxOEINJyIh6ZRWbgErHwUt/J3zetStCkIKhA5+Ut6bcdO\n";

        private VuforiaLocalizer vuforia;
        private TFObjectDetector tfod;


        private void initVuforia() {
            /*
             * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
             */
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

            // Loading trackables is not necessary for the TensorFlow Object Detection engine.
        }

        private void initTfod() {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                    "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfodParameters.minimumConfidence = 0.8;
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
        }

            // called when init button is  pressed.
            @Override
            public void runOpMode () throws InterruptedException
            {
                initVuforia();

                if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
                    initTfod();
                } else {
                    telemetry.addData("Sorry!", "This device is not compatible with TFOD");
                }

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
                                    telemetry.addData(String.format("right,bottom (%d)", i), "%.03f , %.03f",
                                            recognition.getRight(), recognition.getBottom());
                                }
                                telemetry.update();
                            }
                        }
                    }
                }



              /*  leftMotor = hardwareMap.dcMotor.get("leftDrive");
                rightMotor = hardwareMap.dcMotor.get("rightDrive");
                //rightMotor.setDirection(DcMotor.Direction.REVERSE);
                leftMotor.setDirection(DcMotor.Direction.FORWARD);
                leftMotor.setDirection(DcMotor.Direction.REVERSE);




                telemetry.addData("Mode", "waiting");
                telemetry.update();

                // wait for start button.

                waitForStart();

                telemetry.addData("Mode", "running");
                telemetry.update();

                // set both motors to 25% power.

                leftMotor.setPower(0.75);
                rightMotor.setPower(0.75);

                sleep(1500);        // wait for 2 seconds.

                // set motor power to zero to stop motors.

                leftMotor.setPower(-1.0);
                rightMotor.setPower(-1.0);
                sleep(100);

                leftMotor.setPower(0);
                rightMotor.setPower(-1.0);
                sleep(600);

                leftMotor.setPower(-0.75);
                rightMotor.setPower(-0.75);
                sleep(2000);    */


            }
        }





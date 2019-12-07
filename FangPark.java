package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "FangPark", group = "Exercises")
//@Disabled
    public class FangPark extends LinearOpMode {
        DcMotor leftMotor;
        DcMotor rightMotor;
        private Servo leftServo = null;
        private Servo rightServo = null;

        // called when init button is  pressed.

        @Override
        public void runOpMode() throws InterruptedException {
            leftMotor = hardwareMap.dcMotor.get("left_drive");
            rightMotor = hardwareMap.dcMotor.get("right_drive");
            leftServo = hardwareMap.servo.get("leftServo");
            rightServo = hardwareMap.servo.get("rightServo");

            double leftServo_HOME = 0.0;
            double leftServo_MIN_RANGE = 0.0;
            double leftServo_MAX_RANGE = 1.0;
            double leftServo_SPEED = 0.1;
            double leftServo_POSITION = 0.0;

            double rightServo_HOME = 0.0;
            double rightServo_MIN_RANGE = 0.0;
            double rightServo_MAX_RANGE = 1.0;
            double rightServo_SPEED = 0.1;
            double rightServo_POSITION = 0.0;

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

            leftMotor.setPower(0.25);
            rightMotor.setPower(0.25);
            sleep(800);

          /*  leftMotor.setPower(0);
            rightMotor.setPower(-0.75);
            sleep(1500);  */

            sleep(1000);

          /*   leftMotor.setPower(0);
            rightMotor.setPower(0.75);
            sleep(1500);

           leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);
            sleep(1000);   */

            sleep(600);
            leftServo.setPosition(-0.2);
            rightServo.setPosition(0.7);
            sleep(600);

            leftServo.setPosition(0.7);
            rightServo.setPosition(-0.8);
            sleep(1000);
         /*    leftMotor.setPower(-0.25);
            rightMotor.setPower(-0.75);
            sleep(1000)

            leftServo.setPosition(0.0);
            rightServo.setPosition(0.0);

            leftMotor.setPower(-0.75);
            rightMotor.setPower(-0.75);
            sleep(3000);  */


        }
  }

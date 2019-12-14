package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "FangParkRed", group = "Exercises")
//@Disabled
    public class FangParkRed extends LinearOpMode {


        @Override
        public void runOpMode() throws InterruptedException {
            DcMotor leftMotor = hardwareMap.dcMotor.get("left_drive");
            DcMotor rightMotor = hardwareMap.dcMotor.get("right_drive");
            Servo leftFang = hardwareMap.servo.get("leftServo");
            Servo rightFang = hardwareMap.servo.get("rightServo");

            /*double leftFang_HOME = 0.0;
            double leftFang_MIN_RANGE = 0.0;
            double leftFang_MAX_RANGE = 1.0;
            double leftFang_SPEED = 0.1;
            double leftFang_POSITION = 0.0;

            double rightFang_HOME = 0.0;
            double rightFang_MIN_RANGE = 0.0;
            double rightFang_MAX_RANGE = 1.0;
            double rightFang_SPEED = 0.1;
            double rightFang_POSITION = 0.0;*/

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

            leftMotor.setPower(0.30);
            rightMotor.setPower(0.30);
            sleep(295);

            leftFang.setPosition(1);
            rightFang.setPosition(0);
            sleep(2500);

            leftFang.setPosition(0.43);
            rightFang.setPosition(0.42);
            sleep(1000);

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
            sleep(1000);

            leftMotor.setPower(-0.9);
            rightMotor.setPower(-0.8);
            sleep(1200);

            leftMotor.setPower(0.9);
            rightMotor.setPower(0.9);
            sleep(200);

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
            sleep(300);

            leftMotor.setPower(1);
            rightMotor.setPower(-1);
            sleep(4000);

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);

            leftMotor.setPower(-1.0);
            rightMotor.setPower(-0.9);
            sleep(1000);

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);

            leftMotor.setPower(1);
            rightMotor.setPower(-1);
            sleep(2000);

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);

            leftMotor.setPower(0.9);
            rightMotor.setPower(0.9);
            sleep(1200);

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);

            leftFang.setPosition(1);
            rightFang.setPosition(0);
            sleep(1000);

            leftMotor.setPower(-0.5);
            rightMotor.setPower(-0.5);
            sleep(800);

            leftMotor.setPower(0.4);
            rightMotor.setPower(-0.4);
            sleep(1000);

            leftMotor.setPower(-0.5);
            rightMotor.setPower(-0.5);
            sleep(1000);

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);

        }
  }

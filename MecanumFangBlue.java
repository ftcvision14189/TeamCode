package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "MecanumFangBlue", group = "Auto")
    public class MecanumFangBlue extends LinearOpMode {

        DcMotor leftFrontMotor = null;
        DcMotor rightFrontMotor = null;
        DcMotor leftRearMotor = null;
        DcMotor rightRearMotor = null;
        private Servo leftFang = null;
        private Servo rightFang = null;
        DcMotor liftMotor = null;

        // called when init button is  pressed.

        @Override
        public void runOpMode() {
            leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
            rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
            leftRearMotor = hardwareMap.dcMotor.get("leftRear");
            rightRearMotor = hardwareMap.dcMotor.get("rightRear");
            leftFang = hardwareMap.servo.get("leftServo");
            rightFang = hardwareMap.servo.get("rightServo");
            liftMotor = hardwareMap.dcMotor.get("lift");

            leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
            rightRearMotor.setDirection(DcMotor.Direction.FORWARD);

            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // set both motors to 25% power.

            leftFang.setPosition(1.0);
            rightFang.setPosition(0.0);

            liftMotor.setPower(0.3);

            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(0.6);
            rightRearMotor.setPower(0.6);
            sleep(1000);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(500);

            leftFrontMotor.setPower(-0.6);
            rightFrontMotor.setPower(-0.6);
            leftRearMotor.setPower(0.6);
            rightRearMotor.setPower(0.6);
            sleep(500);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(300);

            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(0.6);
            rightRearMotor.setPower(0.6);
            sleep(800);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(400);

            leftFang.setPosition(0.42);
            rightFang.setPosition(0.4);
            sleep(500);

            leftFrontMotor.setPower(-1);
            rightFrontMotor.setPower(-1);
            leftRearMotor.setPower(-1);
            rightRearMotor.setPower(-1);
            sleep(900);

            leftFrontMotor.setPower(-1);
            rightFrontMotor.setPower(0);
            leftRearMotor.setPower(-1);
            rightRearMotor.setPower(0);
            sleep(900);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(200);

            leftFang.setPosition(1.0);
            rightFang.setPosition(0.0);
            sleep(500);

            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(-0.6);
            rightRearMotor.setPower(-0.6);
            sleep(1100);

            liftMotor.setPower(0.0);

            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(-0.6);
            rightRearMotor.setPower(-0.6);
            sleep(1100);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(200);

        }
  }

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
        private Servo claw = null;
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
            claw = hardwareMap.servo.get("claw");

            leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
            rightRearMotor.setDirection(DcMotor.Direction.FORWARD);

            liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.
            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            leftFang.setPosition(1.0);
            rightFang.setPosition(0.0);

            liftMotor.setPower(0.4);
            claw.setPosition(0.5);

            //go forward
            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(0.6);
            rightRearMotor.setPower(0.6);
            sleep(1200);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(500);

            //go sideways to aline with foundation
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

            //go forward to foundation
            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(0.6);
            rightRearMotor.setPower(0.6);
            sleep(700);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(400);

            //grab onto foundation
            leftFang.setPosition(0.5);
            rightFang.setPosition(0.4);
            liftMotor.setPower(0);
            sleep(500);

            //pull foundation back
            leftFrontMotor.setPower(-0.6);
            rightFrontMotor.setPower(-0.6);
            leftRearMotor.setPower(-0.6);
            rightRearMotor.setPower(-0.6);
            sleep(1400);

            //turn while moving back
            leftFrontMotor.setPower(8);
            rightFrontMotor.setPower(-8);
            leftRearMotor.setPower(8);
            rightRearMotor.setPower(-8);
            sleep(1600);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(200);

            //pull back more
            leftFrontMotor.setPower(-0.8);
            rightFrontMotor.setPower(-1.0);
            leftRearMotor.setPower(-0.8);
            rightRearMotor.setPower(-1.0);
            sleep(1700);

            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);
            leftRearMotor.setPower(0);
            rightRearMotor.setPower(0);
            sleep(300);

            //turn and position foundation
            leftFrontMotor.setPower(1.0);
            rightFrontMotor.setPower(-1.0);
            leftRearMotor.setPower(1.0);
            rightRearMotor.setPower(-1.0);
            sleep(1250);

            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);
            leftRearMotor.setPower(0);
            rightRearMotor.setPower(0);
            sleep(500);

            //push forward to align with wall
            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(0.6);
            rightRearMotor.setPower(0.6);
            sleep(1000);

            leftFang.setPosition(0);
            rightFang.setPosition(1);
            liftMotor.setPower(0.4);
            sleep(400);

            /*move sideways off foundation
            leftFrontMotor.setPower(0.6);
            rightFrontMotor.setPower(0.6);
            leftRearMotor.setPower(-0.6);
            rightRearMotor.setPower(-0.6);
            sleep(700);
*/
            //move backwards
            leftFrontMotor.setPower(-0.6);
            rightFrontMotor.setPower(-0.6);
            leftRearMotor.setPower(-0.6);
            rightRearMotor.setPower(-0.6);
            sleep(900);

            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);
            leftRearMotor.setPower(0);
            rightRearMotor.setPower(0);
            sleep(400);

            liftMotor.setPower(0.0);

            //move backwards and park
            leftFrontMotor.setPower(-0.6);
            rightFrontMotor.setPower(-0.6);
            leftRearMotor.setPower(-0.6);
            rightRearMotor.setPower(-0.6);
            sleep(975);

            leftFrontMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            leftRearMotor.setPower(0.0);
            rightRearMotor.setPower(0.0);
            sleep(200);

        }
  }
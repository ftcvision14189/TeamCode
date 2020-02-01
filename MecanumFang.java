package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "MecanumFang", group = "Auto")
public class MecanumFang extends LinearOpMode {

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

        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        leftFang.setPosition(0);
        rightFang.setPosition(1);

        liftMotor.setPower(0.4);
        claw.setPosition(0.5);

        //go forward
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

        //go sideways to align with foundation
        leftFrontMotor.setPower(-0.6);
        rightFrontMotor.setPower(-0.6);
        leftRearMotor.setPower(0.6);
        rightRearMotor.setPower(0.6);
        sleep(200);

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
        leftFang.setPosition(0.28);
        rightFang.setPosition(0.4);
        liftMotor.setPower(0);
        sleep(500);

        //pull foundation back
        leftFrontMotor.setPower(-0.8);
        rightFrontMotor.setPower(-0.6);
        leftRearMotor.setPower(-0.8);
        rightRearMotor.setPower(-0.6);
        sleep(2600);

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
        sleep(400);

        //turn to push into building zone
        leftFrontMotor.setPower(0.8);
        rightFrontMotor.setPower(-0.5);
        leftRearMotor.setPower(0.8);
        rightRearMotor.setPower(-0.5);
        sleep(1500);

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
        sleep(400);

        /*leftFrontMotor.setPower(0.8);
        rightFrontMotor.setPower(0.8);
        leftRearMotor.setPower(0.8);
        rightRearMotor.setPower(0.8);
        sleep(600);*/

        leftFrontMotor.setPower(0.6);
        rightFrontMotor.setPower(0.6);
        leftRearMotor.setPower(0.6);
        rightRearMotor.setPower(0.6);
        sleep(400);

        leftFang.setPosition(0);
        rightFang.setPosition(1);
        liftMotor.setPower(0);
        sleep(200);

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
        sleep(400);

        leftFrontMotor.setPower(0.6);
        rightFrontMotor.setPower(0.6);
        leftRearMotor.setPower(-0.6);
        rightRearMotor.setPower(-0.6);
        sleep(500);



        liftMotor.setPower(0.0);

        //back up and park
        leftFrontMotor.setPower(-0.6);
        rightFrontMotor.setPower(-0.6);
        leftRearMotor.setPower(-0.6);
        rightRearMotor.setPower(-0.6);
        sleep(1700);

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
        sleep(400);

    }
}
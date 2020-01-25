package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "MecanumFoundation", group = "Auto")
public class MecanumFoundation extends LinearOpMode {

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


        //move sideways off foundation
        leftFrontMotor.setPower(0.6);
        rightFrontMotor.setPower(0.6);
        leftRearMotor.setPower(-0.6);
        rightRearMotor.setPower(-0.6);
        sleep(700);

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
        sleep(400);

        liftMotor.setPower(0.0);

        leftFrontMotor.setPower(0.6);
        rightFrontMotor.setPower(0.6);
        leftRearMotor.setPower(-0.6);
        rightRearMotor.setPower(-0.6);
        sleep(700);

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);
        sleep(400);

    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="JoystickOp", group="Linear Opmode")

public class StoneRunJoystick extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftFrontMotor = null;
    DcMotor rightFrontMotor = null;
    DcMotor leftRearMotor = null;
    DcMotor rightRearMotor = null;
    DcMotor intakeLeft = null;
    DcMotor intakeRight = null;
    Servo rightFang = null;
    Servo leftFang = null;
    Servo markerServo = null;
    DcMotor tapePark = null;

    boolean fang_open = false;

    // declare motor speed variables
    double RF;
    double LF;
    double RR;
    double LR;

    double IR;
    double IL;

    double capPower;
    // declare joystick position variables
    double X1;
    double X2;
    double Y1;
    double Z1;
    double Z2;

    // operational constants
    double slowSpeed = 0.3;
    double normalSpeed = 0.6;
    double speedSpeed = 1;
    double motorMax = 1;
    double joyScale = normalSpeed;

    @Override
    public void runOpMode() {

        leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
        leftRearMotor = hardwareMap.dcMotor.get("leftRear");
        rightRearMotor = hardwareMap.dcMotor.get("rightRear");
        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeRight");
        rightFang = hardwareMap.servo.get("rightServo");
        leftFang = hardwareMap.servo.get("leftServo");
        markerServo = hardwareMap.servo.get("capStone");
        tapePark = hardwareMap.dcMotor.get("tapePark");


        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);


        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        telemetry.addData("Status", "Waiting for start.");
        telemetry.update();
        waitForStart();
        runtime.reset();

        boolean slowmode = false;
        boolean wasYPressed = false;
        boolean capstone_in = true;


        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            if (gamepad1.left_bumper && !wasYPressed) {
                slowmode = !slowmode;
            }

            wasYPressed = gamepad1.left_bumper;

            if (slowmode) {
                joyScale = slowSpeed;
                telemetry.addData("Mode", "Slow");
            }
            else if (gamepad1.right_bumper){
                joyScale = speedSpeed;
                telemetry.addLine("I.");
                telemetry.addLine("AM.");
                telemetry.addLine("SPEED.");
            }
            else {
                joyScale = normalSpeed;
                telemetry.addData("Mode", "Normal");
            }

            // Reset speed variables
            LF = 0; RF = 0; LR = 0; RR = 0;

            // Get joystick value
            X1 = gamepad1.right_stick_x * joyScale;
            X2 = gamepad1.left_stick_x * joyScale;
            Y1 = gamepad1.left_stick_y * joyScale;

            // Forward movement
            LF -= Y1; RF -= Y1; LR -= Y1; RR -= Y1;
            // Strafe movement
            LF += X2; RF -= X2; LR -= X2; RR += X2;
            // Rotation movement
            LF += X1; RF -= X1; LR += X1; RR -= X1;

            // Clip motor power values to +-motorMax
            LF = Math.max(-motorMax, Math.min(LF, motorMax));
            RF = Math.max(-motorMax, Math.min(RF, motorMax));
            LR = Math.max(-motorMax, Math.min(LR, motorMax));
            RR = Math.max(-motorMax, Math.min(RR, motorMax));

            // Send values to the motors
            leftFrontMotor.setPower(LF);
            rightFrontMotor.setPower(RF);
            leftRearMotor.setPower(LR);
            rightRearMotor.setPower(RR);

            //Toggle Fang Position when Y is pressed on gamepad1
            if ((gamepad2.y) && (fang_open)) {
                fang_open = false;
                rightFang.setPosition(1);
                leftFang.setPosition(0);
            } else if ((gamepad2.b) && (!fang_open)) {
                fang_open = true;
                rightFang.setPosition(0.4);
                leftFang.setPosition(0.4);
            }


            if (gamepad2.right_stick_y < 0) {
                IL = 0.35 * gamepad2.right_stick_y;
                IR = 0.35 * gamepad2.right_stick_y;
                intakeLeft.setPower(IL);
                intakeRight.setPower(IR);
            }
            else {
                IL = 0.5 * gamepad2.right_stick_y;
                IR = 0.5 * gamepad2.right_stick_y;
                intakeLeft.setPower(IL);
                intakeRight.setPower(IR);
            }


            if ((gamepad2.right_trigger > 0) && (capstone_in)) {
                capstone_in = false;
                markerServo.setPosition(0.75);
            } else if ((gamepad2.right_trigger == 0) && (!capstone_in)) {
                capstone_in = true;
                markerServo.setPosition(0);
            }

            tapePark.setPower(gamepad2.left_stick_y);

            // Send some useful parameters to the driver station
            telemetry.addData("LF", "%.3f", LF);
            telemetry.addData("RF", "%.3f", RF);
            telemetry.addData("LR", "%.3f", LR);
            telemetry.addData("RR", "%.3f", RR);
            telemetry.addData("Fang: ", "left(%.2f), right (%.2f)", leftFang.getPosition(), rightFang.getPosition());
            telemetry.update();
        }
    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="StoneRunOP", group="Linear Opmode")

public class StoneRunOp extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    DcMotor leftRearMotor;
    DcMotor rightRearMotor;

    @Override
    public void runOpMode() {

        leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
        leftRearMotor = hardwareMap.dcMotor.get("leftRear");
        rightRearMotor = hardwareMap.dcMotor.get("rightRear");
        DcMotor intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        DcMotor intakeRight = hardwareMap.dcMotor.get("intakeRight");
        Servo rightFang = hardwareMap.servo.get("rightServo");
        Servo leftFang = hardwareMap.servo.get("leftServo");
        Servo capper = hardwareMap.servo.get("capStone");

        // declare motor speed variables
        double RF;
        double LF;
        double RR;
        double LR;
        double IR;
        double IL;

        // declare joystick position variables
        double X1;
        double X2;
        double Y1;

        // operational constants
        double joyScale;
        double slowModeSpeed = 0.3;
        double normalSpeed = 0.5;
        double speedSpeed = 1;
        double motorMax = 1; // Limit motor power to this value for Andymark RUN_USING_ENCODER mode

        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Waiting for start.");
        telemetry.update();
        waitForStart();
        runtime.reset();

        boolean slowmode = false;
        boolean wasYPressed = false;
        boolean capstone_in = true;
        boolean fang_open = true;

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            slowmode = (gamepad1.y && !wasYPressed);
            wasYPressed = gamepad1.y;

            if (slowmode) {
                joyScale = slowModeSpeed;
                telemetry.addData("Mode", "Slow");
            }
            else if (gamepad1.right_trigger > 0){
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
            LF += Y1; RF += Y1; LR += Y1; RR += Y1;
            // Strafe movement
            LF += X2; RF -= X2; LR -= X2; RR += X2;
            // Rotation movement
            LF -= X1; RF += X1; LR -= X1; RR += X1;

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
                rightFang.setPosition(0.5);
                leftFang.setPosition(0.5);
            } else if ((gamepad2.b) && (!fang_open)) {
                fang_open = true;
                rightFang.setPosition(0);
                leftFang.setPosition(0);
            }

            if ((gamepad2.right_trigger > 0) && (capstone_in)) {
                capstone_in = false;
                capper.setPosition(0.75);
            } else if ((gamepad2.right_trigger == 0) && (!capstone_in)) {
                capstone_in = true;
                capper.setPosition(0);
            }


            IL = gamepad2.right_stick_y * joyScale;
            IR = gamepad2.right_stick_y * joyScale;
            intakeLeft.setPower(IL);
            intakeRight.setPower(IR);

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
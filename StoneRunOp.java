package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="StoneRunOP", group="Linear Opmode") // @Autonomous(...) is the other common choice

public class StoneRunOp extends LinearOpMode {

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

    boolean fang_open = false;

    // declare motor speed variables
    double RF;
    double LF;
    double RR;
    double LR;
    double IR;
    double IL;
    // declare joystick position variables
    double X1;
    double Y1;
    double Z1;
    double Z2;

    // operational constants
    double joyScale = 0.8;
    double motorMax = 1; // Limit motor power to this value for Andymark RUN_USING_ENCODER mode

    @Override
    public void runOpMode() {

        /* Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
        leftRearMotor = hardwareMap.dcMotor.get("leftRear");
        rightRearMotor = hardwareMap.dcMotor.get("rightRear");
        intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
        intakeRight = hardwareMap.dcMotor.get("intakeRight");
        rightFang = hardwareMap.servo.get("rightServo");
        leftFang = hardwareMap.servo.get("leftServo");


        // Set the drive motor direction:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        // These polarities are for the Neverest 20 motors
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.REVERSE);


        // Set the drive motor run modes:
        // "RUN_USING_ENCODER" causes the motor to try to run at the specified fraction of full velocity
        // Note: We were not able to make this run mode work until we switched Channel A and B encoder wiring into
        // the motor controllers. (Neverest Channel A connects to MR Channel B input, and vice versa.)
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


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            if (gamepad1.y && !wasYPressed){
                slowmode = !slowmode;
                wasYPressed = true;
            }

            else if (!gamepad1.y) {
                wasYPressed = false;
            }

            if (slowmode) {
                joyScale = 0.5;
                telemetry.addData("Mode", "Slow");
            }
            else if (gamepad1.right_trigger > 0){
                joyScale = 1;
                telemetry.addLine("I.");
                telemetry.addLine("AM.");
                telemetry.addLine("SPEED.");
            }
            else {
                joyScale = 0.8;
                telemetry.addData("Mode", "Normal");
            }

            // Reset speed variables
            LF = 0; RF = 0; LR = 0; RR = 0;

            // Get joystick value
            X1 = gamepad1.right_stick_x * joyScale;
            Y1 = gamepad1.left_stick_y * joyScale;
            //Z1 = Math.pow(gamepad1.left_stick_x * joyScale, 3);
            Z1 = gamepad1.right_trigger;
            Z2 = gamepad1.left_trigger;

            // Forward movement
            LF += Y1; RF += Y1; LR += Y1; RR += Y1;
            // Right side movement
            LF += Z1; RF -= Z1; LR -= Z1; RR += Z1;
            // Left side movement
            LF -= Z2; RF += Z2; LR += Z2; RR -= Z2;
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
                rightFang.setPosition(1);
                leftFang.setPosition(0);
            } else if ((gamepad2.b) && (!fang_open)) {
                fang_open = true;
                rightFang.setPosition(0.4);
                leftFang.setPosition(0.28);
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
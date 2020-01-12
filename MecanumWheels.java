package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="OpMode Static Arm", group="Linear Opmode")

public class MecanumWheels extends LinearOpMode {

    @Override
    public void runOpMode() {

        ElapsedTime runtime = new ElapsedTime();

        DcMotor frontRightDrive = hardwareMap.dcMotor.get("front_Right");
        DcMotor frontLeftDrive = hardwareMap.dcMotor.get("front_Left");
        DcMotor backRightDrive = hardwareMap.dcMotor.get("back_Right");
        DcMotor backLeftDrive = hardwareMap.dcMotor.get("back_Left");

        Servo rightFang = hardwareMap.servo.get("rightServo");
        Servo leftFang = hardwareMap.servo.get("leftServo");

        double leftFang_HOME = 0.0;
        double leftFang_MIN_RANGE = 0.0;
        double leftFang_MAX_RANGE = 1.0;
        double leftFang_SPEED = 0.1;
        double leftFang_POSITION = 0.0;

        double rightFang_HOME = 0.0;
        double rightFang_MIN_RANGE = 0.0;
        double rightFang_MAX_RANGE = 1.0;
        double rightFang_SPEED = 0.1;
        double rightFang_POSITION = 0.0;


        boolean was_slowmode_pressed = false;
        boolean slow_mode = false;
        boolean fang_open = false;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower = 0.0;
            double rightPower = 0.0;

            // toggle slow mode when A is pressed
            if (gamepad1.a) {
                if (!was_slowmode_pressed) {
                    slow_mode = !slow_mode;
                }
                was_slowmode_pressed = true;
            } else {
                was_slowmode_pressed = false;
            }


            //Toggle Fang Position when Y is pressed on gamepad1
            if ((gamepad1.y) && (fang_open)) {
                fang_open = false;
                rightFang.setPosition(0.4);
                leftFang.setPosition(1);
            } else if ((gamepad1.b) && (!fang_open)) {
                fang_open = true;
                rightFang.setPosition(0.0);
                leftFang.setPosition(0.4);
            }

            // Decrease drivetrain speed if slowmode is on
        if (slow_mode) {
                leftPower = 0.5 * gamepad1.left_stick_y;
                rightPower = 0.5 * gamepad1.right_stick_y;
            }
            else {
                leftPower = gamepad1.left_stick_y;
                rightPower = gamepad1.right_stick_y;
            }


            double magnitude = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double fld = magnitude * Math.cos(robotAngle) + rightX;
            final double frd = magnitude * Math.sin(robotAngle) - rightX;
            final double bld = magnitude * Math.sin(robotAngle) + rightX;
            final double brd = magnitude * Math.cos(robotAngle) - rightX;
            frontLeftDrive.setPower(fld);
            frontRightDrive.setPower(frd);
            backLeftDrive.setPower(bld);
            backRightDrive.setPower(brd);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Drivetrain", "left (%.2f), right (%.2f), slowmode (%b)", leftPower, rightPower, slow_mode);
            telemetry.addData("armPivotPower", gamepad2.right_stick_y);
            telemetry.addData("Fang: ", "left(%.2f), right (%.2f)", leftFang.getPosition(), rightFang.getPosition());
            telemetry.update();
        }
    }
}

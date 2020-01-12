package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="OpMode Static Arm", group="Linear Opmode")

public class MecanumWheels2 extends LinearOpMode {

    @Override
    public void runOpMode() {

        ElapsedTime runtime = new ElapsedTime();

        DcMotor frontRightDrive = hardwareMap.dcMotor.get("front_Right");
        DcMotor frontLeftDrive = hardwareMap.dcMotor.get("front_Left");
        DcMotor backRightDrive = hardwareMap.dcMotor.get("back_Right");
        DcMotor backLeftDrive = hardwareMap.dcMotor.get("back_Left");

        Servo rightFang = hardwareMap.servo.get("rightServo");
        Servo leftFang = hardwareMap.servo.get("leftServo");

        int threshold = 20;
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

        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower = 0.0;
            double rightPower = 0.0;

/*            getJoystickSettings(joystick);

            if(abs(joystick.joy1_y1) > threshold || abs(joystick.joy1_x1) > threshold)
            {
                motor[FR] = (joystick.joy1_y1 - joystick.joy1_x1)/2;
                motor[FL] = (-joystick.joy1_y1 - joystick.joy1_x1)/2;
                motor[BR] = (-joystick.joy1_y1 - joystick.joy1_x1)/2;
                motor[BL] = (joystick.joy1_y1 - joystick.joy1_x1)/2;
            }
            if(abs(joystick.joy1_x2) > threshold)
            {
                //rotate
                motor[FR] = (-joystick.joy1_x2)/2;
                motor[FL] = (-joystick.joy1_x2)/2;
                motor[BR] = (joystick.joy1_x2)/2;
                motor[BL] = (joystick.joy1_x2)/2;
*/

            //Toggle Fang Position when Y is pressed on gamepad1



            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Drivetrain", "left (%.2f), right (%.2f), slowmode (%b)", leftPower, rightPower, slow_mode);
            telemetry.addData("armPivotPower", gamepad2.right_stick_y);
            telemetry.addData("Fang: ", "left(%.2f), right (%.2f)", leftFang.getPosition(), rightFang.getPosition());
            telemetry.update();
        }
    }
}
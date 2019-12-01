package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="OpMode Static Arm", group="Linear Opmode")
public class StaticArmOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        ElapsedTime runtime = new ElapsedTime();
        DcMotor leftDrive = hardwareMap.dcMotor.get("left_drive");
        DcMotor rightDrive = hardwareMap.dcMotor.get("right_drive");
        DcMotor leftArmPivot = hardwareMap.dcMotor.get("arm_pivot_left");
        DcMotor rightArmPivot = hardwareMap.dcMotor.get("arm_pivot_right");
        Servo claw = hardwareMap.servo.get("claw");
        Servo rightFang = hardwareMap.servo.get("rightServo");
        Servo leftFang = hardwareMap.servo.get("leftServo");

        boolean was_slowmode_pressed = false;
        boolean claw_open = false;
        boolean slow_mode = false;
        boolean fang_open = false;
        double PIVOT_POWER = 0.2;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        rightArmPivot.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower = 0;
            double rightPower = 0;
            double armPivotPower = 0.5;

            // toggle slow mode when A is pressed
            if (gamepad1.a) {
                if (!was_slowmode_pressed) {
                    slow_mode = !slow_mode;
                }
                was_slowmode_pressed = true;
            } else {
                was_slowmode_pressed = false;
            }

            // toggle claw position when A/Y is pressed on gamepad2
            if ((gamepad2.a) && (claw_open)) {
                claw_open = false;
                claw.setPosition(100);
            } else if ((gamepad2.y) && (!claw_open)) {
                claw_open = true;
                claw.setPosition(0);
            }

            //Toggle Fang Position when A/Y is pressed on gamepad1
            if ((gamepad1.a) && (fang_open)) {
                fang_open = false;
                rightFang.setPosition(90);
                leftFang.setPosition(90);
            } else if ((gamepad1.a) && (!fang_open)) {
                fang_open = true;
                rightFang.setPosition(0);
                leftFang.setPosition(0);
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
            armPivotPower = PIVOT_POWER * gamepad2.right_stick_y;


            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            leftArmPivot.setPower(armPivotPower);
            rightArmPivot.setPower(armPivotPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Drivetrain", "left (%.2f), right (%.2f), slowmode (%b)", leftPower, rightPower, slow_mode);
            telemetry.addData("Arm", "left pivot (%.2f), right pivot ($.2f)", leftArmPivot.getPower(), rightArmPivot.getPower());
            telemetry.addData("Claw", "open (%b), position (%.2f)", claw_open, claw.getPosition());
            telemetry.update();
        }
    }
}

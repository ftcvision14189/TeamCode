package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="OpMode Static Arm", group="Linear Opmode")
public class BasicOpMode_LinearServo_Edit extends LinearOpMode
{

    @Override
    public void runOpMode() {
        ElapsedTime runtime = new ElapsedTime();
        DcMotor leftDrive;
        DcMotor rightDrive;
        DcMotor armPivot;
        CRServo claw;
        boolean was_a_pressed = false;
        boolean slow_mode = false;
        double CLAW_SERVO_POWER = 0.5;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        armPivot = hardwareMap.get(DcMotor.class, "arm_pivot");
        claw = hardwareMap.get(CRServo.class,"claw");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        claw.setDirection(CRServo.Direction.FORWARD);
        armPivot.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double leftPower;
            double rightPower;
            double armPivotPower;
            double clawPower;

            if (gamepad2.dpad_up) {
                clawPower = CLAW_SERVO_POWER;
            }
            else if (gamepad2.dpad_down) {
                clawPower = -CLAW_SERVO_POWER;
            }
            else {
                clawPower = 0;
            }

            // toggle slow mode when A is pressed
            if (gamepad1.a) {
                if (!was_a_pressed) {
                    if (!slow_mode) {
                        slow_mode = true;
                    }
                    else {
                        slow_mode = false;
                    }
                }
                was_a_pressed = true;
            }
            else {
                was_a_pressed = false;
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
            armPivotPower = 0.3 * gamepad2.right_stick_y ;

            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            armPivot.setPower(armPivotPower);
            claw.setPower(clawPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Drivetrain", "left (%.2f), right (%.2f), slowmode (%b)", leftPower, rightPower, slow_mode);
            telemetry.addData("Arm", "pivot (%.2f), claw (%.2f)", armPivotPower, clawPower);
            telemetry.update();
        }
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp public class MotorTest extends LinearOpMode {

    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftRearMotor;
    private DcMotor rightRearMotor;

    @Override
    public void runOpMode() {
        // Setup hardware
        leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
        leftRearMotor = hardwareMap.dcMotor.get("leftRear");
        rightRearMotor = hardwareMap.dcMotor.get("rightRear");

        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (!robotIsBusy()) {
                leftFrontMotor.setPower(0);
                rightFrontMotor.setPower(0);
                leftRearMotor.setPower(0);
                rightRearMotor.setPower(0);

                leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                if (gamepad1.y) {
                    telemetry.addData("Status", "Moving forwards.");
                    telemetry.update();
                    moveStraight(1, 0.8);
                }
                else if (gamepad1.b) {
                    telemetry.addData("Status", "Strafing right.");
                    telemetry.update();
                    strafe(1, 0.8);
                }
                else if (gamepad1.a) {
                    telemetry.addData("Status", "Moving backwards.");
                    telemetry.update();
                    moveStraight(-1, 0.8);
                }
                else if (gamepad1.x) {
                    telemetry.addData("Status", "Strafing left.");
                    telemetry.update();
                    strafe(-1, 0.8);
                }
                else {
                    telemetry.addData("Status", "Waiting for user input.");
                    telemetry.addData("Front Left", leftFrontMotor.getCurrentPosition());
                    telemetry.addData("Front Right", rightFrontMotor.getCurrentPosition());
                    telemetry.addData("Back Left", leftRearMotor.getCurrentPosition());
                    telemetry.addData("Back Right", rightRearMotor.getCurrentPosition());
                    telemetry.update();
                }
            }
        }
    }

    // Define movement functions
    private void strafe(float rotations, double power) {
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontMotor.setTargetPosition(Math.round(1120 * rotations));
        rightFrontMotor.setTargetPosition(Math.round(-1120 * rotations));
        leftRearMotor.setTargetPosition(Math.round(-1120 * rotations));
        rightRearMotor.setTargetPosition(Math.round(1120 * rotations));

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFrontMotor.setPower(power);
        rightFrontMotor.setPower(power);
        leftRearMotor.setPower(power);
        rightRearMotor.setPower(power);
    }

    private void moveStraight(float rotations, double power) {
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontMotor.setTargetPosition(Math.round(1120 * rotations));
        rightFrontMotor.setTargetPosition(Math.round(1120 * rotations));
        leftRearMotor.setTargetPosition(Math.round(1120 * rotations));
        rightRearMotor.setTargetPosition(Math.round(1120 * rotations));

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFrontMotor.setPower(power);
        rightFrontMotor.setPower(power);
        leftRearMotor.setPower(power);
        rightRearMotor.setPower(power);
    }

    boolean robotIsBusy() {
        return leftFrontMotor.isBusy() || rightFrontMotor.isBusy() || leftRearMotor.isBusy() || rightRearMotor.isBusy();
    }
}

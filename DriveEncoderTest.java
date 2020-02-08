package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Drive Encoder Test", group="Exercises")
//@Disabled
public class DriveEncoderTest extends LinearOpMode
{
    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    DcMotor leftRearMotor;
    DcMotor rightRearMotor;


    @Override
    public void runOpMode() throws InterruptedException
    {

        leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
        leftRearMotor = hardwareMap.dcMotor.get("leftRear");
        rightRearMotor = hardwareMap.dcMotor.get("rightRear");

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);

        waitForStart();
        while (opModeIsActive())
        {
            if (!robotIsBusy()) {

                leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                strafe(5,0.25);
                strafe(-5,0.25);
                moveStraight(5, 0.25);
                moveStraight(-5, 0.25);
                rotate(5,0.25);
                rotate(-5,0.25);
                diagonal(5,0.25);
                diagonal(-5,0.25);

            }
        }

        telemetry.addData("Mode", "running");
        telemetry.update();
        telemetry.addData("encoder-fwd", leftFrontMotor.getCurrentPosition() + "  busy=" + leftFrontMotor.isBusy());
        telemetry.addData("encoder-fwd", leftRearMotor.getCurrentPosition() + "  busy=" + leftRearMotor.isBusy());
        telemetry.addData("encoder-fwd", rightFrontMotor.getCurrentPosition() + "  busy=" + rightFrontMotor.isBusy());
        telemetry.addData("encoder-fwd", rightRearMotor.getCurrentPosition() + "  busy=" + rightRearMotor.isBusy());
        telemetry.update();
        //idle();

    }

    private void strafe(float rotations, double power)
    {
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

    private void moveStraight(float rotations, double power)
    {
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

    private void rotate(float rotations, double power)
    {
    leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    leftFrontMotor.setTargetPosition(Math.round(-1120 * rotations));
    rightFrontMotor.setTargetPosition(Math.round(1120 * rotations));
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

    private void diagonal(float rotations, double power)

    {
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontMotor.setTargetPosition(Math.round(0 * rotations));
        rightFrontMotor.setTargetPosition(Math.round(1120 * rotations));
        leftRearMotor.setTargetPosition(Math.round(1120 * rotations));
        rightRearMotor.setTargetPosition(Math.round(0 * rotations));

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

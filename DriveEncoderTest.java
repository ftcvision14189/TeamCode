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
    DcMotor leftMotor1;
    DcMotor rightMotor1;
    DcMotor leftMotor2;
    DcMotor rightMotor2;


    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor1 = hardwareMap.dcMotor.get("leftFront");
        rightMotor1 = hardwareMap.dcMotor.get("rightFront");
        leftMotor2 = hardwareMap.dcMotor.get("leftRear");
        rightMotor2 = hardwareMap.dcMotor.get("rightRear");


        rightMotor1.setDirection(DcMotor.Direction.REVERSE);
        rightMotor2.setDirection(DcMotor.Direction.REVERSE);
        leftMotor1.setDirection(DcMotor.Direction.FORWARD);
        leftMotor2.setDirection(DcMotor.Direction.FORWARD);

        // reset encoder count kept by left motor.
        leftMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor1.setTargetPosition(1000);
        rightMotor1.setTargetPosition(1000);
        leftMotor2.setTargetPosition(1000);
        rightMotor2.setTargetPosition(1000);

        leftMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // set left motor to run to target encoder position and stop with brakes on.
        leftMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set left motor to run to 5000 encoder counts.

        leftMotor1.setTargetPosition(1500);
        leftMotor2.setTargetPosition(1500);
        rightMotor1.setTargetPosition(1500);
        rightMotor2.setTargetPosition(1500);

        // set both motors to 25% power. Movement will start.

        leftMotor1.setPower(0.25);
        rightMotor1.setPower(0.25);
        leftMotor2.setPower(0.25);
        rightMotor2.setPower(0.25);

        leftMotor1.setTargetPosition(1500);
        leftMotor2.setTargetPosition(1500);
        rightMotor1.setTargetPosition(1500);
        rightMotor2.setTargetPosition(1500);


        while (opModeIsActive())
        {

            while (leftMotor1.isBusy() || rightMotor1.isBusy() || leftMotor2.isBusy() || rightMotor2.isBusy())
            {
                telemetry.addData("encoder-fwd", leftMotor1.getCurrentPosition() + "  busy=" + leftMotor1.isBusy());
                telemetry.addData("encoder-fwd", leftMotor2.getCurrentPosition() + "  busy=" + leftMotor2.isBusy());
                telemetry.addData("encoder-fwd", rightMotor1.getCurrentPosition() + "  busy=" + rightMotor1.isBusy());
                telemetry.addData("encoder-fwd", rightMotor2.getCurrentPosition() + "  busy=" + rightMotor2.isBusy());
                telemetry.update();
                //idle();
            }

            leftMotor1.setPower(0);
            rightMotor1.setPower(0);
            leftMotor2.setPower(0);
            rightMotor2.setPower(0);



            leftMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            leftMotor1.setTargetPosition(1500);
            leftMotor2.setTargetPosition(1500);
            rightMotor1.setTargetPosition(1500);
            rightMotor2.setTargetPosition(1500);

            leftMotor1.setPower(-0.25);
            rightMotor1.setPower(-0.25);
            leftMotor2.setPower(-0.25);
            rightMotor2.setPower(-0.25);

            while (leftMotor1.isBusy() || rightMotor1.isBusy() || leftMotor2.isBusy() || rightMotor2.isBusy())
            {
                telemetry.addData("encoder-bkw", leftMotor1.getCurrentPosition() + "  busy=" + leftMotor1.isBusy());
                telemetry.addData("encoder-bkw", leftMotor2.getCurrentPosition() + "  busy=" + leftMotor2.isBusy());
                telemetry.addData("encoder-bkw", rightMotor1.getCurrentPosition() + "  busy=" + rightMotor1.isBusy());
                telemetry.addData("encoder-bkw", rightMotor2.getCurrentPosition() + "  busy=" + rightMotor2.isBusy());
                telemetry.update();
                //idle();
            }
        }
    }
}

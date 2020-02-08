package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Drive Encoder", group="Exercises")
//@Disabled
    public class DriveEncoder extends LinearOpMode
    {

        DcMotor leftFrontMotor;
        DcMotor leftRearMotor;
        DcMotor rightFrontMotor;
        DcMotor rightRearMotor;

        DcMotor leftMotor1;
        DcMotor rightMotor1;
        DcMotor leftMotor2;
        DcMotor rightMotor2;


        @Override
        public void runOpMode() throws InterruptedException
        {

            leftFrontMotor = hardwareMap.dcMotor.get("left_motor");
            rightFrontMotor = hardwareMap.dcMotor.get("right_motor");

            leftMotor1 = hardwareMap.dcMotor.get("leftFront");
            rightMotor1 = hardwareMap.dcMotor.get("rightFront");
            leftMotor2 = hardwareMap.dcMotor.get("leftRear");
            rightMotor2 = hardwareMap.dcMotor.get("rightRear");



            rightMotor1.setDirection(DcMotor.Direction.REVERSE);
            rightMotor2.setDirection(DcMotor.Direction.REVERSE);
            leftMotor1.setDirection(DcMotor.Direction.FORWARD);
            leftMotor2.setDirection(DcMotor.Direction.FORWARD);

            leftMotor1.setTargetPosition(0);
            rightMotor1.setTargetPosition(0);
            leftMotor2.setTargetPosition(0);
            rightMotor2.setTargetPosition(0);

            // set left motor to run to target encoder position and stop with brakes on.
            leftMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset encoder count kept by left motor.
            leftMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        /*
            // set left motor to run to target encoder position and stop with brakes on.
            leftMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // set right motor to run without regard to an encoder.
            rightMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  */

            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // set left motor to run to 5000 encoder counts.

            leftMotor1.setTargetPosition(5000);
            leftMotor2.setTargetPosition(5000);
            rightMotor1.setTargetPosition(5000);
            rightMotor2.setTargetPosition(5000);

            // set both motors to 25% power. Movement will start.

            leftMotor1.setPower(0.25);
            rightMotor1.setPower(0.25);
            leftMotor2.setPower(0.25);
            rightMotor2.setPower(0.25);

            // wait while opmode is active and left motor is busy running to position.

            {
                telemetry.addData("encoder-fwd", leftMotor1.getCurrentPosition() + "  busy=" + leftMotor1.isBusy());
                telemetry.update();
                idle();
            }


            // set motor power to zero to turn off motors. The motors stop on their own but
            // power is still applied so we turn off the power.

            leftMotor1.setPower(0.25);
            rightMotor1.setPower(-0.25);
            leftMotor2.setPower(0.25);
            rightMotor2.setPower(-0.25);

            // wait 5 sec so you can observe the final encoder position.

            resetStartTime();

            while (opModeIsActive() && getRuntime() < 5)
            {
                telemetry.addData("encoder-fwd-end", leftMotor1.getCurrentPosition() + "  busy=" + leftMotor1.isBusy());
                telemetry.update();
                idle();
            }

            // Now back up to starting point. In this example instead of
            // having the motor monitor the encoder, we will monitor the encoder ourselves.

            leftMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            leftMotor1.setPower(-0.25);
            rightMotor1.setPower(-0.25);
            leftMotor2.setPower(-0.25);
            rightMotor2.setPower(-0.25);

            while (opModeIsActive() && leftMotor1.getCurrentPosition() > 0)
            {
                telemetry.addData("encoder-back", leftMotor1.getCurrentPosition());
                telemetry.update();
                idle();
            }

            // set motor power to zero to stop motors.

            leftMotor1.setPower(0.0);
            rightMotor1.setPower(0.0);
            leftMotor2.setPower(0.0);
            rightMotor2.setPower(0.0);

            // wait 5 sec so you can observe the final encoder position.

            resetStartTime();

            while (opModeIsActive() && getRuntime() < 5)
            {
                telemetry.addData("encoder-back-end", leftMotor1.getCurrentPosition());
                telemetry.update();
                idle();
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

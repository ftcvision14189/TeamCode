package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class StoneAuto {

    @Autonomous(name = "ParkingAuto", group = "Auto")
   // @Disabled
    public class MecanumWallPark extends LinearOpMode {

        DcMotor leftFrontMotor = null;
        DcMotor rightFrontMotor = null;
        DcMotor leftRearMotor = null;
        DcMotor rightRearMotor = null;
        DcMotor intakeLeft = null;
        DcMotor intakeRight = null;
        private Servo leftFang = null;
        private Servo rightFang = null;
        private Servo markerServo = null;


        // called when init button is  pressed.

        @Override
        public void runOpMode() {
            leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
            rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
            leftRearMotor = hardwareMap.dcMotor.get("leftRear");
            rightRearMotor = hardwareMap.dcMotor.get("rightRear");
            leftFang = hardwareMap.servo.get("leftServo");
            rightFang = hardwareMap.servo.get("rightServo");
            intakeRight = hardwareMap.dcMotor.get("intakeRight");
            intakeLeft = hardwareMap.dcMotor.get("intakeLeft");
            markerServo = hardwareMap.servo.get("capStone");


            leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
            rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
            intakeRight.setDirection(DcMotor.Direction.REVERSE);
            intakeRight.setDirection(DcMotor.Direction.FORWARD);

            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // set both motors to 25% power.
            strafe(5,0.25);
            strafe(-5,0.25);
            moveStraight(5, 0.25);
            moveStraight(-5, 0.25);
            rotate(5,0.25);
            rotate(-5,0.25);

        }

        // Define movement functions
        void strafe(float rotations, double power) {
            leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftFrontMotor.setTargetPosition(Math.round(1120 * rotations));
            rightFrontMotor.setTargetPosition(Math.round(-1120 * rotations));
            leftRearMotor.setTargetPosition(Math.round(-1120 * rotations));
            rightRearMotor.setTargetPosition(Math.round(1120 * rotations));

            leftFrontMotor.setPower(power);
            rightFrontMotor.setPower(power);
            leftRearMotor.setPower(power);
            rightRearMotor.setPower(power);

            leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            while (leftFrontMotor.isBusy() && rightFrontMotor.isBusy() && leftRearMotor.isBusy() && rightRearMotor.isBusy() && opModeIsActive());

            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);
            leftRearMotor.setPower(0);
            rightRearMotor.setPower(0);
        }

        void moveStraight(float rotations, double power) {
            leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftFrontMotor.setTargetPosition(Math.round(1120 * rotations));
            rightFrontMotor.setTargetPosition(Math.round(1120 * rotations));
            leftRearMotor.setTargetPosition(Math.round(1120 * rotations));
            rightRearMotor.setTargetPosition(Math.round(1120 * rotations));

            leftFrontMotor.setPower(power);
            rightFrontMotor.setPower(power);
            leftRearMotor.setPower(power);
            rightRearMotor.setPower(power);

            leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            while (leftFrontMotor.isBusy() && rightFrontMotor.isBusy() && leftRearMotor.isBusy() && rightRearMotor.isBusy() && opModeIsActive());

            leftFrontMotor.setPower(0);
            rightFrontMotor.setPower(0);
            leftRearMotor.setPower(0);
            rightRearMotor.setPower(0);
        }

        private void rotate(float rotations, double power) {
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

    }
}

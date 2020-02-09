package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "MecanumFangPark", group = "Auto")
public class MecanumFangPark extends LinearOpMode {

    DcMotor leftFrontMotor = null;
    DcMotor rightFrontMotor = null;
    DcMotor leftRearMotor = null;
    DcMotor rightRearMotor = null;
    private Servo leftFang = null;
    private Servo rightFang = null;
    DcMotor liftMotor = null;
    private Servo claw = null;
    // called when init button is  pressed.

    @Override
    public void runOpMode() {
        leftFrontMotor = hardwareMap.dcMotor.get("leftFront");
        rightFrontMotor = hardwareMap.dcMotor.get("rightFront");
        leftRearMotor = hardwareMap.dcMotor.get("leftRear");
        rightRearMotor = hardwareMap.dcMotor.get("rightRear");
        leftFang = hardwareMap.servo.get("leftServo");
        rightFang = hardwareMap.servo.get("rightServo");
        liftMotor = hardwareMap.dcMotor.get("lift");
        claw = hardwareMap.servo.get("claw");

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.
        waitForStart();

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Mode", "running");
        telemetry.update();

        leftFang.setPosition(0);
        rightFang.setPosition(1);

        //liftMotor.setPower(0.4);
        claw.setPosition(0.5);

        //go forward
        int distanceForward = 100;
        while (opModeIsActive() && leftFrontMotor.getCurrentPosition() < distanceForward)
        {
            leftFrontMotor.setPower(( distanceForward - leftFrontMotor.getCurrentPosition()) / distanceForward);
            rightFrontMotor.setPower((distanceForward - rightFrontMotor.getCurrentPosition()) / distanceForward);
            leftRearMotor.setPower((distanceForward - leftRearMotor.getCurrentPosition()) / distanceForward);
            rightRearMotor.setPower((distanceForward - rightRearMotor.getCurrentPosition()) / distanceForward);

            telemetry.addData("encoder-fwd", rightFrontMotor.getCurrentPosition() + "  busy=" + rightFrontMotor.isBusy());
            telemetry.update();
            //idle();
        }

        telemetry.addLine("test end loop");

        leftFrontMotor.setPower(0.0);
        rightFrontMotor.setPower(0.0);
        leftRearMotor.setPower(0.0);
        rightRearMotor.setPower(0.0);
        sleep(500);

    }
}
// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name="AutoParkInsideRight", group="Exercises")
//@Disabled
public class AutoParkInside extends LinearOpMode
{
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Servo claw = null;
    private DcMotor armPivot = null;

    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        claw = hardwareMap.servo.get("claw");
        armPivot = hardwareMap.dcMotor.get("arm_pivot");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.
        init();
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //Rest Claw and Pivot

        claw.setPosition(0.0);
        armPivot.setPower(0.0);

        // set both motors to 25% power.

        leftDrive.setPower(0.25);
        rightDrive.setPower(0.25);

        sleep(2000);        // wait for 2 seconds.

        // turn to right

        leftDrive.setPower(0.25);
        rightDrive.setPower(0.0);

        sleep(1500);        // turn for 1.5 seconds

        // stop robot

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
}
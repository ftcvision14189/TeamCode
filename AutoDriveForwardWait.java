// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="WaitDriveForward", group="Auto")
public class AutoDriveForwardWait extends LinearOpMode

{
    @Override
    public void runOpMode() throws InterruptedException
    {
        DcMotor leftDrive = hardwareMap.dcMotor.get("left_drive");
        DcMotor rightDrive = hardwareMap.dcMotor.get("right_drive");
        Servo claw = hardwareMap.servo.get("claw");
        DcMotor leftArmPivot = hardwareMap.dcMotor.get("arm_pivot_left");
        DcMotor rightArmPivot = hardwareMap.dcMotor.get("arm_pivot_right");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        init();
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //rest unused motors
        claw.setPosition(90);
        leftArmPivot.setPower(0.0);
        rightArmPivot.setPower((0.0));

        sleep(20000);

        //Go forward to park on tape
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);

        sleep(1500);

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

    }
}
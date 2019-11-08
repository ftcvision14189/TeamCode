// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name="DriveForward", group="Auto")
public class AutoDriveForward extends LinearOpMode
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
        claw.setPosition(0.0);
        leftArmPivot.setPower(0.0);
        rightArmPivot.setPower((0.0));

        //Go forward to park on tape
        leftDrive.setPower(0.25);
        rightDrive.setPower(0.25);

        sleep(1000);

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
}
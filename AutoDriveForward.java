// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name="DriveForward", group="Auto")
public class AutoDriveForward extends LinearOpMode

{
    @Override
    public void runOpMode()
    {
        DcMotor leftDrive = hardwareMap.dcMotor.get("left_drive");
        DcMotor rightDrive = hardwareMap.dcMotor.get("right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //Go forward to park on tape
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);

        sleep(1500);

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

    }
}
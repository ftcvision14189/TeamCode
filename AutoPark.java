// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;


@Autonomous(name="Drive Forward", group="Auto")
public class AutoPark extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        DcMotor leftDrive = hardwareMap.dcMotor.get("left_drive");
        DcMotor rightDrive = hardwareMap.dcMotor.get("right_drive");
        CRServo claw = hardwareMap.crservo.get("claw");
        DcMotor armPivot = hardwareMap.dcMotor.get("arm_pivot");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        init();
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        leftDrive.setPower(0.25);
        rightDrive.setPower(0.25);
        claw.setPower(0.0);
        armPivot.setPower(0.0);

        sleep(2000);

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
}
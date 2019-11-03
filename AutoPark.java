// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name="Drive Forward", group="Exercises")
//@Disabled
public class AutoPark extends LinearOpMode
{
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;

    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.
        init();
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power.

        leftDrive.setPower(0.25);
        rightDrive.setPower(0.25);

        sleep(2000);        // wait for 2 seconds.

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
}
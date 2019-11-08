// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
<<<<<<< HEAD
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name="AutoParkWall", group="Exercises")
//@Disabled
public class AutoParkWall extends LinearOpMode
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
=======
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;


@Autonomous(name="Drive Forward", group="Auto")
public class AutoParkWall extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException
    {
        DcMotor leftDrive = hardwareMap.dcMotor.get("left_drive");
        DcMotor rightDrive = hardwareMap.dcMotor.get("right_drive");
        CRServo claw = hardwareMap.crservo.get("claw");
        DcMotor leftArmPivot = hardwareMap.dcMotor.get("arm_pivot_left");
        DcMotor rightArmPivot = hardwareMap.dcMotor.get("arm_pivot_right");
>>>>>>> d28cefb4818d447b417a831bf0786a9af3ec2775

        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

<<<<<<< HEAD
        // wait for start button.
=======
>>>>>>> d28cefb4818d447b417a831bf0786a9af3ec2775
        init();
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

<<<<<<< HEAD
        // set both motors to 25% power.

        leftDrive.setPower(0.25);
        rightDrive.setPower(0.25);
        claw.setPosition(0.0);
        armPivot.setPower(0.0);

        sleep(2000);        // wait for 2 seconds.
=======
        //rest unused motors
        claw.setPower(0.0);
        leftArmPivot.setPower(0.0);
        rightArmPivot.setPower((0.0));

        //Go forward to park on tape
        leftDrive.setPower(0.25);
        rightDrive.setPower(0.25);

        sleep(1000);
>>>>>>> d28cefb4818d447b417a831bf0786a9af3ec2775

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Auto Spin Demo")
public class AutoSpinDemo extends LinearOpMode {
    @Override
    public void runOpMode() {

        DcMotor LFMotor = hardwareMap.dcMotor.get("LF");
        DcMotor RFMotor = hardwareMap.dcMotor.get("RF");
        DcMotor LBMotor = hardwareMap.dcMotor.get("LR");
        DcMotor RBMotor = hardwareMap.dcMotor.get("RR");

        LBMotor.setDirection(DcMotor.Direction.REVERSE);
        LFMotor.setDirection(DcMotor.Direction.REVERSE);

        double leftPod;
        double rightPod;

        telemetry.addData("Status", "Waiting for start.");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Spinning yeeeeeeeeeeeeeeeee!");
        telemetry.update();

        LFMotor.setPower(-1);
        LBMotor.setPower(-1);
        RFMotor.setPower(1);
        RBMotor.setPower(1);

        sleep(1000);

        LFMotor.setPower(1);
        LBMotor.setPower(1);
        RFMotor.setPower(-1);
        RBMotor.setPower(-1);

        sleep(1000);

        LFMotor.setPower(0);
        LBMotor.setPower(0);
        RFMotor.setPower(0);
        RBMotor.setPower(0);
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Robot Demo", group="Linear Opmode")
public class TankDemo extends LinearOpMode {

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

        double motor_multi = 0.5;

        telemetry.addData("Status", "Waiting for start.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            leftPod = motor_multi * gamepad1.left_stick_y;
            rightPod = motor_multi * gamepad1.right_stick_y;

            LFMotor.setPower(leftPod);
            LBMotor.setPower(leftPod);

            RFMotor.setPower(rightPod);
            RBMotor.setPower(rightPod);

            telemetry.addData("Status", "Robot running.");
            telemetry.addData("Left Pod Power", leftPod);
            telemetry.addData("Right Pod Power", rightPod);
            telemetry.update();

        }

    }

}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp public class ServoTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        Servo clawServo = hardwareMap.servo.get("claw");
        clawServo.setDirection(Servo.Direction.REVERSE);
        float servoPos = 0;

        telemetry.addData("Status", "Waiting for start.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            servoPos += (0.001 * gamepad1.right_stick_y);
            if (servoPos > 0.7) {
                servoPos = 0.7f;
            }
            else if (servoPos < 0.1) {
                servoPos = 0.1f;
            }

            clawServo.setPosition(servoPos);

            telemetry.addData("Status", "Testing servo.");
            telemetry.addData("Servo Position", clawServo.getPosition());
            telemetry.update();
        }

    }
}

// 0.1, 0.7
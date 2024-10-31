package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "Extend Test", group = "TeleOp")
public class Slides extends LinearOpMode {
    private Servo slide;
    @Override
    public void runOpMode(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        slide = hardwareMap.get(Servo.class, "extend");

        waitForStart();

        while (opModeIsActive()) {
            slide.setPosition(Range.clip(gamepad1.left_stick_y, 0, 1)*0.2+0.8);

            telemetry.addData("Motors", "left (%.2f), right (%.2f)", gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("Servo", "min 0.00 ~ now %.3f ~ max 1.00", slide.getPosition());
            telemetry.update();

        }
    }
}

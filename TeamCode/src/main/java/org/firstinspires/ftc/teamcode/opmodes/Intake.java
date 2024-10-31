package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "IntaketeleOP")
public class Intake extends LinearOpMode {
    private Servo s1;
    private Servo s2;

    @Override
    public void runOpMode() throws InterruptedException {
        s1 = hardwareMap.get(Servo.class,"servo1");
        s2 = hardwareMap.get(Servo.class,"servo2");
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){
                s1.setPosition(0);
                s2.setPosition(1);
            }
            else if(gamepad1.b){
                s1.setPosition(1);
                s2.setPosition(0);
            }
            else {
                s1.setPosition(0.5);
                s2.setPosition(0.5);
            }
            telemetry.addData("now:",gamepad1.left_stick_y);
            telemetry.update();
        }
    }
}

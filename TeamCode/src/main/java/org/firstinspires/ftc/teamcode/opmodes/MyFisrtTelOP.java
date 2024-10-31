package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "teleOP")
public class MyFisrtTelOP extends LinearOpMode {
    private DcMotor m;
    //private Servo s;
    public static final double NEW_P = 0.05;
    public static final double NEW_I = 0;
    public static final double NEW_D = 0;
    public static final double setpoint = 10;

    @Override
    public void runOpMode() throws InterruptedException {
        //s = hardwareMap.get(Servo.class,"servo0");
        m = hardwareMap.get(DcMotor.class,"motor1");
        waitForStart();
        while(opModeIsActive()){
            //s.setPosition(gamepad1.left_stick_y);
            //m.setPower(gamepad1.left_stick_y);
            PIDController pidNew = new PIDController(NEW_P, NEW_I, NEW_D);
            double output = pidNew.calculate(m.getCurrentPosition(),setpoint);
            m.setPower(output);

            telemetry.addData("now:",gamepad1.left_stick_y);
            telemetry.update();
        }
    }
}

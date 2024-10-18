package org.firstinspires.ftc.teamcode.subsystems.arm;

import android.util.Log;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.Globals;
import org.firstinspires.ftc.teamcode.utils.RunMode;

public class Slides {

    private Drivetrain drivetrain;
    private Sensors sensors;
    private DcMotorEx slidesMotor1;
    private DcMotorEx slidesMotor2;

    private double targetLength = 0;

    public Slides(HardwareMap hardwareMap, Drivetrain drivetrain, Sensors sensors) {
        this.drivetrain = drivetrain;
        this.sensors = sensors;

        slidesMotor1 = hardwareMap.get(DcMotorEx.class, "slidesMotor1");
        slidesMotor2 = hardwareMap.get(DcMotorEx.class, "slidesMotor2");
        slidesMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        if (Globals.RUNMODE != RunMode.TELEOP) {

        }
    }

    public Double getSlidesEncoders() {

        return (double) slidesMotor1.getCurrentPosition();

    }

    public void resetSlidesEncoders() {

        slidesMotor1.setPower(0);
        slidesMotor2.setPower(0);

        slidesMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slidesMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        targetLength = 0;
        slidesMotor1.setPower(0);
        slidesMotor2.setPower(0);

    }



}

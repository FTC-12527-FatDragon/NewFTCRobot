package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.vision.Vision;

import java.util.Arrays;
import java.util.List;

public class Drivetrain {

    public enum State {
        FOLLOW_SPLINE,
        GO_TO_POINT,
        DRIVE,
        FINAL_ADJUSTMENT,
        BRAKE,
        WAIT_AT_POINT,
        IDLE
    }

    private Robot robot;
    private Sensors sensors;
    private HardwareMap hardwareMap;
    private Vision vision;
    private final DcMotorEx rightBack;
    private final DcMotorEx leftBack;

    public Drivetrain(HardwareMap hardwareMap, Robot robot, Sensors sensors, Vision vision) {
        this.robot = robot;
        this.sensors = sensors;
        this.hardwareMap = hardwareMap;
        this.vision = vision;

        this.rightBack = hardwareMap.get(DcMotorEx.class, "rightBackMotor");
        this.leftBack = hardwareMap.get(DcMotorEx.class, "leftBackMotor");

    }

    public void setPower(double leftBackPower, double rightBackPower) {
        rightBack.setPower(rightBackPower);
        leftBack.setPower(leftBackPower);
    }

    public void setPowerWithGamepad(double leftJoystick, double rightJoystick) {
        double leftPower;
        double rightPower;

        double drive = -leftJoystick;

        leftPower    = Range.clip(drive + rightJoystick, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - rightJoystick, -1.0, 1.0) ;

        setPower(leftPower, rightPower);
    }

    public void setRobotDirection(boolean isHeadingFront) {
        if (isHeadingFront) {
            leftBack.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.FORWARD);
        }
        else {
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
        }
    }


}

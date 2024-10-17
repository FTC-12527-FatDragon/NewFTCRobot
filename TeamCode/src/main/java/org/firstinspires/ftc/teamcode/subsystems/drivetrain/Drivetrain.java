package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.vision.Vision;

public class Drivetrain {

    private Robot robot;
    private Sensors sensors;
    private HardwareMap hardwareMap;
    private Vision vision;

    // according to the current design

//    private DcMotorEx rightFront = hardwareMap.get(DcMotorEx.class, "rightFrontMotor");
//    private DcMotorEx leftFront = hardwareMap.get(DcMotorEx.class, "leftFrontMotor");

    private DcMotorEx rightBack = hardwareMap.get(DcMotorEx.class, "rightBackMotor");
    private DcMotorEx leftBack = hardwareMap.get(DcMotorEx.class, "leftBackMotor");

    public Drivetrain(HardwareMap hardwareMap, Robot robot, Sensors sensors, Vision vision) {
        this.robot = robot;
        this.sensors = sensors;
        this.hardwareMap = hardwareMap;
        this.vision = vision;
    }

    public Drivetrain(HardwareMap hardwareMap, Robot robot, Sensors sensors) {
        this.robot = robot;
        this.sensors = sensors;
        this.hardwareMap = hardwareMap;
    }

}

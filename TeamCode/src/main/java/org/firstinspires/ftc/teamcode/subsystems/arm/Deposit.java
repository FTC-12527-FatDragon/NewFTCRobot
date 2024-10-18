package org.firstinspires.ftc.teamcode.subsystems.arm;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;

public class Deposit {

    public Slides slides;
    private Robot robot;
    private Sensors sensors;
    private Drivetrain drivetrain;

    public Deposit(HardwareMap hardwareMap, Robot robot, Sensors sensors) {
        slides = new Slides(hardwareMap, robot.drivetrain, sensors);

    }
}

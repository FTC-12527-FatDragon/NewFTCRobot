package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.arm.Deposit;
import org.firstinspires.ftc.teamcode.subsystems.arm.Slides;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.vision.Vision;

public class Robot {

    public final Drivetrain drivetrain;
    public final Sensors sensors;
    public final Deposit deposit;
    public final Vision vision;

    public Robot(HardwareMap hardwareMap) {
        this(hardwareMap, null);
    }

    public Robot(HardwareMap hardwareMap, Vision vision) {
        this.vision = vision;

        sensors = new Sensors();
        deposit = new Deposit(hardwareMap, this, sensors);

        if (vision != null) {
            drivetrain = new Drivetrain(hardwareMap, this, sensors, vision);
        } else {
            drivetrain = new Drivetrain(hardwareMap, this, sensors);
        }
    }
}
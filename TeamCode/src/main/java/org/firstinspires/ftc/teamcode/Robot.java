package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.utils.Globals.GET_LOOP_TIME;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.arm.Deposit;
import org.firstinspires.ftc.teamcode.subsystems.arm.Slides;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.Dashboard;
import org.firstinspires.ftc.teamcode.vision.Vision;

public class Robot {

    public final Drivetrain drivetrain;
    public final Sensors sensors;
    //public final Deposit deposit;
    public final Vision vision;

    public Robot(HardwareMap hardwareMap) {
        this(hardwareMap, null);
    }

    public Robot(HardwareMap hardwareMap, Vision vision) {
        this.vision = vision;

        sensors = new Sensors();
        //deposit = new Deposit(hardwareMap, this, sensors);

        drivetrain = new Drivetrain(hardwareMap, this, sensors, vision);

        Dashboard.setup();
    }

    private void updateTelemetry() {
        // to be done
        Dashboard.packet.put("Loop Time", GET_LOOP_TIME());
        Dashboard.sendTelemetry();
    }

}
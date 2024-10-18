package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.Globals;
import org.firstinspires.ftc.teamcode.utils.RunMode;
import org.firstinspires.ftc.teamcode.vision.Vision;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")
public class TeleOp extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();

    Robot robot = new Robot(hardwareMap, null);
    Sensors sensors = new Sensors();
    Drivetrain drivetrain = new Drivetrain(hardwareMap, robot, sensors, null);

    @Override
    public void runOpMode() {
        Globals.RUNMODE = RunMode.TELEOP;
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            drivetrain.setPowerWithGamepad(gamepad1.left_stick_y, gamepad1.right_stick_x);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.update();

        }

    }
}

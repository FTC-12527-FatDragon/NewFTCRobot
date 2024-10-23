package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.Globals;
import org.firstinspires.ftc.teamcode.utils.RunMode;
import org.firstinspires.ftc.teamcode.vision.Vision;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="mecanum", group="Linear OpMode")
public class TeleOp extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
    Robot robot;
    Sensors sensors = new Sensors();

    // Grab Constants and give them to drivetrain
    private final PIDFCoefficients pidfTeleopCoefficients =
            new PIDFCoefficients(Constants.teleP, Constants.teleI, Constants.teleD, Constants.teleF);

    @Override
    public void runOpMode() {
        Globals.RUNMODE = RunMode.TELEOP;
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        robot = new Robot(hardwareMap, null);

        robot.drivetrain.setZeroPowerBrake();
        robot.drivetrain.enablePIDFControl(pidfTeleopCoefficients);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            robot.drivetrain.setVelocityWithGamepad(
                    gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_y, gamepad1.right_stick_x);
            //robot.drivetrain.setPowerWithGamepad(
            //      gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_y, gamepad1.right_stick_x);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

        }

    }
}

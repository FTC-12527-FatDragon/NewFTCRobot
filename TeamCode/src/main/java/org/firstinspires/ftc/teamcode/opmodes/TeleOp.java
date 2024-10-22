package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.utils.Globals;
import org.firstinspires.ftc.teamcode.utils.RunMode;
import org.firstinspires.ftc.teamcode.vision.Vision;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="mecanum", group="Linear OpMode")
public class TeleOp extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();
//    DcMotor LFmotor;
//    DcMotor LBmotor;
//    DcMotor RFmotor;
//    DcMotor RBmotor;
    Robot robot;
    Sensors sensors = new Sensors();

    @Override
    public void runOpMode() {
        Globals.RUNMODE = RunMode.TELEOP;
        telemetry.addData("Status", "Initialized");
        telemetry.update();
         robot = new Robot(hardwareMap, null);
//        LFmotor=hardwareMap.get(DcMotor.class,"LF");
//        LBmotor=hardwareMap.get(DcMotor.class,"LB");
//        RFmotor=hardwareMap.get(DcMotor.class,"RF");
//        RBmotor=hardwareMap.get(DcMotor.class,"RB");

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            robot.drivetrain.setPowerWithGamepad(gamepad1.left_stick_y, gamepad1.left_stick_x,gamepad1.right_stick_y,gamepad1.right_stick_x);
//            LFmotor.setPower(-gamepad1.left_stick_y);
//            LBmotor.setPower(-gamepad1.left_stick_y);
//            RFmotor.setPower(-gamepad1.left_stick_y);
//            RBmotor.setPower(-gamepad1.left_stick_y);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.update();

        }

    }
}

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.lib.gobilda.GoBildaPinpointDriver;
import org.firstinspires.ftc.teamcode.lib.roadrunner.drive.GoBildaPinPointLocalizer;

@TeleOp(name = "pinPointTestTeleOP")
public class Test extends LinearOpMode {
    private GoBildaPinPointLocalizer odometry;
    @Override
    public void runOpMode() throws InterruptedException {

        odometry = new GoBildaPinPointLocalizer(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            telemetry.update();
            telemetry.addData("heading:",odometry.getHeading());
            telemetry.addData("positions:",odometry.getWheelPositions());
            telemetry.addData("velocities:",odometry.getWheelVelocities());
        }
    }
}

package org.firstinspires.ftc.teamcode.lib.roadrunner.drive;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.TwoTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.lib.gobilda.GoBildaPinpointDriver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Config
public class GoBildaPinPointLocalizer extends TwoTrackingWheelLocalizer {
    private final GoBildaPinpointDriver odometry;


    public GoBildaPinPointLocalizer(final HardwareMap hardwareMap) {
        super(Arrays.asList(new Pose2d(mmToInches(-25),mmToInches(155),0), new Pose2d(mmToInches(-25),mmToInches(-155), Math.toRadians(90))));
        odometry = hardwareMap.get(GoBildaPinpointDriver.class, "od");
        odometry.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odometry.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odometry.setOffsets(0,0);
        odometry.resetPosAndIMU();
    }

    private static double mmToInches(double val) {
        return 0.0393701 * val;
    }

    @Override
    public double getHeading() {
        odometry.update();
        return odometry.getHeading();
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        odometry.update();
        return Arrays.asList(
                mmToInches(odometry.getPosX()),
                mmToInches(odometry.getPosY())
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        odometry.update();
        return Arrays.asList(
                mmToInches(odometry.getVelX()),
                mmToInches(odometry.getVelY())
        );
    }

    @NonNull
    @Override
    public Double getHeadingVelocity() {
        odometry.update();
        return odometry.getHeadingVelocity();
    }


}

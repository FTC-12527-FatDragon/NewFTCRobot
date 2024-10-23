package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.sensors.Sensors;
import org.firstinspires.ftc.teamcode.vision.Vision;

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
    private final DcMotorEx rightFront;
    private final DcMotorEx leftFront;
    private final DcMotorEx leftBack;

    public Drivetrain(HardwareMap hardwareMap, Robot robot, Sensors sensors, Vision vision) {
        this.robot = robot;
        this.sensors = sensors;
        this.hardwareMap = hardwareMap;
        this.vision = vision;

        rightBack = hardwareMap.get(DcMotorEx.class, "RB");
        rightFront=hardwareMap.get(DcMotorEx.class,"RF");
        leftFront=hardwareMap.get(DcMotorEx.class,"LF");
        leftBack = hardwareMap.get(DcMotorEx.class, "LB");
//        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double LBP, double LFP,double RBP,double RFP) {
        rightBack.setPower(Range.clip(RBP,-1,1));
        rightFront.setPower(Range.clip(RFP,-1,1));
        leftFront.setPower(Range.clip(LFP,-1,1));
        leftBack.setPower(Range.clip(LBP,-1,1));
    }

    public void setPowerWithGamepad(double leftY, double leftX,double RightY, double RightX) {
        setPower(- leftY - leftX + RightX,
                 - leftY + leftX + RightX,
                 - leftY + leftX - RightX,
                 - leftY - leftX - RightX);
    }

    public void enablePIDFControl(PIDFCoefficients pidfCoef) {
        rightBack.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoef);
        rightFront.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoef);
        leftBack.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoef);
        leftFront.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoef);
    }

    public void setVelocity(double LBV, double LFV,double RBV,double RFV) {
        rightBack.setVelocity(Range.clip(RBV, -Constants.maxMotorAngularVelocity, Constants.maxMotorAngularVelocity));
        rightFront.setVelocity(Range.clip(RFV, -Constants.maxMotorAngularVelocity, Constants.maxMotorAngularVelocity));
        leftFront.setVelocity(Range.clip(LFV, -Constants.maxMotorAngularVelocity, Constants.maxMotorAngularVelocity));
        leftBack.setVelocity(Range.clip(LBV, -Constants.maxMotorAngularVelocity, Constants.maxMotorAngularVelocity));
    }

    public void setVelocityWithGamepad(double leftY, double leftX,double RightY, double RightX) {
        setVelocity(- leftY - leftX + RightX,
                    - leftY + leftX + RightX,
                    - leftY + leftX - RightX,
                    - leftY - leftX - RightX);
    }

    /**
     * Call this function to set BRAKE state when no power for EVERY motor in MECANUM Drivetrain.
     */
    public void setZeroPowerBrake() {
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setRobotDirection(boolean isHeadingFront) {
        if (isHeadingFront) {
            leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
            rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        else {
            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
            leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
            leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }


}

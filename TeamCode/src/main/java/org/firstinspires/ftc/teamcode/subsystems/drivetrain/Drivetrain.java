package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

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
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double LBp, double LFp,double RBp,double RFp) {
        rightBack.setPower(Range.clip(RBp,-1,1));
        rightFront.setPower(Range.clip(RFp,-1,1));
        leftFront.setPower(Range.clip(LFp,-1,1));
        leftBack.setPower(Range.clip(LBp,-1,1));
    }

    public void setPowerWithGamepad(double leftY, double leftX,double RightY, double RightX) {
        setPower(-leftY-leftX+RightX,-leftY+leftX+RightX,-leftY+leftX-RightX,-leftY-leftX-RightX);
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
            leftBack.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.FORWARD);
        }
        else {
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
        }
    }


}

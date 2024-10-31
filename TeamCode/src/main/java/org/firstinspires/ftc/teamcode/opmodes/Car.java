package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "noHeadTeleOP")
public class Car extends LinearOpMode {
    private Servo leftIntakeServo, rightIntakeServo, armServo, slideServo;
    private DcMotor leftLiftMotor, rightLiftMotor, leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    private ArmServoState armState = ArmServoState.ORIGIN;

    @Override
    public void runOpMode() throws InterruptedException {
        armServo = hardwareMap.get(Servo.class,"servo3");
        //slideServo = hardwareMap.get(Servo.class,"servo5");
        leftLiftMotor = hardwareMap.get(DcMotor.class,"motor1");
        rightLiftMotor = hardwareMap.get(DcMotor.class,"motor2");
        leftIntakeServo = hardwareMap.get(Servo.class,"servo1");
        rightIntakeServo = hardwareMap.get(Servo.class,"servo2");
        leftFrontMotor = hardwareMap.get(DcMotor.class,"leftFrontMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class,"leftBackMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class,"rightFrontMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class,"rightBackMotor");
        leftLiftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightLiftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        leftLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
        waitForStart();
        while(opModeIsActive()){
            double forward = -gamepad1.left_stick_y;
            double fun = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;
            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = fun * Math.cos(-botHeading) - forward * Math.sin(-botHeading);
            double rotY = fun * Math.sin(-botHeading) + forward * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(turn), 1);
            double leftFrontPower = (rotY + rotX + turn) / denominator;
            double leftBackPower = (rotY - rotX + turn) / denominator;
            double rightFrontPower = (rotY - rotX - turn) / denominator;
            double rightBackPower = (rotY + rotX - turn) / denominator;
            leftFrontMotor.setPower(leftFrontPower);
            leftBackMotor.setPower(leftBackPower);
            rightFrontMotor.setPower(rightFrontPower);
            rightBackMotor.setPower(rightBackPower);
            if(gamepad1.dpad_up) {
                leftLiftMotor.setPower(1);
                rightLiftMotor.setPower(1);
            }
            else if(gamepad1.dpad_down) {
                leftLiftMotor.setPower(-1);
                rightLiftMotor.setPower(-1);
            }
            else {
                leftLiftMotor.setPower(0);
                rightLiftMotor.setPower(0);
            }
            if(gamepad1.left_bumper) {
                leftIntakeServo.setPosition(0);
                rightIntakeServo.setPosition(1);
            }
            else if(gamepad1.right_bumper) {
                leftIntakeServo.setPosition(1);
                rightIntakeServo.setPosition(0);
            }
            else {
                leftIntakeServo.setPosition(0.5);
                rightIntakeServo.setPosition(0.5);
            }
            if(gamepad1.a) {
                armState = ArmServoState.SCORE;
            }
            else if(gamepad1.b) {
                armState = ArmServoState.INTAKE;
            }
            else if(gamepad1.dpad_right) {
                armState = ArmServoState.MIDIUM;
            }
            else if(gamepad1.dpad_left) {
                armState = ArmServoState.HANG;
            }

            armServo.setPosition(armState.armPosition);
            /*else if(gamepad1.x) {
                slideServo.setPosition(0.5);
            }
            else if(gamepad1.y) {
                slideServo.setPosition(0.65);
            }*/
            telemetry.addData("forward",gamepad1.left_stick_y);
            telemetry.addData("fun:",gamepad1.left_stick_x);
            telemetry.addData("turn:",gamepad1.right_stick_x);
            telemetry.addData("armServo:",armServo.getPosition());
            //telemetry.addData("slideServo:",slideServo.getPosition());
            telemetry.update();
        }
    }

    private enum ArmServoState {
        ORIGIN(0.15),
        INTAKE(0.75),
        SCORE(0.15),
        MIDIUM(0.45),
        HANG(0.25);

        public double armPosition;

        ArmServoState(double position) {
            this.armPosition = position;
        }


    }
}

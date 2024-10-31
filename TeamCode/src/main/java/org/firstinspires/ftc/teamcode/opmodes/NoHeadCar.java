package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.lib.gobilda.GoBildaPinpointDriver;

@TeleOp(name = "fieldRelativeTeleOP")
public class NoHeadCar extends LinearOpMode {
    private Servo leftIntakeServo, rightIntakeServo, clawServo, slideServo, turnServo;
    private DcMotor leftLiftMotor, rightLiftMotor, leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    private TurnServoState turnState = TurnServoState.ORIGIN;
    private GoBildaPinpointDriver od;
    private double yawOffset;

    @Override
    public void runOpMode() throws InterruptedException {
        clawServo = hardwareMap.get(Servo.class,"clawServo");
        turnServo = hardwareMap.get(Servo.class,"turnServo");
        slideServo = hardwareMap.get(Servo.class,"slideServo");
        leftLiftMotor = hardwareMap.get(DcMotor.class,"leftLiftMotor");
        rightLiftMotor = hardwareMap.get(DcMotor.class,"rightLftMotor");
        //leftIntakeServo = hardwareMap.get(Servo.class,"leftIntakeServo");
        //rightIntakeServo = hardwareMap.get(Servo.class,"rightIntakeServo");
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
        od = hardwareMap.get(GoBildaPinpointDriver.class,"od");
        od.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        od.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        od.setOffsets(0, 0);
        waitForStart();
        while(opModeIsActive()){
            od.update();
            double forward = -gamepad1.left_stick_y;
            double fun = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            double botHeading = od.getHeading() % 360 - yawOffset;

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
            /* if(gamepad1.left_bumper) {
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
            }*/
            if(gamepad1.a) {
                yawOffset = od.getHeading() % 360;
            }
            else if(gamepad1.dpad_right) {
                switch(turnState) {
                    case ORIGIN:
                        turnState = TurnServoState.RIGHT30;
                        break;
                    case RIGHT30:
                        turnState = TurnServoState.RIGHT60;
                        break;
                    case RIGHT60:
                        turnState = TurnServoState.RIGHT90;
                        break;
                    case RIGHT90:
                        turnState = TurnServoState.RIGHT90;
                        break;
                    case LEFT90:
                        turnState = TurnServoState.LEFT60;
                        break;
                    case LEFT60:
                        turnState = TurnServoState.LEFT90;
                        break;
                    case LEFT30:
                        turnState = TurnServoState.LEFT90;
                        break;
                }
            }
            else if(gamepad1.dpad_left) {
                switch(turnState) {
                    case ORIGIN:
                        turnState = TurnServoState.LEFT30;
                        break;
                    case RIGHT30:
                        turnState = TurnServoState.ORIGIN;
                        break;
                    case RIGHT60:
                        turnState = TurnServoState.RIGHT30;
                        break;
                    case RIGHT90:
                        turnState = TurnServoState.RIGHT60;
                        break;
                    case LEFT90:
                        turnState = TurnServoState.LEFT90;
                        break;
                    case LEFT60:
                        turnState = TurnServoState.LEFT90;
                        break;
                    case LEFT30:
                        turnState = TurnServoState.LEFT60;
                        break;
                }
            }

            turnServo.setPosition(turnState.turnPosition);
            if(gamepad1.x) {
                clawServo.setPosition(0);
            }
            else if(gamepad1.y) {
                clawServo.setPosition(0.5);
            }
            telemetry.addData("forward",gamepad1.left_stick_y);
            telemetry.addData("fun:",gamepad1.left_stick_x);
            telemetry.addData("turn:",gamepad1.right_stick_x);
            telemetry.addData("clawServo:",clawServo.getPosition());
            telemetry.addData("heading:",botHeading);
            telemetry.addData("rawHeading:",od.getHeading());
            //telemetry.addData("slideServo:",clawServo.getPosition());
            telemetry.update();
        }
    }

    private enum TurnServoState {
        ORIGIN(0),
        LEFT30(-0.15),
        LEFT60(-0.30),
        LEFT90(-0.45),
        RIGHT30(0.15),
        RIGHT60(0.30),
        RIGHT90(0.45);

        public final double turnPosition;

        TurnServoState(double position) {
            this.turnPosition = position;
        }
    }
}

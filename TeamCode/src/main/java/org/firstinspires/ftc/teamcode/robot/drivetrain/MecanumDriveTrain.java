package org.firstinspires.ftc.teamcode.robot.drivetrain;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


public class MecanumDriveTrain {
    private final double WHEEL_DIAMETER_INCHES = 3.875; //96 mm diameter Mecanum Wheels
    private final long ENCODER_TICKS_PER_REV = 538; // 5203 Series Yellow Jacket Planetary Gear Motor (PPR at Output Shaft)
    private final double ENCODER_TICKS_PER_INCH = (ENCODER_TICKS_PER_REV) / (WHEEL_DIAMETER_INCHES * 3.14);
    private final double ROBOT_TURN_CIRCLE_DIAMETER_INCHES = 15.75;//Don't know for sure right now, just to test turning in degrees with encoders
    private final double ROBOT_TURN_CIRCLE_CIRCUMFERENCE_INCHES = (ROBOT_TURN_CIRCLE_DIAMETER_INCHES * 3.14);
    private final double STRAFE_FRICTION_LOSS_DISTANCE_FACTOR_FRONT = 1.15;
    private final double STRAFE_FRICTION_LOSS_DISTANCE_FACTOR_BACK = 1.14;
    private final double TURN_FRICTION_LOSS_DISTANCE_FACTOR = 1.465;
    private final long MILLISECONDS_PER_FORWARD_INCH = 500;
    private final double FORWARD_POWER = .25;
    private final double halfSquareLength = 12; // Half a square tile (12 in.)
    private volatile boolean stopRequested = false;
    private volatile boolean isStarted = false;
    private boolean userMonitoredForStart = false;

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor rearLeft;
    private DcMotor rearRight;



    public MecanumDriveTrain(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;

        this.frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rearRight.setDirection(DcMotorSimple.Direction.FORWARD);

        this.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public final boolean isStopRequested() {
        return this.stopRequested || Thread.currentThread().isInterrupted();
    }

    public final boolean isStarted() {
        if (this.isStarted) {
            this.userMonitoredForStart = true;
        }

        return this.isStarted || Thread.currentThread().isInterrupted();
    }

    public final void idle() {
        Thread.yield();
    }

    private final boolean opModeIsActive() {
        boolean isActive = !this.isStopRequested() && this.isStarted();
        if (isActive) {
            this.idle();
        }

        return isActive;
    }

    public void forward(double distanceInInches) {
        resetEncoders();
        int newTarget = (int)(distanceInInches * ENCODER_TICKS_PER_INCH);

        frontLeft.setTargetPosition(newTarget);
        rearLeft.setTargetPosition(newTarget);
        frontRight.setTargetPosition(newTarget);
        rearRight.setTargetPosition(newTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(FORWARD_POWER);
        rearLeft.setPower(FORWARD_POWER);
        frontRight.setPower(FORWARD_POWER);
        rearRight.setPower(FORWARD_POWER);

        while ((frontLeft.isBusy() && rearLeft.isBusy() && frontRight.isBusy()
                && rearRight.isBusy())) {

        }

        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);

    }

    public void backward(double distanceInInches) {
        resetEncoders();
        int newTarget = (int)(distanceInInches * ENCODER_TICKS_PER_INCH);

        frontLeft.setTargetPosition(-newTarget);
        rearLeft.setTargetPosition(-newTarget);
        frontRight.setTargetPosition(-newTarget);
        rearRight.setTargetPosition(-newTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(-FORWARD_POWER);
        rearLeft.setPower(-FORWARD_POWER);
        frontRight.setPower(-FORWARD_POWER);
        rearRight.setPower(-FORWARD_POWER);

        while ((frontLeft.isBusy() && rearLeft.isBusy() && frontRight.isBusy()
                && rearRight.isBusy())) {

        }

        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);

    }

    public void turnRight(double angleInDegreesClockwise) {
        resetEncoders();
        int newTarget = (int)((TURN_FRICTION_LOSS_DISTANCE_FACTOR * ((angleInDegreesClockwise / 360) * ROBOT_TURN_CIRCLE_CIRCUMFERENCE_INCHES)) * ENCODER_TICKS_PER_INCH);

        frontLeft.setTargetPosition(newTarget);
        rearLeft.setTargetPosition(newTarget);
        frontRight.setTargetPosition(-newTarget);
        rearRight.setTargetPosition(-newTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(FORWARD_POWER);
        rearLeft.setPower(FORWARD_POWER);
        frontRight.setPower(-(FORWARD_POWER));
        rearRight.setPower(-(FORWARD_POWER));

        while ((frontLeft.isBusy() && rearLeft.isBusy() && frontRight.isBusy()
                && rearRight.isBusy())) {

        }

        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);
    }
    public void turnLeft(double angleInDegreesClockwise) {
        resetEncoders();
        int newTarget = (int)(TURN_FRICTION_LOSS_DISTANCE_FACTOR * ((angleInDegreesClockwise / 360) * ROBOT_TURN_CIRCLE_CIRCUMFERENCE_INCHES) * ENCODER_TICKS_PER_INCH);

        frontLeft.setTargetPosition(-newTarget);
        rearLeft.setTargetPosition(-newTarget);
        frontRight.setTargetPosition(newTarget);
        rearRight.setTargetPosition(newTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(-FORWARD_POWER);
        rearLeft.setPower(-FORWARD_POWER);
        frontRight.setPower((FORWARD_POWER));
        rearRight.setPower((FORWARD_POWER));

        while ((frontLeft.isBusy() && rearLeft.isBusy() && frontRight.isBusy()
                && rearRight.isBusy())) {

        }

        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);
    }

    public void strafeRight(double distanceInInches) {
        resetEncoders();
        int newFrontTarget = (int)(STRAFE_FRICTION_LOSS_DISTANCE_FACTOR_FRONT * (distanceInInches * ENCODER_TICKS_PER_INCH));
        int newBackTarget = (int)(STRAFE_FRICTION_LOSS_DISTANCE_FACTOR_BACK * (distanceInInches * ENCODER_TICKS_PER_INCH));
        frontLeft.setTargetPosition(newFrontTarget);
        rearLeft.setTargetPosition(-newBackTarget);
        frontRight.setTargetPosition(-newFrontTarget);
        rearRight.setTargetPosition(newBackTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower((FORWARD_POWER));
        rearLeft.setPower(-FORWARD_POWER);
        frontRight.setPower(-FORWARD_POWER);
        rearRight.setPower((FORWARD_POWER));

        while ((frontLeft.isBusy() && rearLeft.isBusy() && frontRight.isBusy()
                && rearRight.isBusy())) {

        }

        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);

    }
    public void strafeLeft(double distanceInInches) {
        resetEncoders();
        int newFrontTarget = (int)(STRAFE_FRICTION_LOSS_DISTANCE_FACTOR_FRONT * (distanceInInches * ENCODER_TICKS_PER_INCH));
        int newBackTarget = (int)(STRAFE_FRICTION_LOSS_DISTANCE_FACTOR_BACK * (distanceInInches * ENCODER_TICKS_PER_INCH));

        frontLeft.setTargetPosition(newFrontTarget);
        rearLeft.setTargetPosition(-newBackTarget);
        frontRight.setTargetPosition(-newFrontTarget);
        rearRight.setTargetPosition(newBackTarget);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(FORWARD_POWER);
        rearLeft.setPower(-FORWARD_POWER);
        frontRight.setPower(-FORWARD_POWER);
        rearRight.setPower(FORWARD_POWER);

        while (opModeIsActive() && (frontLeft.isBusy() && rearLeft.isBusy() && frontRight.isBusy()
                && rearRight.isBusy())) {

        }

        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);

    }

    public void manualControl(double forwardPower, double turnPower, double strafePower)
    {
        double max;
        double leftFrontPower  = forwardPower + strafePower + turnPower;
        double rightFrontPower = forwardPower - strafePower - turnPower;
        double leftBackPower   = forwardPower - strafePower + turnPower;
        double rightBackPower  = forwardPower + strafePower - turnPower;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        frontLeft.setPower(leftFrontPower);
        frontRight.setPower(rightFrontPower);
        rearLeft.setPower(leftBackPower);
        rearRight.setPower(rightBackPower);
    }
    public void autoControl(){



    }
    public void resetEncoders(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void dpadControl (){
        if (gamepad1.dpad_up){
            forward(halfSquareLength);
        }
    }

}





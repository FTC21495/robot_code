package org.firstinspires.ftc.teamcode.robot.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


public class MecanumDriveTrain {
    private static double WHEEL_DIAMETER_INCHES = 3.78; //96 mm diameter Mecanum Wheels
    private static long ENCODER_TICKS_PER_REV = 538; // 5203 Series Yellow Jacket Planetary Gear Motor (PPR at Output Shaft)
    private static double ENCODER_TICKS_PER_INCH = (ENCODER_TICKS_PER_REV)/(WHEEL_DIAMETER_INCHES*3.14);  //538 ticks per revolution
    private final long MILLISECONDS_PER_FORWARD_INCH = 500;
    private final double FORWARD_POWER = 1;

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
    }

    public void forward(double distanceInInches) throws InterruptedException {
        resetEncoders();
        manualControl(FORWARD_POWER, 0, 0);
        wait((long)(distanceInInches * MILLISECONDS_PER_FORWARD_INCH));
        manualControl(0,0,0);
    }

    public void turn(double angleInDegreesClockwise) {

    }

    public void strafe(double distanceInInches) {

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

    private void resetEncoders(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

}




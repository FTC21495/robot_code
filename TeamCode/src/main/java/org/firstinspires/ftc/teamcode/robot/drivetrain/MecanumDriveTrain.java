package org.firstinspires.ftc.teamcode.robot.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


public class MecanumDriveTrain {
    private final double WHEEL_DIAMETER_INCHES = 3.875; //96 mm diameter Mecanum Wheels
    private final long ENCODER_TICKS_PER_REV = 538; // 5203 Series Yellow Jacket Planetary Gear Motor (PPR at Output Shaft)
    private final double ENCODER_TICKS_PER_INCH = (ENCODER_TICKS_PER_REV) / (WHEEL_DIAMETER_INCHES * 3.14);
    private final long MILLISECONDS_PER_FORWARD_INCH = 500;
    private final double FORWARD_POWER = .25;

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
    public void autoControl(){



    }
    public void resetEncoders(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }


}




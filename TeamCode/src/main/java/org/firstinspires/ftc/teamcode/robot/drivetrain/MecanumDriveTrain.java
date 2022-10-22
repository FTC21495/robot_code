package org.firstinspires.ftc.teamcode.robot.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDriveTrain {

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
    }

    public void forward(double distanceInInches) throws InterruptedException {
        manualControl(FORWARD_POWER, 0, 0);
        wait((long)(distanceInInches * MILLISECONDS_PER_FORWARD_INCH));
        manualControl(0,0,0);
    }

    public void turn(double angleInDegreesClockwise) {

    }

    public void strafe(double distanceInInches) {

    }

    public void manualControl(double forwardPower, double turnPower, double strafePower) {

    }
}






package org.firstinspires.ftc.teamcode.robot.drivetrain;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;



import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
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

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.FORWARD);
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
}


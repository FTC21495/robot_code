

package org.firstinspires.ftc.teamcode.robot.drivetrain;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MechanumDriveTrain {

    private final long MILLISECONDS_PER_FORWARD_INCH = 500;
    private final double FORWARD_POWER = 1;


    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor rearLeft;
    private DcMotor rearRight;

    public MechanumDriveTrain(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
    }

    public void forward(double distanceInInches) throws InterruptedException {
        manualControl(FORWARD_POWER, 0, 0); {
            frontLeft.setPower(FORWARD_POWER);
            frontRight.setPower(FORWARD_POWER);
        }
        wait((long)(distanceInInches * MILLISECONDS_PER_FORWARD_INCH));
        manualControl(0,0,0);
    }

    public void turn(double angleInDegreesClockwise) {
        manualControl(0,1,0);

    }

    public void strafe(double distanceInInches) {
        manualControl(0,0,1);
        double strafeVal = gamepad1.right_stick_x;

    }

    public void manualControl(double forwardPower, double turnPower, double strafePower) {




    }
}




package org.firstinspires.ftc.teamcode.robot.drivetrain;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;



import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MecanumDriveTrain {

    private final long MILLISECONDS_PER_FORWARD_INCH = 500;
    private long MILLISECONDS_PER_SIDE_INCH = 500;
    private final double FORWARD_POWER = 1;


    private DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeftMotor");
    private DcMotor frontRight = hardwareMap.dcMotor.get("frontRightMotor");
    private DcMotor rearLeft = hardwareMap.dcMotor.get("rearLeftMotor");
    private DcMotor rearRight = hardwareMap.dcMotor.get("rearRightMotor");


    double vertical = -gamepad1.left_stick_y;
    double horizontal = gamepad1.left_stick_x;
    double strafeVal = gamepad1.right_stick_x;



    public MecanumDriveTrain(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight) {
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

    public void strafe(double distanceInInches) throws InterruptedException {
        manualControl(0,0,1); {
            // With the signs used, the wheels wheel strafe right as a default
            frontRight.setPower(distanceInInches - (strafeVal));
            frontLeft.setPower(distanceInInches +(strafeVal));

            rearRight.setPower(distanceInInches + (strafeVal));
            rearLeft.setPower(distanceInInches - (strafeVal));
        }

    }

    public void manualControl(double forwardPower, double turnPower, double strafePower) {
        turnPower = gamepad1.left_stick_x;


    }
}


package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.drivetrain.MecanumDriveTrain;

public class Robot {

    private MecanumDriveTrain drivetrain;

    public Robot(HardwareMap hardwareMap) {
        drivetrain = new MecanumDriveTrain(
                hardwareMap.get(DcMotor.class, "left_front_drive"),
                hardwareMap.get(DcMotor.class, "right_front_drive"),
                hardwareMap.get(DcMotor.class, "left_rear_drive"),
                hardwareMap.get(DcMotor.class, "right_rear_drive")
        );
    }

    public void driveForward (double distanceInInches) throws InterruptedException {
        drivetrain.forward(distanceInInches);

    }

    public void driveBackwards (double distanceInInches){

    }

    public void turnLeft (double angleInDegrees){

    }

    public void turnRight (double angleInDegrees){

    }

    public void manualMove (double forwardPower, double turnPower, double strafePower){
        drivetrain.manualControl(forwardPower,turnPower, strafePower);

    }


}

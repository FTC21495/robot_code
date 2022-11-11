package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous(name="Robot: Auto Drive By Encoder", group="Robot")
public class AutonomousDriveTrainTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot maple = new Robot(hardwareMap, this::opModeIsActive);

        waitForStart();

        maple.driveForward(96);

        maple.driveBackwards(48);

        maple.turnLeft(360);

        maple.turnRight(270);

        maple.strafeRight(48);

        maple.strafeLeft(90);

    }
}

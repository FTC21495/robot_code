package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous(name="Robot: Auto Drive By Encoder", group="Robot")
public class AutonomousDriveTrainTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot maple = new Robot(hardwareMap, this::opModeIsActive, telemetry);

        waitForStart();

        maple.driveForward(20.5);

        switch (maple.getColorFromColorSensor()) {

            case RED:                               // 1
                maple.driveBackwards(17.5);
                maple.strafeLeft(24);
                maple.driveForward(27);
                break;

            case GREEN:                             // 2
                maple.driveForward(7.5);
                break;

            case BLUE:                              // 3
                maple.driveBackwards(17.5);
                maple.strafeRight(24);
                maple.driveForward(27);
                break;
        }

    }
}

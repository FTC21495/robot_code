package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous(name="Robot: Auto Drive By Encoder", group="Robot")
public class AutonomousDriveTrainTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot maple = new Robot(hardwareMap, this::opModeIsActive, telemetry);
        maple.grabCup();

        waitForStart();
        maple.letGoOfCup();
        maple.driveForward(20);

        switch (maple.getColorFromColorSensor()) {

            case RED:                               // 1
                maple.driveBackwards(17);
                maple.grabCup();
                maple.strafeLeft(24);
                maple.driveForward(27);
                break;

            case BLUE:                             // 2
                maple.driveBackwards(12);
                maple.grabCup();
                maple.driveForward(22);
                break;

            case GREEN:                              // 3
                maple.driveBackwards(17);
                maple.grabCup();
                maple.strafeRight(24);
                maple.driveForward(27);
                break;
        }

    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous(name="Robot: Auto Drive By Encoder Test", group="Robot")
@Disabled
public class NewAutonomousDriveTrainTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot maple = new Robot(hardwareMap, this::opModeIsActive, telemetry);

        waitForStart();

        maple.driveForwardBlocking(20);

        switch (maple.getColorFromColorSensor()) {

            case RED:                               // 1
                maple.driveBackwardsBlocking(17);
                maple.strafeLeftBlocking(24);
                maple.driveForwardBlocking(27);
                break;

            case BLUE:                             // 2
                maple.driveForwardBlocking(10);
                break;

            case GREEN:                              // 3
                maple.driveBackwardsBlocking(17);
                maple.strafeRightBlocking(24);
                maple.driveForwardBlocking(27);
                break;
        }

    }
}

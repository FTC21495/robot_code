package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.LiftLevels;
import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous(name="Robot: Auto Drive By Encoder", group="Robot")
public class AutonomousDriveTrainTest extends LinearOpMode {

    private int COLOR_DETECTED;

    @Override
    public void runOpMode() throws InterruptedException {
        Robot maple = new Robot(hardwareMap, this::opModeIsActive, telemetry);

        waitForStart();

        maple.setLiftPosition(LiftLevels.GROUND);

        maple.driveForwardBlocking(23);

        switch (maple.getColorFromColorSensor()) {

            case RED:                               // 1
                maple.driveBackwardsBlocking(17);
                maple.strafeLeftBlocking(24);
                maple.driveForwardBlocking(24);
                break;

            case BLUE:                             // 2
                maple.driveBackwardsBlocking(10);
                break;

            case GREEN:                              // 3
                maple.driveBackwardsBlocking(17);
                maple.strafeRightBlocking(24);
                maple.driveForwardBlocking(24);
                break;
        }

        telemetry.update();

    }
}

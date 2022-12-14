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

        maple.driveForward(20);

        switch (maple.getColorFromColorSensor()) {

            case RED:                               // 1
                maple.driveBackwards(17);
                maple.strafeLeft(24);
                maple.driveForward(27);
                break;

            case BLUE:                             // 2
                maple.driveForward(10);
                break;

            case GREEN:                              // 3
                maple.driveBackwards(17);
                maple.strafeRight(24);
                maple.driveForward(27);
                break;
        }

    }
}

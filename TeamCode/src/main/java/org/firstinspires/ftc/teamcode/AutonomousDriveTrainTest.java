package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous(name="Robot: Auto Drive By Encoder", group="Robot")
public class AutonomousDriveTrainTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Robot maple = new Robot(hardwareMap, this::opModeIsActive, telemetry);
        maple.closeClaw();

        waitForStart();
        maple.openClaw();
        maple.driveForward(21);

        switch (maple.getColorFromColorSensor()) {

            case BLACK:                               // 1
                maple.driveBackwards(17);
                maple.closeClaw();
                maple.strafeLeft(24);
                maple.driveForward(27);
                break;

            case GRAY:// 2
                maple.driveBackwards(12);
                maple.closeClaw();
                maple.driveForward(22);
                break;

            case WHITE:                              // 3
                maple.driveBackwards(17);
                maple.closeClaw();
                maple.strafeRight(24);
                maple.driveForward(27);
                break;
        }

    }
}

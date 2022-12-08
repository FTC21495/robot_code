package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.LiftLevels;
import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous(name="Autonomous With One Drop", group="Robot")
public class AutonomousWithOneDrop extends LinearOpMode {

    private String COLOR_DETECTED;

    @Override
    public void runOpMode() throws InterruptedException {
        Robot maple = new Robot(hardwareMap, this::opModeIsActive, telemetry);

        waitForStart();

        maple.grabCup();

        Thread.sleep(3000);

        maple.setLiftPosition(LiftLevels.LOW);

        maple.driveForwardBlocking(11);

        maple.letGoOfCup();

        Thread.sleep(500);

        maple.driveBackwardsBlocking(4.5);

        maple.strafeLeftBlocking(12);

        maple.driveForwardBlocking(14);

        switch (maple.getColorFromColorSensor()) {

            case RED:                               // 1
                maple.driveBackwardsBlocking(17);
                maple.strafeLeftBlocking(24);
                maple.driveForwardBlocking(27);
                break;

            case BLUE:                             // 2
                maple.driveBackwardsBlocking(10);
                break;

            case GREEN:                              // 3
                maple.driveForwardBlocking(17);
                maple.strafeLeftBlocking(24);
                maple.driveBackwardsBlocking(27);
                break;
        }
        telemetry.update();

    }
}

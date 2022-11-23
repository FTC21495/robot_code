package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Claw;
import org.firstinspires.ftc.teamcode.robot.Lift;

@TeleOp(name="MainTeleOpV1", group ="Iterative OpMode")
public class MainTeleOp extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Robot maple = null;

    private static final double INITIAL_DRIVETRAIN_SENSITIVITY = 1.5;
    private static final double SENSITIVITY_CHANGE = 0.5;

    private double drivetrainSensitivity = INITIAL_DRIVETRAIN_SENSITIVITY;
    private boolean wasSensitivityChangedLastLoop;
    private boolean isActive = true;

    @Override
    public void runOpMode() {
        maple = new Robot(hardwareMap,this::opModeIsActive,telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();



        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            setDrivetrainPower();
            setSensitivityChange();
            setDpadControl();
            setLiftControl();
            setClawControl();

            telemetry.update();

        }
    }

    private void setSensitivityChange(){

        // Check to see whether or not a button has been pressed
        if (gamepad1.right_bumper || gamepad1.left_bumper) {
            if (!wasSensitivityChangedLastLoop) {
                if (gamepad1.right_bumper) {
                    drivetrainSensitivity += SENSITIVITY_CHANGE;
                }
                // If it has been pressed, change sensitivity
                if (gamepad1.left_bumper && drivetrainSensitivity > 1) {
                    drivetrainSensitivity -= SENSITIVITY_CHANGE;
                }
                // Log the change in sensitivity
                telemetry.addData("Current Sensitivity", drivetrainSensitivity);
                wasSensitivityChangedLastLoop = true;
            }
        } else {
            wasSensitivityChangedLastLoop = false;
        }
    }

    private void setDrivetrainPower(){
        maple.manualMove(-gamepad1.left_stick_y / drivetrainSensitivity,
                gamepad1.right_stick_x / drivetrainSensitivity,
                gamepad1.left_stick_x / drivetrainSensitivity);

    }

    private void setDpadControl(){
        if (gamepad1.dpad_up){
            maple.driveForward(12);
        }
        if (gamepad1.dpad_down){
            maple.driveBackwards(12);
        }
        if (gamepad1.dpad_right){
            maple.strafeRight(12);
        }
        if (gamepad1.dpad_left){
            maple.strafeLeft(12);
        }

    }

    private void setLiftControl(){
        if (gamepad2.left_trigger > .1){
            maple.lowerLift(gamepad2.left_trigger);
        } else if(gamepad2.right_trigger > .1){
            maple.raiseLift(gamepad2.right_trigger);
        } else {
            maple.stopLift();
        }

    }

    private void setClawControl(){
        if (gamepad2.dpad_up){
            maple.openClaw();
        }

        if (gamepad2.dpad_down){
            maple.closeClaw();
        }
    }
}





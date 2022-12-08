package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name="MecanumDrive Test Op", group="Iterative Opmode")
public class MecanumTestOpMode extends OpMode {

    private static final double INITIAL_DRIVETRAIN_SENSITIVITY = 1.5;
    private static final double SENSITIVITY_CHANGE = 0.5;

    private Robot maple;

    private double drivetrainSensitivity = INITIAL_DRIVETRAIN_SENSITIVITY;
    private boolean wasSensitivityChangedLastLoop;
    private boolean isActive = true;

    @Override
    public void init() {
        maple = new Robot(hardwareMap, () -> isActive, telemetry);
    }

    @Override
    public void loop() {
        setDrivetrainPower();
        setSensitivityChange();
        setDpadControl();

      telemetry.update();
    }

    @Override
    public void stop(){
        isActive = false;
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
            maple.driveForwardBlocking(12);
        }
        if (gamepad1.dpad_down){
            maple.driveBackwardsBlocking(12);
        }
        if (gamepad1.dpad_right){
            maple.strafeRightBlocking(12);
        }
        if (gamepad1.dpad_left){
            maple.strafeLeftBlocking(12);
        }

    }

}

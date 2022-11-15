package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.robot.Claw;

@TeleOp (name="tele op", group="claw control test")
public class ClawControlTest extends OpMode {

    private Claw clawArm;

    @Override
    public void init() {

    }

    public void loop() {


        if (gamepad2.dpad_up){
            clawArm.openClaw();
        }

        if (gamepad1.dpad_down){
            clawArm.closeClaw();
        }

    }










}

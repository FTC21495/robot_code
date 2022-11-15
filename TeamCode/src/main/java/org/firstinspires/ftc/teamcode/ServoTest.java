package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp (group = "TeleOp", name = "servoTest")
public class ServoTest extends OpMode {
    private Servo servo;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    @Override
    public void loop() {
        double currentPosition = servo.getPosition();
        telemetry.addData("servoPosition", currentPosition);

// When the claw comes in, we will have to switch this to gamepad 2.
        if (gamepad1.dpad_up){
            servo.setPosition(currentPosition + 0.01);
        }

        if (gamepad1.dpad_down){
            servo.setPosition(currentPosition - 0.01);
        }

        telemetry.update();

    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

// TODO: Add timing delay


@TeleOp (group = "TeleOp", name = "servoTest")
@Disabled
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

        if (gamepad1.dpad_up){
            servo.setPosition(currentPosition + 0.01);
        }

        if (gamepad1.dpad_down){
            servo.setPosition(currentPosition - 0.01);
        }

        telemetry.update();

    }
}

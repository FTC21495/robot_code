package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name="MecanumDrive Test Op", group="Opmode")
class MecanumTestOpMode extends OpMode {

    private Robot maple;

    @Override
    public void init() {
        maple = new Robot(hardwareMap);
    }

    @Override
    public void loop() {
        maple.manualMove(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

    }
}


package org.firstinspires.ftc.teamcode.robot.drivetrain;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumTest {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;


        public void movement() {
            double vertical = 0;
            double horizontal = 0;
            double strafe = 0;
            // Check which motors are flipped if any
            vertical = -gamepad1.left_stick_y;
            horizontal = gamepad1.left_stick_x;
            strafe = gamepad1.right_stick_x;

            frontLeft.setPower(strafe + (-vertical - horizontal));
            rearLeft.setPower(strafe + (-vertical + horizontal));

            frontRight.setPower(strafe + (-vertical + horizontal));
            rearRight.setPower(strafe + (-vertical - horizontal));

        }
        public void mapping (){
                frontLeft = hardwareMap.dcMotor.get("FLMotor");
                rearLeft = hardwareMap.dcMotor.get("RLMotor");
                frontRight = hardwareMap.dcMotor.get("FRMotor");
                rearRight = hardwareMap.dcMotor.get("RRMotor");
        }
    }






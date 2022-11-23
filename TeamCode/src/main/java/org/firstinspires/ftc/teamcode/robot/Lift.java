package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift {

    private final double LIFT_POWER = 1;
    private final long ENCODER_TICKS_PER_REV = 538;//5203 Motor
    private final double LIFT_PULLEY_WHEEL_CIRCUMFERENCE_INCHES = 4.40945;// 3407 Hub-Mount Winch Pulley; mm circumference to inches
    private final double ENCODER_TICKS_PER_LIFT_INCH = (ENCODER_TICKS_PER_REV / LIFT_PULLEY_WHEEL_CIRCUMFERENCE_INCHES);
    private DcMotor liftMotor;
    private Telemetry telemetry;




    public Lift(DcMotor liftMotor, Telemetry telemetry) {
        this.liftMotor = liftMotor;
        this.telemetry = telemetry;

        this.liftMotor.setPower(0);
        this.liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }


    public void liftUp(double power){

        liftMotor.setPower(power);


    }

    public void liftDown(double power){

        liftMotor.setPower(-power);


    }

    public void liftStop(){

        liftMotor.setPower(0);

    }

}

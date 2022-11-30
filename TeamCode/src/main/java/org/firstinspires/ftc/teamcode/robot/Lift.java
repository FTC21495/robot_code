package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;

public class Lift {

    private final double LIFT_POWER = 1;
    private final long ENCODER_TICKS_PER_REV = 538;//5203 Motor
    private final double LIFT_PULLEY_WHEEL_CIRCUMFERENCE_INCHES = 4.40945;// 3407 Hub-Mount Winch Pulley; mm circumference to inches
    private final double ENCODER_TICKS_PER_LIFT_INCH = (ENCODER_TICKS_PER_REV / LIFT_PULLEY_WHEEL_CIRCUMFERENCE_INCHES);
    private DcMotor liftMotor;
    private Telemetry telemetry;
    private HashMap<LiftLevels, Integer> liftLevelMap;
    public Lift(DcMotor liftMotor, Telemetry telemetry) {

        this.liftMotor = liftMotor;
        this.telemetry = telemetry;

        this.liftMotor.setPower(0);
        this.liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftLevelMap.put(LiftLevels.GROUND, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 1.5)));
        liftLevelMap.put(LiftLevels.LOW, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 10)));
        liftLevelMap.put(LiftLevels.MEDIUM, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 20)));
        liftLevelMap.put(LiftLevels.HIGH, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 30)));
    }


    public void setTargetPosition(LiftLevels level){
        liftMotor.setTargetPosition(liftLevelMap.get(level));
    }

    public void getCurrentPosition(){
        liftMotor.getCurrentPosition();
    }
    public void liftUp(){

        liftMotor.setPower(1);

    }

    public void liftDown(){

        liftMotor.setPower(-1);

    }

    public void liftStop(){

        liftMotor.setPower(0);

    }



}

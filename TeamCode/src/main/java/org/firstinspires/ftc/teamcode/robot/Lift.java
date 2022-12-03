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
    private final double STOP_MOTOR_POWER = .05;
    private DcMotor liftMotor;
    private Telemetry telemetry;
    private HashMap<LiftLevels, Integer> liftLevelMap = new HashMap<>();
    public Lift(DcMotor liftMotor, Telemetry telemetry) {

        this.liftMotor = liftMotor;
        this.telemetry = telemetry;

        this.liftMotor.setPower(0);
        this.liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        liftLevelMap.put(LiftLevels.GROUND, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 2)));
        liftLevelMap.put(LiftLevels.LOW, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 12)));
        liftLevelMap.put(LiftLevels.MEDIUM, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 22)));
        liftLevelMap.put(LiftLevels.HIGH, new Integer((int) (ENCODER_TICKS_PER_LIFT_INCH * 32)));
    }


    public void goToTargetPosition(LiftLevels level){
        liftMotor.setTargetPosition(liftLevelMap.get(level));
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public boolean isUp(){
        return (liftMotor.getCurrentPosition() > (ENCODER_TICKS_PER_LIFT_INCH * 8));
    }

    public void liftUp(double power){

        liftMotor.setPower(power);

    }

    public void liftDown(double power){

        liftMotor.setPower((-power / 3));

    }

    public void liftStop(){

        liftMotor.setPower(STOP_MOTOR_POWER);

    }



}

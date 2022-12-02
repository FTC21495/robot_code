package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.robotcore.external.Supplier;

public class Robot {

    public enum ColorSensorColor {
        BLACK,
        WHITE,
        GRAY
    }


    private MecanumDriveTrain drivetrain;
    ColorSensor colorSensor;
    private Telemetry telemetry;
    Lift lift;
    private Claw claw;


    public Robot(HardwareMap hardwareMap, Supplier<Boolean>opModeIsActive, Telemetry telemetry) {
        drivetrain = new MecanumDriveTrain(
                hardwareMap.get(DcMotor.class, "left_front_drive"),
                hardwareMap.get(DcMotor.class, "right_front_drive"),
                hardwareMap.get(DcMotor.class, "left_rear_drive"),
                hardwareMap.get(DcMotor.class, "right_rear_drive"),
                opModeIsActive
        );
        colorSensor = hardwareMap.get(ColorSensor.class, "front_color_sensor" );

        lift = new Lift(hardwareMap.get(DcMotor.class, "lift"), telemetry);

        claw = new Claw(hardwareMap.get(Servo.class, "claw_servo"), opModeIsActive);

        this.telemetry = telemetry;

    }

    public void raiseLift(double power){

        lift.liftUp(power);

    }

    public void lowerLift(double power){

        lift.liftDown(power);

    }

    public void stopLift(){
        lift.liftStop();
    }

    public void driveForward (double distanceInInches) {
        drivetrain.forward(distanceInInches);

    }

    public void driveBackwards (double distanceInInches){
        drivetrain.backward(distanceInInches);
    }

    public void turnLeft (double angleInDegrees){

        drivetrain.turnLeft(angleInDegrees);
    }

    public void turnRight (double angleInDegrees){

        drivetrain.turnRight(angleInDegrees);
    }

    public void strafeLeft (double distanceInInches){

        drivetrain.strafeLeft(distanceInInches);
    }
    public void strafeRight (double distanceInInches){

        drivetrain.strafeRight(distanceInInches);
    }


    public void manualMove (double forwardPower, double turnPower, double strafePower){
        drivetrain.manualControl(forwardPower,turnPower, strafePower);

    }

    public void openClaw(){
        claw.openClaw();
    }

    public void closeClaw(){
        claw.closeClaw();
    }


    public ColorSensorColor getColorFromColorSensor(){


        float redSaturation = colorSensor.red();
        float blueSaturation = colorSensor.blue();
        float greenSaturation = colorSensor.green();
        float totalPigment = redSaturation + blueSaturation + greenSaturation;

        telemetry.addLine()
                .addData("Red", "%.3f", redSaturation)
                .addData("Green", "%.3f", greenSaturation)
                .addData("Blue", "%.3f", blueSaturation);
        telemetry.update();

        if ((totalPigment >50)){

            return ColorSensorColor.BLACK;

        } else if (totalPigment <15){

            return ColorSensorColor.WHITE;

        }
        else {

            return ColorSensorColor.GRAY;
        }
    }


}

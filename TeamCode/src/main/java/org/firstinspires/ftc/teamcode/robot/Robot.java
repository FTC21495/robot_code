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
            RED,
            GREEN,
            BLUE
    }

    private final long SENSITIVITY_WHEN_LIFT_UP = 5;
    private MecanumDriveTrain drivetrain;
    ColorSensor colorSensor;
    private Telemetry telemetry;
    Lift lift;
    private Claw claw;
    private Supplier<Boolean> opModeIsActive;


    public Robot(HardwareMap hardwareMap, Supplier<Boolean>opModeIsActive, Telemetry telemetry) {
        drivetrain = new MecanumDriveTrain(
                hardwareMap.get(DcMotor.class, "left_front_drive"),
                hardwareMap.get(DcMotor.class, "right_front_drive"),
                hardwareMap.get(DcMotor.class, "left_rear_drive"),
                hardwareMap.get(DcMotor.class, "right_rear_drive"),
                opModeIsActive
        );
        colorSensor = hardwareMap.get(ColorSensor.class, "front_color_sensor" );
        //if (colorSensor instanceof SwitchableLight) {
            //SwitchableLight light = (SwitchableLight)colorSensor;
            //light.enableLight(false);
        //}

        lift = new Lift(hardwareMap.get(DcMotor.class, "lift"), telemetry, opModeIsActive);

        claw = new Claw(hardwareMap.get(Servo.class, "claw_servo"), opModeIsActive);

        this.telemetry = telemetry;

        this.opModeIsActive = opModeIsActive;

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

    public void setLiftPosition(LiftLevels level){

        lift.goToTargetPosition(level);
    }

    public void driveForwardNonBlocking(double distanceInInches) {
        drivetrain.forward(distanceInInches);

    }

    public void driveBackwardsNonBlocking(double distanceInInches){
        drivetrain.backward(distanceInInches);
    }

    public void turnLeftNonBlocking(double angleInDegrees){

        drivetrain.turnLeft(angleInDegrees);
    }

    public void turnRightNonBlocking(double angleInDegrees){

        drivetrain.turnRight(angleInDegrees);
    }

    public void strafeLeftNonBlocking(double distanceInInches){

        drivetrain.strafeLeft(distanceInInches);
    }
    public void strafeRightNonBlocking(double distanceInInches){

        drivetrain.strafeRight(distanceInInches);
    }


    public void driveForwardBlocking(double distanceInInches) {
        drivetrain.forward(distanceInInches);
        while (drivetrain.isBusy() && opModeIsActive.get()){
            // intentionally blank
        }
        drivetrain.stopRunningUsingEncoders();
    }

    public void driveBackwardsBlocking(double distanceInInches){
        drivetrain.backward(distanceInInches);
        while (drivetrain.isBusy() && opModeIsActive.get()){
            // intentionally blank
        }
        drivetrain.stopRunningUsingEncoders();
    }

    public void turnLeftBlocking(double angleInDegrees){
        drivetrain.turnLeft(angleInDegrees);
        while (drivetrain.isBusy() && opModeIsActive.get()){
            // intentionally blank
        }
        drivetrain.stopRunningUsingEncoders();
    }

    public void turnRightBlocking(double angleInDegrees){
        drivetrain.turnRight(angleInDegrees);
        while (drivetrain.isBusy() && opModeIsActive.get()){
            // intentionally blank
        }
        drivetrain.stopRunningUsingEncoders();
    }

    public void strafeLeftBlocking(double distanceInInches){

        drivetrain.strafeLeft(distanceInInches);
        while (drivetrain.isBusy() && opModeIsActive.get()){
            // intentionally blank
        }
        drivetrain.stopRunningUsingEncoders();
    }
    public void strafeRightBlocking(double distanceInInches){
        drivetrain.strafeRight(distanceInInches);
        while (drivetrain.isBusy() && opModeIsActive.get()){
            // intentionally blank
        }
        drivetrain.stopRunningUsingEncoders();
    }


    public void manualMove (double forwardPower, double turnPower, double strafePower){
        if (lift.isUp()){
            drivetrain.manualControl((forwardPower / SENSITIVITY_WHEN_LIFT_UP),(turnPower / SENSITIVITY_WHEN_LIFT_UP), (strafePower / SENSITIVITY_WHEN_LIFT_UP));
        }   else {
            drivetrain.manualControl(forwardPower, turnPower, strafePower);
        }
    }

    public void letGoOfCup(){
        claw.letGoOfCup();
    }

    public void grabCup(){
        claw.grabCup();
    }


    public ColorSensorColor getColorFromColorSensor(){

        //light.enableLight(true);


        //light.enableLight(false);

        float redSaturation = colorSensor.red();
        float blueSaturation = colorSensor.blue();
        float greenSaturation = colorSensor.green();

        telemetry.addLine()
                .addData("Red", "%.3f", redSaturation)
                .addData("Green", "%.3f", greenSaturation)
                .addData("Blue", "%.3f", blueSaturation);
        telemetry.update();

        //if ((greenSaturation - redSaturation) < 100 && (blueSaturation < greenSaturation)){
        if (((greenSaturation-redSaturation) < 30) && (blueSaturation > greenSaturation)){

            return ColorSensorColor.RED;

        } else if ((blueSaturation > redSaturation) && (blueSaturation > greenSaturation)){

            return ColorSensorColor.BLUE;

        }

        return ColorSensorColor.GREEN;

    }


}

package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Supplier;


public class Claw {

    private Servo robotArm;
    private TouchSensor touch;
    private Supplier<Boolean> opModeIsActive;
    private final double OPEN_POSITION_OF_CLAW = 0;//change later
    private final double CLOSED_POSITION_OF_CLAW = 0.167;//change later


    public Claw(Servo robotArm, Supplier<Boolean> opModeIsActive){
        this.robotArm = robotArm;
        this.opModeIsActive = opModeIsActive;

        this.robotArm.setDirection(Servo.Direction.FORWARD);

        this.robotArm.setPosition(OPEN_POSITION_OF_CLAW);
    }
    public void letGoOfCup(){

        robotArm.setPosition(OPEN_POSITION_OF_CLAW);

    }
    public void grabCup(){

        robotArm.setPosition(CLOSED_POSITION_OF_CLAW);


    }
    //Buttons on claw that indicate to robot that it is holding the cup.


}


package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class Claw {


 private Servo robotArm;
 private TouchSensor touch;


    public Claw(){

    }
    public void openClaw(){

    }
    public void closeClaw(){

    }
    //Buttons on claw that indicate to robot that it is holding the cup.
    public boolean senseCup  (boolean holdingCup) {return holdingCup;}




}


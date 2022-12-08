package org.firstinspires.ftc.teamcode.robot;


import org.firstinspires.ftc.robotcore.internal.android.dx.util.IntSet;
import org.firstinspires.ftc.teamcode.robot.drivetrain.MecanumDriveTrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Navigation {



    private HashMap< Integer, Integer> tilesHorizontal = new HashMap<>();
    private HashMap<VerticalNavigation, Integer> tilesVertical = new HashMap<>();
    private MecanumDriveTrain driveTrain;

    public Navigation (MecanumDriveTrain driveTrain){

        tilesVertical.put(VerticalNavigation.A, (120));
        tilesVertical.put(VerticalNavigation.B, (96));
        tilesVertical.put(VerticalNavigation.C, (72));
        tilesVertical.put(VerticalNavigation.D, (48));
        tilesVertical.put(VerticalNavigation.E, (24));

        tilesHorizontal.put(1, (84));
        tilesHorizontal.put(2, (60));
        tilesHorizontal.put(3, (36));
        tilesHorizontal.put(4, (12));
        tilesHorizontal.put(5, (-12));

        this.driveTrain = driveTrain;


    }


    public void goToPosition(VerticalNavigation target, Integer HorizontalTarget){


        driveTrain.forward(tilesVertical.get(target));
        checkHorizontalPosition(tilesHorizontal.get(HorizontalTarget));

    }

    public void checkHorizontalPosition (Integer HorizontalTarget){
        if (tilesHorizontal.get(HorizontalTarget) > 0){
            driveTrain.strafeLeft(tilesHorizontal.get(HorizontalTarget));
        } else {
            driveTrain.strafeRight(-tilesHorizontal.get(HorizontalTarget));
        }
    }

    public void pathBrake() {


    }


}

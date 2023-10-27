package org.firstinspires.ftc.teamcode.BNI_Team.Olivia;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public abstract class Switch_Case extends OpMode{

    public enum DriverProfiles {LILY, GRACE}
    public DriverProfiles driverProfiles = DriverProfiles.LILY;



    public void driverControls (){
        switch (driverProfiles){
            case LILY:
                if(gamepad1.dpad_up){
                   // BlueBot.worm
                }

        break;


        }
    }
}

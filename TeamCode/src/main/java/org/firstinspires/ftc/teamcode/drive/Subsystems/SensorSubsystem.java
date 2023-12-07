package org.firstinspires.ftc.teamcode.drive.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import android.view.TouchDelegate;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class SensorSubsystem {

    public RevColorSensorV3 colorSensor;

    public TouchSensor touchSensor;

    public DigitalChannel magnetSensor;

    public SensorSubsystem(HardwareMap hardwareMap){
        colorSensor = hardwareMap.get(RevColorSensorV3.class,"colorSensor");
        touchSensor = hardwareMap.get(TouchSensor.class,"armSensor");
        magnetSensor = hardwareMap.get(DigitalChannel.class,"eleSensor");
    }

    public int ReturnColor() {
        return colorSensor.argb();

    }

 //   public int get() {
 //       return colorSensor.red();
 //   }

    public int getColor(){
        int r = colorSensor.red();
        int g = colorSensor.green();
        int b = colorSensor.blue();

        if(r*2 >= g && r >= b){
            // Red
            return 2;
        } else if(g*0.5 >= b && g*0.5 >= r){
            //Green
            return 1;
        } else if(b*2 > r && b > g){
            //blue
            return 2;
        }
        return 1;
    }
}

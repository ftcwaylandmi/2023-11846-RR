package org.firstinspires.ftc.teamcode.subsystems.auxsubsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AuxSubsystem {

    Servo launchServo;

    public AuxSubsystem(HardwareMap hardwareMap) {
        launchServo = hardwareMap.get(Servo.class, "launchServo");
    }

    public void Launch() {
        launchServo.setPosition(0.6);
    }
}

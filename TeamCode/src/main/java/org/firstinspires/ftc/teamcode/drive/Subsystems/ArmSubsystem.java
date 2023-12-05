package org.firstinspires.ftc.teamcode.drive.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmSubsystem {
    ArmConstants armConstants;
    private DcMotor pivotMotor, extendMotor, intakeMotor;

    private Servo launchServo, grabServo;
    public Servo wristServo;
    private double servoSlush = 0;

    public ArmSubsystem(HardwareMap hardwareMap) {

        armConstants = new ArmConstants();

        pivotMotor = hardwareMap.get(DcMotor.class, "pivotMotor");
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        launchServo = hardwareMap.get(Servo.class, "airplaneServo");
        wristServo = hardwareMap.get(Servo.class, "wristServo");

        pivotMotor.setPower(0);
        extendMotor.setPower(0);
        intakeMotor.setPower(0);
    }

    public void extendM(double power) {
        extendMotor.setPower(power);
    }

    public void pivotM(double power) {
        pivotMotor.setPower(power);
    }

    public void intakeM(double power) {
        intakeMotor.setPower(power);
    }

    public void airplaneLaunch() {
        launchServo.setPosition(0);
    }

    public void grabOpen() {
        grabServo.setPosition(0);
    }

    public void grabClose() {
        grabServo.setPosition(1);
    }

    public void wristPickup() {
        wristServo.setPosition(armConstants.wristPickup);
    }

    public void wristPlace() {
        wristServo.setPosition(armConstants.wristPlace);
    }
   }

//    public double wristSlush(double slushAdjust){
//        wristServo.setPosition(wristServo.getPosition() + slushAdjust);
//        return slushAdjust;
//    }
//
//    public double wristUp(){
//        wristServo.setPosition(wristSlush(0.1));
//       return wristUp();
//    }
//
//    public double wristDown(){
//        wristServo.setPosition(wristSlush(-0.1));
//       return wristDown();
//    }
//   }
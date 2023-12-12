package org.firstinspires.ftc.teamcode.drive.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class ArmSubsystem {
    ArmConstants armConstants;
    private DcMotor pivotMotor, extendMotor, intakeMotor;

    private Servo launchServo, grabServo, grabServo2;
    public Servo wristServo, wristServo2;
    private double servoSlush = 0;

    public ArmSubsystem(HardwareMap hardwareMap) {

        armConstants = new ArmConstants();

        pivotMotor = hardwareMap.get(DcMotor.class, "pivotMotor");
        extendMotor = hardwareMap.get(DcMotor.class, "extendMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        launchServo = hardwareMap.get(Servo.class, "airplaneServo");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        wristServo2 = hardwareMap.get(Servo.class, "wristServo2");
        grabServo2 = hardwareMap.get(Servo.class, "grabServo2");

        pivotMotor.setPower(0);
        extendMotor.setPower(0);
        intakeMotor.setPower(0);

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void extendM(double power) {
        extendMotor.setPower(power);
    }

    public void pivotM(double power) {
        pivotMotor.setPower(power);
    }

    public void intakeIn(double power) {intakeMotor.setPower(power);}

    public void intakeOut(double power){ intakeMotor.setPower(-power);}

    public void airplaneLaunch() {
        launchServo.setPosition(0);
    }

    public void grabOpen() {
        grabServo.setPosition(0);
    }

    public void grabClose() {
        grabServo.setPosition(1);
    }

    public void grab2Open() {grabServo2.setPosition(0);}

    public void grab2Close() {grabServo2.setPosition(1);}



    public void wristPickup() {
        wristServo.setPosition(armConstants.wristPickup);
    }

    public void wristPlace() {
        wristServo.setPosition(armConstants.wristPlace);
    }

    public void wrist2Pickup() {
        wristServo2.setPosition(armConstants.wrist2Pickup);
    }

    public void wrist2Place() {
        wristServo2.setPosition(armConstants.wrist2Place);
    }

    public int pivotPosition(){
        return pivotMotor.getCurrentPosition();
    }

    public int extendPosition(){
        return extendMotor.getCurrentPosition();
    }

    public double wristPosition(){
        return wristServo.getPosition();

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
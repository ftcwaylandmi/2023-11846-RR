package org.firstinspires.ftc.teamcode.drive.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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

        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void setOverrideMode() {
        pivotMotor.setPower(0.0);
        extendMotor.setPower(0.0);

        pivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setPositionMode() {
        pivotMotor.setTargetPosition(pivotMotor.getCurrentPosition());
        extendMotor.setTargetPosition(extendMotor.getCurrentPosition());

        pivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pivotMotor.setPower(1.0);
        extendMotor.setPower(1.0);
    }

    private void wait(int timeoutMili) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (timer.milliseconds() < timeoutMili) {

        }
    }

    public void  hangStart(){
        setPositionMode();
        pivotPos(armConstants.pivotHangStart);
        wait(2000);
        extendPos(armConstants.extendHangStart);
        wait(2000);
    }
    public void  hangTight(){
        setPositionMode();
        pivotPos(armConstants.pivotHangTight);
        wait(2000);
        extendPos(armConstants.extendHangTight);
        wait(2000);
    }

    public void homeRoutine(){
        setPositionMode();
        pivotPos(ArmConstants.pivotHome);
        extendPos(ArmConstants.extendHome);
    }
    public void scoringRoutine(){
        setPositionMode();
        pivotPos(ArmConstants.pivotPlace);
        extendPos(ArmConstants.extendPlace);
        wristPlace();
        wrist2Place();
        pivotPos(ArmConstants.pivotPickup);
        extendPos(ArmConstants.extendPickup);
        wristPickup();
        wrist2Pickup();
    }
    public void extendM(double power) {
        setOverrideMode();
        extendMotor.setPower(power);
    }

    public void extendPos(int position) {
        // setPositionMode();
        extendMotor.setTargetPosition(position);
    }

    public void pivotM(double power) {
        setOverrideMode();
        pivotMotor.setPower(power);
    }

    public void pivotPos(int position) {
        // setPositionMode();
        pivotMotor.setTargetPosition(position);
    }

    public void intakeIn(double power) {intakeMotor.setPower(power);}

    public void intakeOut(double power){ intakeMotor.setPower(-power);}

    public void airplaneLaunch() {
        launchServo.setPosition(armConstants.airplaneShoot);
    }

    public void grabOpen() {
        grabServo.setPosition(armConstants.grabPickup);
    }

    public void grabClose() {
        grabServo.setPosition(armConstants.grabPlace);
    }

    public void grab2Open() {grabServo2.setPosition(armConstants.grab2Pickup);}

    public void grab2Close() {grabServo2.setPosition(armConstants.grab2Place);}



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
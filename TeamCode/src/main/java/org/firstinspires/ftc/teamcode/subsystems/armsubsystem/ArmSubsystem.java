package org.firstinspires.ftc.teamcode.subsystems.armsubsystem;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmSubsystem {

    private DcMotorEx pivotMotor, extendMotor;
    private Servo grabServo, pivotServo;

    private MotorConfigurationType pivotMotorConfig;
    private MotorConfigurationType extendMotorConfig;

    private final int TMC = 2000;

    /*
        0 = HOME
        1 = PICKUP_IN
        2 = PICKUP_OUT
        3 = SCORE
     */
    private int stage = 0;

    public ArmSubsystem(@NonNull HardwareMap hardwareMap) {
        pivotMotor = hardwareMap.get(DcMotorEx.class, "pivotMotor");
        extendMotor = hardwareMap.get(DcMotorEx.class, "extendMotor");

        grabServo = hardwareMap.get(Servo.class, "grabServo");
        pivotServo = hardwareMap.get(Servo.class, "pivotServo");

        pivotMotorConfig = pivotMotor.getMotorType().clone();
        extendMotorConfig = extendMotor.getMotorType().clone();

        pivotMotorConfig.setAchieveableMaxRPMFraction(1.0);
        extendMotorConfig.setAchieveableMaxRPMFraction(1.0);

        pivotMotor.setMotorType(pivotMotorConfig);
        extendMotor.setMotorType(extendMotorConfig);

        pivotMotor.setTargetPosition(0);
        extendMotor.setTargetPosition(0);

        pivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void wait(int timeoutMili) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (timer.milliseconds() < timeoutMili) {

        }
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

    public void setMotorAsync(double power) { extendMotor.setPower(power); pivotMotor.setPower(power); }

    public void setMode(DcMotor.RunMode runmode) { extendMotor.setMode(runmode); pivotMotor.setMode(runmode); }

    public void RawExtendMotor(double power) { extendMotor.setPower(power); }
    public void RawPivotMotor(double power) { pivotMotor.setPower(power); }

    /* HOME POSITIONS */
    private void homePositionFromPickupIn() {
        pivotMotor.setTargetPosition(ArmConstants.PMC.HOME);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN);
        stage = 0;
    }

    private void homePositionFromPickupOut() {
        extendMotor.setTargetPosition(ArmConstants.EMC.HOME);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.HOME);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN);
        stage = 0;
    }

    private void homePositionFromScore() {
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.HOME);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.HOME);
        stage = 0;
    }

    /* PICKUP_IN POSITIONS */
    private void pickupInFromHome() {
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.PICKUP_IN);
        stage = 1;
    }

    private void pickupInFromPickupOut() {
        pivotMotor.setTargetPosition(ArmConstants.PMC.HOME);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.PICKUP_IN);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.PICKUP_IN);
        stage = 1;
    }

    private void pickupInFromScore() {
        extendMotor.setTargetPosition(ArmConstants.EMC.PICKUP_IN);
        wait(2000);
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.PICKUP_IN);
        stage = 1;
    }

    /* PICKUP_OUT POSITIONS */
    private void pickupOutFromHome() {
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_OUT);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.PICKUP_OUT);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.PICKUP_OUT);
        stage = 2;
    }

    private void pickupOutFromPickupIn() {
        pivotMotor.setTargetPosition(ArmConstants.PMC.HOME);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_OUT);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.PICKUP_OUT);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.PICKUP_OUT);
        stage = 2;
    }

    private void pickupOutFromScore() {
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_OUT);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.PICKUP_OUT);
        wait(TMC);
        pivotMotor.setTargetPosition(ArmConstants.PMC.PICKUP_OUT);
        stage = 2;
    }

    /* SCORE POSITIONS */
    private void scoreFromHome() {
        pivotMotor.setTargetPosition(ArmConstants.PMC.SCORE);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.SCORE);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.SCORE);
        stage = 3;
    }

    private void scoreFromPickupIn() {
        pivotMotor.setTargetPosition(ArmConstants.PMC.SCORE);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.SCORE);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.SCORE);
        stage = 3;
    }

    private void scoreFromPickupOut() {
        pivotMotor.setTargetPosition(ArmConstants.PMC.SCORE);
        wait(TMC);
        extendMotor.setTargetPosition(ArmConstants.EMC.SCORE);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.SCORE);
        stage = 3;
    }

    private void hold() {
        pivotMotor.setTargetPosition(pivotMotor.getTargetPosition());
        extendMotor.setTargetPosition(extendMotor.getTargetPosition());
    }

    public int getCurrentStage() {
        return stage;
    }

    public void Home() {
        switch (getCurrentStage()) {
            case 0:
                hold();
                break;
            case 1:
                homePositionFromPickupIn();
                break;
            case 2:
                homePositionFromPickupOut();
                break;
            case 3:
                homePositionFromScore();
                break;
        }
    }

    public void PickupIn() {
        switch (getCurrentStage()) {
            case 0:
                pickupInFromHome();
                break;
            case 1:
                hold();
                break;
            case 2:
                pickupInFromPickupOut();
                break;
            case 3:
                pickupInFromScore();
                break;
        }
    }

    public void PickupOut() {
        switch (getCurrentStage()) {
            case 0:
                pickupOutFromHome();
                break;
            case 1:
                pickupOutFromPickupIn();
                break;
            case 2:
                hold();
                break;
            case 3:
                pickupOutFromScore();
                break;
        }
    }


    public void Score() {
        switch (getCurrentStage()) {
            case 0:
                scoreFromHome();
                break;
            case 1:
                scoreFromPickupIn();
                break;
            case 2:
                scoreFromPickupOut();
                break;
            case 3:
                hold();
                break;
        }
    }

    public void GrabPixel() {
        grabServo.setPosition(ArmConstants.GSC.GRAB);
    }

    public void ScorePixelDrop() {
        grabServo.setPosition(ArmConstants.GSC.RELEASE);
        wait(TMC);
        pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN);
    }

    public void ReleasePixel() {
        grabServo.setPosition(ArmConstants.GSC.RELEASE);
    }

    public void HomePixel() { pivotServo.setPosition(ArmConstants.PSC.HOME); }

    public void PickupInPixel() { pivotServo.setPosition(ArmConstants.PSC.PICKUP_IN); }

    public void PickupOutPixel() { pivotServo.setPosition(ArmConstants.PSC.PICKUP_OUT); }

    public void ScorePixel() { pivotServo.setPosition(ArmConstants.PSC.SCORE); }

    public int GetExtendPosition() {
        return extendMotor.getCurrentPosition();
    }

    public int GetPivotPosition() {
        return pivotMotor.getCurrentPosition();
    }
}
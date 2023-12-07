package org.firstinspires.ftc.teamcode.subsystems.intakesubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public class IntakeSubsystem {

    private DcMotorEx intakeMotor;

    private MotorConfigurationType intakeConfig;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");

        intakeConfig = intakeMotor.getMotorType().clone();

        intakeConfig.setAchieveableMaxRPMFraction(1.0);

        intakeMotor.setMotorType(intakeConfig);

        intakeMotor.setTargetPosition(0);

        intakeMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void setMode(DcMotorEx.RunMode runMode) {
        intakeMotor.setMode(runMode);
    }

    public void setMotorAsync(double power) { intakeMotor.setPower(power); }

    public void RawIntakeMotor(double power) { intakeMotor.setPower(power); }

    public void SetPosition(int target) {
        intakeMotor.setTargetPosition(target);
    }

    public int GetIntakePosition() {
        return intakeMotor.getCurrentPosition();
    }

}
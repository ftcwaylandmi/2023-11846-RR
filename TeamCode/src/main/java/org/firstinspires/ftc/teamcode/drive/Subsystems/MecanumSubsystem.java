package org.firstinspires.ftc.teamcode.drive.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config

public class MecanumSubsystem {

    private DcMotor frontLeft, frontRight, rearLeft, rearRight;

    public MecanumSubsystem(HardwareMap hardwareMap) {

        frontLeft = hardwareMap.get(DcMotor.class, "left_front");
        frontRight = hardwareMap.get(DcMotor.class, "right_front");
        rearLeft = hardwareMap.get(DcMotor.class, "left_rear");
        rearRight = hardwareMap.get(DcMotor.class, "right_rear");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Drive(0,0,0,0);
    }

    public void Drive(double fL, double fR, double rL, double rR) {
        frontLeft.setPower(fL);
        frontRight.setPower(fR);
        rearLeft.setPower(rL);
        rearRight.setPower(rR);
    }

    public void Forward(double power) {
        Drive(power,power,power,power);
    }

    public void Strafe(double power) {
        Drive(-power, -power, power, power);
    }

    public void Turn(double power) {
        Drive(power, -power, power, -power);
    }

    public void TeleOperatedDrive(double forward, double strafe, double turn) {

        double[] speeds = {
                (forward + strafe + turn),
                (forward - strafe - turn),
                (forward - strafe + turn),
                (forward + strafe - turn)
        };

        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        }

        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        frontLeft.setPower(speeds[0]);
        frontRight.setPower(-1*speeds[1]);
        rearLeft.setPower(speeds[2]);
        rearRight.setPower(-1*speeds[3]);
    }
}

package org.firstinspires.ftc.teamcode.subsystems.mecanumsubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumSubsystem {

    DcMotor front_left = null;
    DcMotor front_right = null;
    DcMotor back_left = null;
    DcMotor back_right = null;

    public MecanumSubsystem(HardwareMap hardwareMap) {
        front_left = hardwareMap.get(DcMotor.class, "left_front");
        front_right = hardwareMap.get(DcMotor.class, "right_front");
        back_left = hardwareMap.get(DcMotor.class, "left_rear");
        back_right = hardwareMap.get(DcMotor.class, "right_rear");

        front_left.setPower(0);
        front_right.setPower(0);
        back_left.setPower(0);
        back_right.setPower(0);

        front_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

        front_left.setPower(speeds[0]);
        front_right.setPower(-1*speeds[1]);
        back_left.setPower(speeds[2]);
        back_right.setPower(-1*speeds[3]);
    }

}
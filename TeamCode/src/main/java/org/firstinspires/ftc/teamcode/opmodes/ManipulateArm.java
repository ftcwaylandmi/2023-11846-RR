package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.auxsubsystem.AuxSubsystem;

@TeleOp(name = "ManipulateArm", group = "Debug")
public class ManipulateArm extends OpMode {
    ArmSubsystem armSubsystem;
    AuxSubsystem auxSubsystem;

    @Override
    public void init() {

        armSubsystem = new ArmSubsystem(hardwareMap);
        auxSubsystem = new AuxSubsystem(hardwareMap);

        armSubsystem.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        armSubsystem.RawExtendMotor(-gamepad2.left_stick_y);
        armSubsystem.RawPivotMotor(-gamepad2.right_stick_y);

        /* Grab Servo */
        if (gamepad2.left_bumper) {
            armSubsystem.ReleasePixel();
        }else if (gamepad2.right_bumper) {
            armSubsystem.GrabPixel();
        }

        if (gamepad2.a) {
            armSubsystem.ScorePixel();
        }else if (gamepad2.b) {
            armSubsystem.PickupIn();
        }else if (gamepad2.x) {
            armSubsystem.PickupOut();
        } else if (gamepad2.y) {
            armSubsystem.HomePixel();
        }
    }
}

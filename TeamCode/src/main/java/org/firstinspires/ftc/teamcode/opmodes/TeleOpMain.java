package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.auxsubsystem.AuxSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.intakesubsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.mecanumsubsystem.MecanumSubsystem;

@TeleOp(name = "TeleOpMain", group = "Competition")
public class TeleOpMain extends OpMode {

    ArmSubsystem armSubsystem;
    AuxSubsystem auxSubsystem;
    IntakeSubsystem intakeSubsystem;

    MecanumSubsystem mecanumSubsystem;
    private int intakeOn = -1;
    boolean override = false;

    @Override
    public void init() {

        mecanumSubsystem = new MecanumSubsystem(hardwareMap);

        armSubsystem = new ArmSubsystem(hardwareMap);
        auxSubsystem = new AuxSubsystem(hardwareMap);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);

        armSubsystem.setMotorAsync(1);
        intakeSubsystem.setMotorAsync(1);
        armSubsystem.GrabPixel();
        armSubsystem.PickupInPixel();
    }

    @Override
    public void loop() {

        mecanumSubsystem.TeleOperatedDrive(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x
        );

        /* Arm Positions */


        if (gamepad2.left_trigger < 0.1) {
            if (override) {
                armSubsystem.setPositionMode();
            }
            override = false;
            if (gamepad2.x) {
                armSubsystem.PickupIn();
            }else if(gamepad2.a) {
                armSubsystem.Home();
            }else if(gamepad2.y) {
                armSubsystem.Score();
            }
        } else if (gamepad2.left_trigger > 0.1) {
            if(!override) {
                armSubsystem.setOverrideMode();
            }
            override = true;
            armSubsystem.RawExtendMotor(-gamepad2.left_stick_y);
            armSubsystem.RawPivotMotor(-gamepad2.right_stick_y);
        }

        /* Grab Servo */
        if (gamepad2.left_bumper) {
            armSubsystem.ReleasePixel();
        }else if (gamepad2.right_bumper) {
            armSubsystem.GrabPixel();
        }

        /* Score Pixel */
        if (gamepad2.dpad_down) {
            armSubsystem.ScorePixelDrop();
        }

        /* AuxSubsystem */
        if(gamepad1.a) {
            auxSubsystem.Launch();
        }

        if(gamepad1.b) {
            intakeOn = intakeOn * (-1);
        }
        if (intakeOn == -1) {
            intakeSubsystem.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeSubsystem.RawIntakeMotor(0);
        }
        if (intakeOn == 1) {
            intakeSubsystem.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeSubsystem.RawIntakeMotor(1.0);
        }


    }
}

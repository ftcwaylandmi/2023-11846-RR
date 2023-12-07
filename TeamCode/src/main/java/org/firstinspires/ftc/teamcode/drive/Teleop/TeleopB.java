package org.firstinspires.ftc.teamcode.drive.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.Subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.drive.Subsystems.MecanumSubsystem;


@TeleOp(name = "TeleOpB", group = "TeleOp")
public class TeleopB extends OpMode {

    MecanumSubsystem mecanumSubsystem;
    ArmSubsystem armSubsystem;
    @Override
    public void init(){
        mecanumSubsystem = new MecanumSubsystem(hardwareMap);
        armSubsystem = new ArmSubsystem(hardwareMap);
    }

    @Override
    public void loop() {

        telemetry.addData("PivotPos", armSubsystem.pivotPosition());
        telemetry.addData("ExtendPos", armSubsystem.extendPosition());
        telemetry.addData("WristPos", armSubsystem.wristPosition());
        telemetry.update();
        double forward = -gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        double extend = gamepad2.left_stick_y;
        double pivot = -gamepad2.right_stick_y;
        double intake = gamepad1.right_trigger;

        armSubsystem.intakeM(intake);
        armSubsystem.extendM(extend);
        armSubsystem.pivotM(pivot);
        mecanumSubsystem.TeleOperatedDrive(forward, -strafe, turn);

        if(gamepad2.dpad_left){
            armSubsystem.airplaneLaunch();
        }else if(gamepad2.left_bumper){
            armSubsystem.grabOpen();
        }else if(gamepad2.right_bumper){
            armSubsystem.grabClose();
        }else if(gamepad2.left_trigger>0.1){
            armSubsystem.wristPickup();
        }else if(gamepad2.right_trigger>0.1){
            armSubsystem.wristPlace();
//        }else if(gamepad2.dpad_left){
//            armSubsystem.wristSlush(0.1);
//            armSubsystem.wristUp();
//        }else if(gamepad2.dpad_down){
//            armSubsystem.wristSlush(-0.1);
//            armSubsystem.wristDown();
        }
    }

}
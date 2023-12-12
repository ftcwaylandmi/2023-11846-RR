package org.firstinspires.ftc.teamcode.drive.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.Subsystems.ArmConstants;
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
        telemetry.addData("RTrig", gamepad1.right_trigger);
        telemetry.update();
        double forward = -gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        double extend = gamepad2.left_stick_y;
        double pivot = -gamepad2.right_stick_y;
        double intake = gamepad1.right_trigger;
        double intakeOut = gamepad1.left_trigger;

        armSubsystem.intakeIn(intake);
        armSubsystem.intakeOut(intakeOut);
        armSubsystem.extendM(extend);
        armSubsystem.pivotM(pivot);
        mecanumSubsystem.TeleOperatedDrive(forward, -strafe, turn);

        if(gamepad2.dpad_up){
            armSubsystem.airplaneLaunch();
        }else if(gamepad2.left_bumper){
            armSubsystem.grabOpen();
            armSubsystem.grab2Open();
        }else if(gamepad2.right_bumper){
            armSubsystem.grabClose();
            armSubsystem.grab2Close();
        }else if(gamepad2.left_trigger>0.1){
            armSubsystem.wristPickup();
            armSubsystem.wrist2Pickup();
        }else if(gamepad2.right_trigger>0.1) {
            armSubsystem.wristPlace();
            armSubsystem.wrist2Place();
        }

        if (gamepad2.b) {
            armSubsystem.pivotM(ArmConstants.pivotPlace);
            armSubsystem.extendM(ArmConstants.extendPlace);
            armSubsystem.wristPlace();
        }else if (gamepad2.y) {
            armSubsystem.pivotM(ArmConstants.pivotHang);
            armSubsystem.extendM(ArmConstants.extendHang);
        }else if (gamepad2.a) {
            armSubsystem.pivotM(ArmConstants.pivotHome);
            armSubsystem.extendM(ArmConstants.extendHome);
        } else if (gamepad2.x) {
            armSubsystem.pivotM(ArmConstants.pivotPickup);
            armSubsystem.extendM(ArmConstants.extendPickup);
            armSubsystem.wristPickup();
        }

//        }else if(gamepad2.dpad_left){
//            armSubsystem.wristSlush(0.1);
//            armSubsystem.wristUp();
//        }else if(gamepad2.dpad_down){
//            armSubsystem.wristSlush(-0.1);
//            armSubsystem.wristDown();
        }
    }
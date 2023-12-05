package org.firstinspires.ftc.teamcode.autons;//package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.sensors.SensorSubsystem;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class PurpleOnly_2023_22154 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        ArmSubsystem armSubsystem = new ArmSubsystem(hardwareMap);
        SensorSubsystem sensorSubsystem = new SensorSubsystem(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        // Purple-only Trajectory 1, Strafe right, until color sensors can view left/right spike positions
        Trajectory trajPOA1 = drive.trajectoryBuilder(new Vector2d(-63,36))
                .splineToSplineHeading(new Pose2d(-42,36,Math.toRadians(0)),0)
                .build();

        // Purple-only Trajectory 3, drive to Right spike position
        Trajectory trajPOR2 = drive.trajectoryBuilder(new Pose2d(-41,36,Math.toRadians(225)),Math.toRadians(45))
                .splineToSplineHeading(new Pose2d(-41,30),Math.toRadians(270))
                .build();

        // Purple-only Trajectory 2, drive to Center spike position
        Trajectory trajPOC2 = drive.trajectoryBuilder(new Pose2d(-42,36,Math.toRadians(0)),Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(-32,33,Math.toRadians(0)),Math.toRadians(0))
                .build();

        // Purple-only Trajectory 2, drive to Left spike position
        Trajectory trajPOL2 = drive.trajectoryBuilder(new Pose2d(-42,-36,Math.toRadians(0)),Math.toRadians(45))
                .splineTo(new Vector2d(-41,36),Math.toRadians(0))
                .build();

        Trajectory trajPOL3 = drive.trajectoryBuilder(new Pose2d(-41,36,Math.toRadians(45)),Math.toRadians(0))
                .splineTo(new Vector2d(-38,48),Math.toRadians(0))
                .build();



        // ~~~~~~~~~~~~~~Trajectories done~~~~~~~~~~~~~~~~~'




        // ~~~~~~~~~~Start of Autonomous execution~~~~~~~~~~~
        drive.followTrajectory(trajPOA1);
        // read block position
    if (sensorSubsystem.getFrontColor()==2){
            drive.followTrajectory(trajPOL2);
            armSubsystem.OpenGrabServo();
            armSubsystem.wait(1000);
        } else {
            if (sensorSubsystem.getRearColor()==2){
                drive.followTrajectory(trajPOR2);
                armSubsystem.OpenGrabServo();
                armSubsystem.wait(1000);
            } else {
                drive.followTrajectory(trajPOC2);
                armSubsystem.OpenGrabServo();
                armSubsystem.wait(1000);
            }
        }
    }
}

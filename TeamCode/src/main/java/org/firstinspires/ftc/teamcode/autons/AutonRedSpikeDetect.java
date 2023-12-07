package org.firstinspires.ftc.teamcode.autons;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.sensors.SensorSubsystem;

@Autonomous(name = "AutonRedSpikeDetect", group = "Auton")
public class AutonRedSpikeDetect extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        ArmSubsystem armSubsystem = new ArmSubsystem(hardwareMap);
        SensorSubsystem sensorSubsystem = new SensorSubsystem(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory trajInit = drive.trajectoryBuilder(new Pose2d(-63, 35), 1.5707963267948966)
                .strafeRight(40)
                .build();
        armSubsystem.CloseGrabServo();
        armSubsystem.wait(1000);
        drive.followTrajectory(trajInit);
        if (sensorSubsystem.getFrontRed() > sensorSubsystem.getRearRed()) {
            // Front
            if (sensorSubsystem.getFrontRed() > sensorSubsystem.getFrontGreen()/1.5) {
                // Front

                // Move to score
                Trajectory traj = drive.trajectoryBuilder(new Pose2d(0, 0))
                        .strafeRight(16)
                        .forward(12)
                        .build();
                drive.followTrajectory(traj);
                armSubsystem.wait(1000);

                // Release Pixel
                armSubsystem.OpenGrabServo();
                armSubsystem.wait(1000);

                // Move Back
                Trajectory traj2 = drive.trajectoryBuilder(new Pose2d(0, 0))
                        .back(12)
                        .build();
                drive.followTrajectory(traj2);
            } else {
                // Right

                // Move to score
                drive.turn(-90);
                Trajectory traj = drive.trajectoryBuilder(new Pose2d(0,0))
                        .forward(20)
                        .build();
                drive.followTrajectory(traj);
                armSubsystem.wait(1000);

                // Release Pixel
                armSubsystem.OpenGrabServo();
                armSubsystem.wait(1000);

                // Move Back
                Trajectory traj2 = drive.trajectoryBuilder(new Pose2d(0, 0))
                        .back(20)
                        .build();
                drive.followTrajectory(traj2);
            }
        } else if (sensorSubsystem.getFrontRed() < sensorSubsystem.getRearRed()) {
            // Rear
            if (sensorSubsystem.getRearRed() > sensorSubsystem.getRearGreen()/1.5) {
                // Rear

                // Move to score
                drive.turn(180);
                Trajectory traj = drive.trajectoryBuilder(new Pose2d(0,0))
                        .forward(20)
                        .build();
                drive.followTrajectory(traj);
                armSubsystem.wait(1000);

                // Release Pixel
                armSubsystem.OpenGrabServo();
                armSubsystem.wait(1000);

                // Move Back
                Trajectory traj2 = drive.trajectoryBuilder(new Pose2d(0, 0))
                        .back(20)
                        .build();
                drive.followTrajectory(traj2);
            } else {
                // Right

                // Move to score
                drive.turn(-90);
                Trajectory traj = drive.trajectoryBuilder(new Pose2d(0,0))
                        .forward(20)
                        .build();
                drive.followTrajectory(traj);
                armSubsystem.wait(1000);

                // Release Pixel
                armSubsystem.OpenGrabServo();

                // Move Back
                Trajectory traj2 = drive.trajectoryBuilder(new Pose2d(0, 0))
                        .back(20)
                        .build();
                drive.followTrajectory(traj2);
            }
        }

    }
}

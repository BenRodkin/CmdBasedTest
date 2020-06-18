package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.commands.auto.DriveByTimer;
import org.firstinspires.ftc.teamcode.robot.commands.auto.GripperSetState;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpArmControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpCapstoneControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpDriveControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpFoundationControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpGripperControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpIntakeControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpLiftControl;
import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpTapeDriveControl;
import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;
import org.firstinspires.ftc.teamcode.robot.subsystems.Capstone;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Foundation;
import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Lift;
import org.firstinspires.ftc.teamcode.robot.subsystems.TapeDrive;

@Autonomous
public class TestDogeAuto extends LinearOpMode implements DogeOpMode {
    public void runOpMode(){
        DogeCommander commander = new DogeCommander(this);

        Drive drive             = new Drive(hardwareMap);
        Intake intake           = new Intake(hardwareMap);
        Foundation foundation   = new Foundation(hardwareMap);
        TapeDrive tapeDrive     = new TapeDrive(hardwareMap);
        Capstone capstone       = new Capstone(hardwareMap);
        Arm arm                 = new Arm(hardwareMap);
        Gripper gripper         = new Gripper(hardwareMap);
        Lift lift               = new Lift(hardwareMap);

        commander.registerSubsystem(drive);
        commander.registerSubsystem(intake);
        commander.registerSubsystem(foundation);
        commander.registerSubsystem(tapeDrive);
        commander.registerSubsystem(capstone);
        commander.registerSubsystem(arm);
        commander.registerSubsystem(gripper);
        commander.registerSubsystem(lift);
        commander.init();

        waitForStart();

        commander.runCommandsParallel(
                new DriveByTimer(drive, 1, 0.3),
                new GripperSetState(gripper, 0.5, Gripper.GripState.GRIP, Gripper.WristState.GRAB)
        );




        commander.stop();
    }



}

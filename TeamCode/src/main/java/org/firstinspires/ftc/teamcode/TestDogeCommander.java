package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

@TeleOp
public class TestDogeCommander extends LinearOpMode implements DogeOpMode {
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
                new TeleOpDriveControl(drive, gamepad1),
                new TeleOpIntakeControl(intake, gamepad2),
                new TeleOpFoundationControl(foundation, gamepad1),
                new TeleOpTapeDriveControl(tapeDrive, gamepad1),
                new TeleOpCapstoneControl(capstone, gamepad2),
                new TeleOpArmControl(arm, gamepad2),
                new TeleOpGripperControl(gripper, gamepad2),
                new TeleOpLiftControl(lift, gamepad2)
        );


        commander.stop();

    }



}
/*

[p1,p2,p3,...,pn,del]
set m1 to p1 for 1->n
wait del


while opmodeisactive(){
robotState = 2.dpd
if robotState is normal =>
 commander.runCommandsParallel(
               normal teleop controls
        );
        <
        else if robotState is capping =>
         commander.runCommandsParallel(
                driving controls
                override auxiliary controls & auto cap
        );
        <
}

0) Automation (Autocap)
1) Odometry
sqrt(2)) Camera
2) LED indicators
3) 7-Segment LED display
4)











 */

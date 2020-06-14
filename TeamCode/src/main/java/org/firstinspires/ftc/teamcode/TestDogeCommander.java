package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecommander.DogeCommander;
import com.disnodeteam.dogecommander.DogeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.commands.teleop.TeleOpDriveControl;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;

@TeleOp
public class TestDogeCommander extends LinearOpMode implements DogeOpMode {
    public void runOpMode(){
        DogeCommander commander = new DogeCommander(this);

        Drive drive = new Drive(hardwareMap);

        commander.registerSubsystem(drive);
        commander.init();

        waitForStart();

        commander.runCommandsParallel(
                new TeleOpDriveControl(drive, gamepad1)
        );


        commander.stop();

    }



}

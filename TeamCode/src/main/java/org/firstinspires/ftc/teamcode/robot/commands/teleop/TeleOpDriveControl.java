package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;

public class TeleOpDriveControl implements Command {
    // Subsystem
    private Drive drive;
    // Input
    private Gamepad gamepad;

    // Constructor
    public TeleOpDriveControl(Drive drive, Gamepad gamepad){
        this.drive = drive;
        this.gamepad = gamepad;
    }

    @Override
    public void start() {
        drive.setPower(0,0);
    }

    @Override
    public void periodic() {
        drive.setPower(
                -gamepad.left_stick_y + gamepad.right_stick_x,
                -gamepad.left_stick_y - gamepad.right_stick_x
        );
    }

    @Override
    public void stop() {
        drive.setPower(0,0);
    }

    @Override
    public boolean isCompleted(){
        return false;
    }

















}

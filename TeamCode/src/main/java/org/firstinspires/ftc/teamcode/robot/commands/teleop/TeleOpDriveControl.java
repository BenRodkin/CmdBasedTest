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
        drive.setPower(0,0, 0,0);
    }

    @Override
    public void periodic() {
        double dri = -gamepad.left_stick_y;
        double str = gamepad.left_stick_x;
        double twi = -gamepad.right_stick_x;
        drive.setPower(
                (dri + str + twi) * 1,
                (dri - str - twi) * 1,
                (dri - str + twi) * 1,
                (dri + str - twi) * 1
        );
    }

    @Override
    public void stop() {
        drive.setPower(0,0,0,0);
    }

    @Override
    public boolean isCompleted(){
        return false;
    }

















}

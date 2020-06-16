package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;

public class TeleOpDriveControl implements Command {
    // Subsystem
    private Drive drive;
    // Input
    private Gamepad gamepad;

    private final double TRIGGER_THRESHOLD = 0.7;

    // Constructor
    public TeleOpDriveControl(Drive drive, Gamepad gamepad) {
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
        boolean goSlow = gamepad.left_trigger > TRIGGER_THRESHOLD;


        drive.setMecanumPower(dri, str, twi, goSlow);
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

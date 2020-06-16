package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.TapeDrive;

public class TeleOpTapeDriveControl implements Command {
    private TapeDrive tapeDrive;

    private Gamepad gamepad;

    public TeleOpTapeDriveControl(TapeDrive tapeDrive, Gamepad gamepad){
        this.tapeDrive = tapeDrive;
        this.gamepad = gamepad;
    }

    public void start(){
        tapeDrive.setPower(0.0);
    }

    public void periodic(){
        tapeDrive.setPower(gamepad.right_trigger - gamepad.left_trigger);
    }

    public void stop(){
        tapeDrive.setPower(0.0);
    }

    public boolean isCompleted(){
        return false;
    }
}

package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Capstone;

public class TeleOpCapstoneControl implements Command {
    private Capstone capstone;

    private Gamepad gamepad;

    public TeleOpCapstoneControl(Capstone capstone, Gamepad gamepad) {
        this.capstone = capstone;
        this.gamepad = gamepad;
    }

    @Override
    public void start(){
        capstone.setState(Capstone.State.STOW);
    }

    @Override
    public void periodic(){

        if(gamepad.dpad_down){
            capstone.setState(Capstone.State.CAP);
        } else if(gamepad.dpad_up){
            capstone.setState(Capstone.State.STOW);
        }
    }

    @Override
    public void stop(){
    }

    @Override
    public boolean isCompleted(){
        return false;
    }
}

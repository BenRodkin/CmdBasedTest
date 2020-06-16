package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Foundation;

public class TeleOpFoundationControl implements Command {
    private Foundation foundation;
    private Gamepad gamepad;

    private ElapsedTime time;

    private final double COOLDOWN = 1.0;
    private double lastToggle = -COOLDOWN;
    private double timestamp;
    private boolean ready;
    private boolean gripping;

    public TeleOpFoundationControl(Foundation foundation, Gamepad gamepad) {
        time = new ElapsedTime();

        this.foundation = foundation;
        this.gamepad = gamepad;
    }

    @Override
    public void start(){
        time.reset();
        foundation.setState(Foundation.State.OPEN);
    }

    public void periodic(){
        timestamp = time.seconds();
        ready = (timestamp - lastToggle > COOLDOWN);

        gripping = gamepad.a;

        if(gripping && ready) {
            lastToggle = timestamp;
            foundation.toggleState();
        }

    }

    public void stop(){
    }

    public boolean isCompleted(){
        return false;
    }

}

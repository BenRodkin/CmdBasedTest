package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;

public class TeleOpIntakeControl implements Command {
    private Intake intake;

    private Gamepad gamepad;

    private final double MAX_POWER = 0.6;

    public TeleOpIntakeControl(Intake intake, Gamepad gamepad){
        this.intake = intake;
        this.gamepad = gamepad;
    }

    @Override
    public void start(){ intake.setPower(0);}

    @Override
    public void periodic(){
        intake.setPower(
                ((gamepad.left_bumper ? 1.0 : 0.0) - (gamepad.right_bumper ? 1.0 : 0.0) ) * MAX_POWER
        );
    }

    @Override
    public void stop(){
        intake.setPower(0);
    }

    @Override
    public boolean isCompleted(){
        return false;
    }
}

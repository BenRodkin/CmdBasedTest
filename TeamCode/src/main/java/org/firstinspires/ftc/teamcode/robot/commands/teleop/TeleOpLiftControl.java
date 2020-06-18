package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Lift;

public class TeleOpLiftControl implements Command {
    private Lift lift;

    private Gamepad gamepad;

    private double power;
    private boolean liftInput;
    private boolean resetLift;

    private final double LIFT_INPUT_THRESHOLD = 0.01;

    public TeleOpLiftControl(Lift lift, Gamepad gamepad) {
        this.lift = lift;
        this.gamepad = gamepad;
    }

    @Override
    public void start() {
        lift.setPower(0.0);
    }

    @Override
    public void periodic() {
        power = gamepad.left_trigger - gamepad.right_trigger;
        if(gamepad.left_bumper) resetLift = true;
        liftInput = Math.abs(power) > LIFT_INPUT_THRESHOLD;

        lift.setPower(power);
        if(resetLift && !liftInput) {
            lift.setState(Lift.State.MANUAL);
        }
        if(!lift.isBusy() || liftInput) {
            resetLift = false;
            lift.setState(Lift.State.RESET);
        }

    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}

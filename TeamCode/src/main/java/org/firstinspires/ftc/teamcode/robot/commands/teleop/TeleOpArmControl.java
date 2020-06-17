package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;

public class TeleOpArmControl implements Command {
    private Arm arm;

    private Gamepad gamepad;

    private final double ARM_SPEED_SCALAR = 0.6;

    public TeleOpArmControl(Arm arm, Gamepad gamepad){
        this.arm = arm;
        this.gamepad = gamepad;
    }

    @Override
    public void start(){
        arm.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setPower(0.0);
    }

    @Override
    public void periodic(){
        arm.setPower(gamepad.left_stick_y * ARM_SPEED_SCALAR);
    }

    @Override
    public void stop(){
        arm.setPower(0.0);
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}

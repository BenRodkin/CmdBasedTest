package org.firstinspires.ftc.teamcode.robot.commands.teleop;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;

public class TeleOpGripperControl implements Command {
    private Gripper gripper;

    private Gamepad gamepad;

    private ElapsedTime time;

    private boolean wristStore;
    private boolean wristGrap;
    private double wristManual;

    private final double WRIST_SCALAR = 0.000005;
    private final double WRIST_THRESHOLD = 0.5;


    private final double COOLDOWN = 1.00;
    private double lastToggle = -COOLDOWN;
    private double timestamp;
    private boolean ready;
    private boolean gripGrip;

    public TeleOpGripperControl(Gripper gripper, Gamepad gamepad){
        this.gripper = gripper;
        this.gamepad = gamepad;

        time = new ElapsedTime();
    }

    @Override
    public void start(){
        time.reset();

        gripper.setWristState(Gripper.WristState.START);
        gripper.setGripState(Gripper.GripState.OPEN);
    }

    @Override
    public void periodic(){
        timestamp = time.seconds();
        ready = (timestamp - lastToggle) > COOLDOWN;

        wristStore = gamepad.right_bumper;
        wristGrap = gamepad.x;
        gripGrip = gamepad.a;

        wristManual = gamepad.right_stick_y;

        if(Math.abs(wristManual) > WRIST_THRESHOLD) {
            gripper.setWristState(Gripper.WristState.MANUAL);
            gripper.setWristPos(gripper.getWristPos() + (wristManual * WRIST_SCALAR));
        }
        else if(wristStore) gripper.setWristState(Gripper.WristState.STORE);
        else if(wristGrap) gripper.setWristState(Gripper.WristState.GRAB);

        if(gripGrip && ready){
            lastToggle = timestamp;
            gripper.toggleGripState();
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

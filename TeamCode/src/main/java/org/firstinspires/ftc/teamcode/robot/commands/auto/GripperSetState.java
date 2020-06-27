package org.firstinspires.ftc.teamcode.robot.commands.auto;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Gripper;

public class GripperSetState implements Command {
    private Gripper gripper;
    private Gripper.GripState gripState;
    private Gripper.WristState wristState;

    private ElapsedTime timer;
    private double waitTime;

    public GripperSetState(Gripper gripper, double waitTime, Gripper.GripState gripState, Gripper.WristState wristState){
        timer = new ElapsedTime();
        this.waitTime = waitTime;

        this.gripper = gripper;
        this.gripState = gripState;
        this.wristState = wristState;
    }

    public GripperSetState(Gripper gripper, double waitTime, Gripper.GripState gripState){
        timer = new ElapsedTime();
        this.waitTime = waitTime;

        this.gripper = gripper;
        this.gripState = gripState;

        this.wristState = null;
    }

    public GripperSetState(Gripper gripper, double waitTime, Gripper.WristState wristState){
        timer = new ElapsedTime();
        this.waitTime = waitTime;

        this.gripper = gripper;
        this.wristState = wristState;

        this.gripState = null;
    }

    @Override
    public void start(){
        timer.reset();
    }

    @Override
    public void periodic(){
        if(timer.seconds() > waitTime){
            if (gripState != null) gripper.setGripState(gripState);
            if (wristState != null) gripper.setWristState(wristState);
        }
    }

    @Override
    public void stop(){
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}

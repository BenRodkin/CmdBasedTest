package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Gripper implements Subsystem {
    private HardwareMap hardwareMap;

    private Servo grip;
    private Servo wrist;

    private double wristPos;

    private WristState wristState = WristState.START;
    private GripState gripState = GripState.OPEN;

    public enum WristState{
        PLACE   (0.20),
        GRAB    (1.00),
        START   (0.30),
        STORE   (0.37),
        MANUAL  (-1.00);

        private final double pos;

        WristState(double pos){
            this.pos = pos;
        }
    }

    public enum GripState{
        OPEN(1.00),
        GRIP(0.00);

        private final double pos;

        GripState(double pos){
            this.pos = pos;
        }
    }

    public Gripper(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    public void initHardware(){
        grip = hardwareMap.get(Servo.class, "gripper");
        wrist = hardwareMap.get(Servo.class, "wrist");


    }

    public void periodic(){
        wrist.setPosition(wristPos);
        grip.setPosition(gripState.pos);

    }

    public void setWristState(WristState wristState){
        this.wristState = wristState;
        if(!wristState.equals(WristState.MANUAL)) wristPos = wristState.pos;
    }

    public void setGripState(GripState gripState){
        this.gripState = gripState;
    }

    public void toggleGripState() {
        if(gripState.equals(GripState.OPEN)) setGripState(GripState.GRIP);
        else if(gripState.equals(GripState.GRIP)) setGripState(GripState.OPEN);
    }

    public void setWristPos(double pos) {
        this.wristPos = Range.clip(pos, 0.0, 1.0);
    }

    public double getWristPos(){
        return wristPos;
    }

}

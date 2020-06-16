package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.disnodeteam.dogecommander.Subsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Foundation implements Subsystem {
    private HardwareMap hardwareMap;

    private Servo foundLeft;
    private Servo foundRight;

    private State state = State.OPEN;

    public enum State{
        OPEN(1.0),
        GRIP(0.3);

        private final double pos;

        State(double state){
            this.pos = state;
        }
    }

    public void setState(State state){
        this.state = state;
    }

    public Foundation(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    @Override
    public void initHardware(){
        foundLeft = hardwareMap.get(Servo.class, "found_left");
        foundRight = hardwareMap.get(Servo.class, "found_right");

        foundLeft.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void periodic(){
        foundLeft.setPosition(state.pos - State.GRIP.pos);
        foundRight.setPosition(state.pos);
    }

    public void toggleState(){
        if(state.equals(State.OPEN)) setState(State.GRIP);
        else if(state.equals(State.GRIP)) setState(State.OPEN);
    }

}

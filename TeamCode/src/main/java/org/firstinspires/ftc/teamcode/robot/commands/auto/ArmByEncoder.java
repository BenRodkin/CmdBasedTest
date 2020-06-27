package org.firstinspires.ftc.teamcode.robot.commands.auto;

import com.disnodeteam.dogecommander.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.subsystems.Arm;

public class ArmByEncoder implements Command {
    private Arm arm;

    private ElapsedTime timer;
    private int counts;
    private double power;
    private double timeout;

    private final double DEFAULT_TIMEOUT = 0.5;
    private DcMotor.RunMode prevRunMode;

    private ArmByEncoder(Arm arm, int counts, double power, double timeout){
        timer = new ElapsedTime();

        this.arm = arm;
        this.counts = counts;
        this.power = power;
        this.timeout = timeout;
    }

    private ArmByEncoder(Arm arm, int counts, double power){
        timer = new ElapsedTime();

        this.arm = arm;
        this.counts = counts;
        this.power = power;
        this.timeout = DEFAULT_TIMEOUT;
    }

    @Override
    public void start(){
        timer.reset();

        int currentPos = arm.getPos();
        prevRunMode = arm.getRunMode();

        arm.setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(power);
        arm.setTargetPos(currentPos + counts);
    }

    @Override
    public void periodic(){

    }

    @Override
    public void stop(){
        arm.setRunMode(prevRunMode);
        arm.setPower(0);
    }

    @Override
    public boolean isCompleted(){
        return (!arm.armIsBusy() || timer.seconds() > timeout);
    }

}

package org.firstinspires.ftc.teamcode.robot.commands.auto;

import android.os.Environment;

import com.disnodeteam.dogecommander.Command;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drive;


public class SavePID implements Command {
    private Drive drive;

    private JsonObject json;

    //private static final String BASE_FOLDER_NAME = "FIRST";
    //private Writer fileWriter;

    private float heading;
    private Exception e;

    private ElapsedTime timer;

    public SavePID(Drive drive){
        this.drive = drive;

        try {
            this.json = (JsonObject) new JsonParser().parse(
                    ReadWriteFile.readFile(
                            AppUtil.getInstance().getSettingsFile("SavedHeading.json")
                    )
            );
        } catch (Exception e){
            e.printStackTrace();
            this.json = new JsonObject();
        }

        JsonElement o = this.json.get("heading");
        if(o != null){
            try {
                this.heading = o.getAsFloat();
            } catch (Exception e){
                e.printStackTrace();
                this.heading = 400f;
            }

        } else {
            this.heading = 420f;
        }

        timer = new ElapsedTime();
    }

    @Override
    public void start(){
      timer.reset();
    }

    @Override
    public void periodic(){
        if(this.json.has("heading")){
            this.json.remove("heading");
        }
        this.json.addProperty("heading", drive.heading());

    }

    @Override
    public void stop(){
        ReadWriteFile.writeFile(AppUtil.getInstance().getSettingsFile("SavedHeading.json"), json.toString());
    }

    @Override
    public boolean isCompleted() {
        return timer.seconds() > 5;
    }

    public float getHeading(){
        return heading;
    }

    public void setHeading(float heading){
        this.heading = heading;
    }
}

package org.firstinspires.ftc.teamcode.Core.Input;

import com.arcrobotics.ftclib.command.button.Trigger;

public class EctoButton extends Trigger {
    private boolean state = false;
//    private boolean Trigger = new Trigger();


    public final void update(boolean state) {
        this.state = state;
    }


    public final boolean get(){
        return state;
    }
}

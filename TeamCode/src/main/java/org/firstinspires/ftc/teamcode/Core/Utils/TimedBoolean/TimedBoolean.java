package org.firstinspires.ftc.teamcode.Core.Utils.TimedBoolean;

import org.firstinspires.ftc.teamcode.Core.Utils.Timer.Timer;

public class TimedBoolean {
    private Timer t = new Timer();
    private boolean m_old = false;

    public boolean update(boolean value, double timeout) {
        if (!m_old && value) {
            t.reset();
            t.start();
        }
        m_old = value;
        return value && t.get() >= timeout;
    }
}
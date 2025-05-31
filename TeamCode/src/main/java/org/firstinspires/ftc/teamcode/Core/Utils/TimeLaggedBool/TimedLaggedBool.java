package org.firstinspires.ftc.teamcode.Core.Utils.TimeLaggedBool;

import org.firstinspires.ftc.teamcode.Core.Utils.Timer.Timer;


public class TimedLaggedBool {
    private Timer t = new Timer();
    private boolean m_old = false;

    public TimedLaggedBool() {
        t.reset();
        t.start();
    }

    public boolean update(boolean value, double timeout) {
        if (!m_old && value) {
            t.reset();
            t.start();
        }
        m_old = value;
        return value || t.get() < timeout;
    }
}
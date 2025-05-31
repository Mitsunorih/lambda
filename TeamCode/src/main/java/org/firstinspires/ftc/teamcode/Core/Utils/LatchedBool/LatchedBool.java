package org.firstinspires.ftc.teamcode.Core.Utils.LatchedBool;

public class LatchedBool {
    private boolean mLast = true;

    public boolean update(boolean newValue) {
        boolean ret = newValue && !mLast;
        mLast = newValue;
        return ret;
    }
}
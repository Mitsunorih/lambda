package org.firstinspires.ftc.teamcode.Core.Utils.Timer;

public class Timer {
    private double m_startTime;
    private double m_accumulatedTime;
    private boolean m_running;


    public static void delay(double seconds) {
        try {
            Thread.sleep((long)(seconds * 1000.0));
        } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
        }

    }

    public Timer() {
        this.reset();
    }

    private double getMsClock() {
        return (double)System.nanoTime() / 1000.0;
    }

    public double get() {
        return this.m_running ? this.m_accumulatedTime + (this.getMsClock() - this.m_startTime) / 1000.0 : this.m_accumulatedTime;
    }

    public void reset() {
        this.m_accumulatedTime = 0.0;
        this.m_startTime = this.getMsClock();
    }

    public void start() {
        if (!this.m_running) {
            this.m_startTime = this.getMsClock();
            this.m_running = true;
        }

    }

    public void stop() {
        this.m_accumulatedTime = this.get();
        this.m_running = false;
    }

    public boolean hasElapsed(double seconds) {
        return this.get() >= seconds;
    }

    public boolean advanceIfElapsed(double seconds) {
        if (this.get() >= seconds) {
            this.m_startTime += seconds * 1000.0;
            return true;
        } else {
            return false;
        }
    }
}
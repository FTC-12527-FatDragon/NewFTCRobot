package org.firstinspires.ftc.teamcode.utils;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

// https://www.ctrlaltftc.com/the-pid-controller
@Config
public class PID {
    public double p;
    public double i;
    public double d;
    public PID(double P, double I, double D){
        p=P;
        i=I;
        d=D;
    }
    double integral = 0;
    long lastLoopTime = System.nanoTime();
    double lastError = 0;
    int counter = 0;
    double loopTime = 0.0;

    public void resetIntegral() {
        integral = 0;
    }

    public double update(double error, double min, double max){
        return 0;
        // to be done
    }

    public void updatePID(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }
}
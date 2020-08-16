package com.example.cubigrov.sensor;

import android.hardware.Sensor;
import android.hardware.SensorManager;


/**
 * by hagnji{
 *  sensorData object
 *  getter and setter methods
 *  toString methods
 * }
 */

public class getSensorData {
    private SensorManager senseManage;
    private Sensor ambTempSense;
    private Sensor luxSense;
    private Sensor pressureSense;
    private Sensor humiditySense;

    public SensorManager getSenseManage() {
        return senseManage;
    }

    public void setSenseManage(SensorManager senseManage) {
        this.senseManage = senseManage;
    }

    public Sensor getAmbTempSense() {
        return ambTempSense;
    }

    public void setAmbTempSense(Sensor ambTempSense) {
        this.ambTempSense = ambTempSense;
    }

    public Sensor getLuxSense() {
        return luxSense;
    }

    public void setLuxSense(Sensor luxSense) {
        this.luxSense = luxSense;
    }

    public Sensor getPressureSense() {
        return pressureSense;
    }

    public void setPressureSense(Sensor pressureSense) {
        this.pressureSense = pressureSense;
    }

    public Sensor getHumiditySense() {
        return humiditySense;
    }

    public void setHumiditySense(Sensor humiditySense) {
        this.humiditySense = humiditySense;
    }

    public getSensorData(SensorManager senseManage, Sensor ambTempSense, Sensor luxSense, Sensor pressureSense, Sensor humiditySense) {
        super();
        this.senseManage = senseManage;
        this.ambTempSense = ambTempSense;
        this.luxSense = luxSense;
        this.pressureSense = pressureSense;
        this.humiditySense = humiditySense;

    }

    @Override
    public String toString() {
        return "getSensorData{" +
                "senseManage=" + senseManage +
                ", ambTempSense=" + ambTempSense +
                ", luxSense=" + luxSense +
                ", pressureSense=" + pressureSense +
                ", humiditySense=" + humiditySense +
                '}';
    }
}

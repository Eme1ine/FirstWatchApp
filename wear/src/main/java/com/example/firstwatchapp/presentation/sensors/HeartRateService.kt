// File: sensors/HeartRateService.kt
package com.example.firstwatchapp.presentation.sensors

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.VibrationEffect
import android.os.VibratorManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.health.services.client.HealthServices
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.MeasureClient
import androidx.health.services.client.data.*
import androidx.health.services.client.HealthServicesClient
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import com.example.firstwatchapp.presentation.audio.HeartRateZoneAnnouncer

class HeartRateService(private val activity: ComponentActivity) {
    val heartRate = mutableFloatStateOf(0f)
    val zone = mutableIntStateOf(0)
    private var heartRateZoneAnnouncer = HeartRateZoneAnnouncer(activity)

    private val permissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            measureClient = healthClient.measureClient
        } else {
            Log.d("HeartRate", "Permission not granted")
        }
    }

    private val healthClient: HealthServicesClient = HealthServices.getClient(activity)
    private lateinit var measureClient: MeasureClient

    private val heartRateCallback = object : MeasureCallback {
        override fun onAvailabilityChanged(dataType: DeltaDataType<*, *>, availability: Availability) {
            Log.d("HeartRate", "Availability: ${availability.id}")
            if (availability is DataTypeAvailability) {
                // Handle availability change.
                Log.d("HeartRate", "Availibility changed : ${availability.name}")
            }
        }

        override fun onDataReceived(data: DataPointContainer) {
            val bpm = data.getData(DataType.HEART_RATE_BPM).lastOrNull()?.value?.toFloat()
            if (bpm != null && bpm != 0f) {
                heartRate.floatValue = bpm
                updateZone()
                Log.d("HeartRate", "Received BPM: $bpm")
            } else {
                Log.d("HeartRate", "Data is null or 0")
            }
        }
    }

    fun checkAndRequestPermission() {
        if (activity.checkSelfPermission(Manifest.permission.BODY_SENSORS) == PackageManager.PERMISSION_GRANTED) {
            measureClient = healthClient.measureClient
        } else {
            permissionLauncher.launch(Manifest.permission.BODY_SENSORS)
        }
    }

    fun start() {
        if (::measureClient.isInitialized) {
            measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, heartRateCallback)
        }
    }

    fun stop() {
        if (::measureClient.isInitialized) {
            measureClient.unregisterMeasureCallbackAsync(DataType.HEART_RATE_BPM, heartRateCallback)
        }
    }

    fun updateZone(){
        val oldValue = zone.intValue
        if (heartRate.floatValue < 60) {
            zone.intValue = 1
        }
        else if (heartRate.floatValue < 65) {
            zone.intValue = 2
        }
        else {
            zone.intValue = 3
        }

        if ( oldValue != zone.intValue){
            val vibratorManager = activity.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorManager.defaultVibrator
            val effect = VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
            heartRateZoneAnnouncer.speakZone(zone.intValue)
        }
    }
}
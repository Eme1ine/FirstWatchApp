// File: sensors/HeartRateService.kt
package com.example.firstwatchapp.presentation.sensors

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.health.services.client.HealthServices
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.MeasureClient
import androidx.health.services.client.data.*
import androidx.health.services.client.HealthServicesClient
import androidx.compose.runtime.mutableFloatStateOf

class HeartRateService(private val activity: ComponentActivity) {
    val heartRate = mutableFloatStateOf(0f)

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
            Log.d("HeartRate", "Availability: ${availability.javaClass.simpleName}")
        }

        override fun onDataReceived(data: DataPointContainer) {
            val bpm = data.getData(DataType.HEART_RATE_BPM).lastOrNull()?.value?.toFloat()
            if (bpm != null && bpm != 0f) {
                heartRate.floatValue = bpm
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
}
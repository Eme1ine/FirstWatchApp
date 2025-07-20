package com.example.firstwatchapp.presentation.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class HeartRateZoneAnnouncer (context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech = TextToSpeech(context.applicationContext, this)
    private var isReady = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.FRANCE
            isReady = true
        }
    }

    fun speakZone(zone: Int) {
        if (isReady) {
            Log.d("HeartRateZoneAnnouncer", "Changement de zone : $zone")
            tts.speak("Zone $zone", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }


    fun shutdown() {
        tts.stop()
        tts.shutdown()
    }
}
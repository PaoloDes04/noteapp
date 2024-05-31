/*
package com.example.appnotes

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appnotes.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity() , TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fileHandler: FileHandler
    private lateinit var tts : TextToSpeech

    companion object {
        private const val PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1
        private const val PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init file handler
        fileHandler = FileHandler(this@MainActivity)
        checkWriteExternalStoragePermission()
        checkReadExternalStoragePermission()








        with(binding) {
            btnspeak.setOnClickListener {
                if (etYourText.text.toString().isNotEmpty()) {
                    fileHandler.writeText(etYourText.text.toString())
                    Toast.makeText(this@MainActivity, R.string.data_saved, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.please_enter_text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            btnViewSavedText.setOnClickListener {
                val data = fileHandler.readText()
                if (data.isEmpty()) {
                    Toast.makeText(this@MainActivity, R.string.no_data, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Testo Salvato: $data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        //initViews
        binding.apply {
            tts = TextToSpeech(this@MainActivity,this@MainActivity)
            btnspeak.setOnClickListener {
                val text = etYourText.text.toString()
                if (text.isEmpty()) Toast.makeText(this@MainActivity , "testo non presente" , Toast.LENGTH_SHORT).show()
                else speak(text)
            }
        }
    }

    private fun checkWriteExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE
            )
        } else {
            Toast.makeText(this, R.string.permission_already_granted, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_READ_EXTERNAL_STORAGE
            )
        } else {
            Toast.makeText(this, R.string.permission_already_granted, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE, PERMISSION_REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    finish()
                    Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onInit(status : Int)
    {
        if (status == TextToSpeech.SUCCESS)
        {
            val result = tts.setLanguage(Locale.ITALY)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                //Handle language not supported
                Toast.makeText(this@MainActivity , "Lingua non supportata" , Toast.LENGTH_SHORT).show()
            }else{
                binding.btnspeak.isEnabled = true
                tts.setSpeechRate(0.5f)//set the speech rate to 0.5
            }
        }else
        {
            //Handle TTS initialization failed
            Toast.makeText(this@MainActivity , "errore riprova" , Toast.LENGTH_SHORT).show()
        }
    }

    private fun speak(text:String)
    {
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy()
    {
        if(tts.isSpeaking)
            tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}

*/


package com.example.appnotes

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appnotes.databinding.ActivityMainBinding
import java.util.Locale


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fileHandler: FileHandler
    private lateinit var tts: TextToSpeech

    companion object {
        private const val PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1
        private const val PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //quando si clicca il pulsante della matita si va sul fragment paint
        val buttonPaint: ImageButton = findViewById(R.id.imageButton2)
        buttonPaint.setOnClickListener {

            val intent = Intent(this@MainActivity, FragmentPaint::class.java)
            startActivity(intent)
        }

                // Initialize file handler
        fileHandler = FileHandler(this@MainActivity)

        // Check permissions
        checkWriteExternalStoragePermission()
        checkReadExternalStoragePermission()

        // Initialize Text-to-Speech
        tts = TextToSpeech(this@MainActivity, this@MainActivity)

        with(binding) {
            btnSave.setOnClickListener {
                if (etYourText.text.toString().isNotEmpty()) {
                    fileHandler.writeText(etYourText.text.toString())
                    Toast.makeText(this@MainActivity, R.string.data_saved, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        R.string.please_enter_text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            btnViewSavedText.setOnClickListener {
                val data = fileHandler.readText()
                if (data.isEmpty()) {
                    Toast.makeText(this@MainActivity, R.string.no_data, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Testo Salvato: $data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            btnspeak.setOnClickListener {
                val text = etYourText.text.toString()
                if (text.isEmpty()) {
                    Toast.makeText(this@MainActivity, "testo non presente", Toast.LENGTH_SHORT).show()
                } else {
                    speak(text)
                }
            }
        }
    }

    private fun checkWriteExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE
            )
        } else {
            Toast.makeText(this, R.string.permission_already_granted, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_READ_EXTERNAL_STORAGE
            )
        } else {
            Toast.makeText(this, R.string.permission_already_granted, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE, PERMISSION_REQUEST_READ_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    finish()
                    Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.ITALY)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this@MainActivity, "Lingua non supportata", Toast.LENGTH_SHORT).show()
            } else {
                binding.btnspeak.isEnabled = true
                tts.setSpeechRate(0.8f) // Set the speech rate to 0.8
            }
        } else {
            Toast.makeText(this@MainActivity, "errore riprova", Toast.LENGTH_SHORT).show()
        }
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts.isSpeaking)
            tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}

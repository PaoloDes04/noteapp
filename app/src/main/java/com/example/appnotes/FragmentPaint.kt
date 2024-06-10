package com.example.appnotes


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appnotes.databinding.FragmentpaintBinding


class FragmentPaint : AppCompatActivity() {

    private lateinit var binding: FragmentpaintBinding
    private lateinit var canvasView: CanvasView
//legame con xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragmentpaint)
        binding = FragmentpaintBinding.inflate(layoutInflater)

        canvasView = binding.canvasView

        binding.apply {
            colorButton.setOnClickListener {
                canvasView.setColor()
            }
            clearButton.setOnClickListener {
                canvasView.clearCanvas()
            }
        }

            }
        }
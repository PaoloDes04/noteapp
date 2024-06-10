
package com.example.appnotes

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream

class FileHandler(private val context: Context) {

    private val fileName = "saved_text.txt"

    fun writeText(text: String) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(text.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun readText(): String {
        val fileInputStream: FileInputStream
        try {
            fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = fileInputStream.reader()
            val bufferedReader = inputStreamReader.buffered()
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine { stringBuilder.append(it) }
            return stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
        }
}









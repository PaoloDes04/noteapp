/*
package com.example.appnotes

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class FileHandler(private val context: Context) {

    private val directory: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

    init {
        if (!directory.exists()) {
            directory.mkdirs()
        }
    }

    fun writeText(data: String) {
        val file = File(directory, "saved_text.txt")
        try {
            FileOutputStream(file).use { fos ->
                fos.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readText(): String {
        val file = File(directory, "saved_text.txt")
        return try {
            FileInputStream(file).use { fis ->
                fis.readBytes().toString(Charsets.UTF_8)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }
}
*/

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









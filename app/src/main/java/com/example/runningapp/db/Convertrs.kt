package com.example.runningapp.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.io.OutputStream

class Convertrs {

    @TypeConverter
    fun toBitmap(bytes:ByteArray):Bitmap =
        BitmapFactory.decodeByteArray(bytes,0,bytes.size)

    @TypeConverter
    fun fromBitmap(bitmap:Bitmap):ByteArray{
        val outputstream= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputstream)
        return outputstream.toByteArray()
    }


}
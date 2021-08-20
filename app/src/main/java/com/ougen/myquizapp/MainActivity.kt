package com.ougen.myquizapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.ads.mediationtestsuite.dataobjects.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import com.google.android.gms.ads.MobileAds
import java.io.InputStream
import java.lang.Exception
import java.util.concurrent.ExecutionException



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}
        var tvJsonString=findViewById<TextView>(R.id.question)

        tvJsonString.text=getSorular(this)
        getSorular(this)?.let { Log.d("MainActivity", it) }

    }

    private fun getSorular(context: Context): String? {
        var input:InputStream?=null
        var jsonString:String

        try{
            //Create InputStream
            input =context.assets.open("ingilizcekelimeler.json")

            val size=input.available()
            //Create a buffer with the size
            val buffer=ByteArray(size)

            //Read data from InputSteam into the Buffer
            input.read(buffer)

            //Create a json String

            jsonString= String(buffer)
            return jsonString
        }
        catch (ex:Exception){
            ex.printStackTrace()
        }finally {
            //Must close the stream
            input?.close()

        }


        return null

    }




    /*
    fun getSorular(context: Context): List<Sorular> {
        val jsonFileString = getJsonDataFromAsset(context, "ingilizcekelimeler.json")
        var sorular: List<Sorular> = listOf()
        try {
            val gson = Gson()
            val listCountryType = object : TypeToken<List<Country>>() {}.type
            sorular = gson.fromJson(jsonFileString, listCountryType)
        } catch (exception: Exception) {
            exception.printStackTrace()
            return sorular
        }
        return sorular
    }
*/






}
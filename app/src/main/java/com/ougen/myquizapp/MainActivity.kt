package com.ougen.myquizapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.ads.mediationtestsuite.dataobjects.Country
import com.google.gson.Gson
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import com.google.gson.reflect.TypeToken
import java.io.IOException
import com.google.android.gms.ads.MobileAds
import java.lang.Exception
import java.util.concurrent.ExecutionException



class MainActivity : AppCompatActivity() {

    private var questionsList: ArrayList<Question> = ArrayList()
    // store player score
    private var playerScore = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}

        val tvJsonString=findViewById<TextView>(R.id.question)
        tvJsonString.text=getSorular(this)


        //Jsonu Gson ile parse etmek
        val quest=Gson().fromJson(getSorular(this),ListQuestion::class.java)
        Log.d("MainActivity","Size:${quest.questions.size}")


        var questionArraySize = 0
        // get all question objects of selected category from json file and store in questionsList
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val questionArray = obj.getJSONArray(.)
            questionArraySize = questionArray.length()
            for (i in 0 until questionArray.length()) {
                val questionJSONObject = questionArray.getJSONObject(i)
                val question = Question(
                    q = questionJSONObject.getString("question"),
                    opt1 = questionJSONObject.getString("opt1"),
                    opt2 = questionJSONObject.getString("opt2"),
                    opt3 = questionJSONObject.getString("opt3"),
                    answer = questionJSONObject.getInt("answer")
                )
                questionsList.add(question)
            }
        } catch (ex: JSONException) {
            ex.printStackTrace()
        }



    }





    //Create Model Depends on json


      private fun getSorular(context: Context): String? {
        var input:InputStream?=null
        val jsonString:String

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
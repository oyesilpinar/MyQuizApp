package com.ougen.myquizapp

import com.google.gson.annotations.SerializedName

class Sorular {

    @SerializedName(value = "q")
    var q:String=""

    @SerializedName(value = "opt1")
    var opt1:String=""
    @SerializedName(value = "opt2")
    var opt2:String=""
    @SerializedName(value = "opt3")
    var opt3:String=""
    @SerializedName(value = "answer")
    var answer:String=""



}

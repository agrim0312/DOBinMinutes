package com.example.dobinminutes

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import java.util.*


class MainActivity : AppCompatActivity() {
    private var textViewSelected :TextView? = null
    private var textViewDisplay : TextView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSelected = findViewById(R.id.tvselectedDate)
        textViewDisplay = findViewById(R.id.tvdisplay)

        val imageButton: ImageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener {
            openCalender()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun openCalender(){
        val mycalender = Calendar.getInstance()
        val year:Int = mycalender.get(Calendar.YEAR)
        val month:Int = mycalender.get(Calendar.MONTH)
        val day_of_month:Int = mycalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,{_,selectedYear,selectedMonth,selectedDay->
            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
            textViewSelected?.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val preview = sdf.parse(selectedDate)

//            textViewDisplay?.setText(preview.toString())

            val selectedDateInMin = preview.time/60000

            val standardDateInMin = sdf.parse(sdf.format(System.currentTimeMillis()))

            val standardDateInMinNew = standardDateInMin.time / 60000

            val difference = standardDateInMinNew - selectedDateInMin

            textViewDisplay?.setText(difference.toString())

        },year,month,day_of_month)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}
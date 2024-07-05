package com.example.doccal

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var selectedDate:TextView? = null
    private var dateInMin:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val datePickerBtn :Button = findViewById(R.id.DatePickerbtn)
         selectedDate =  findViewById(R.id.txtDate)
        dateInMin = findViewById(R.id.txtMin)

        datePickerBtn.setOnClickListener {
            datepick()

        }



    }

    private fun datepick() {
       val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedyear, selectedmonth, selecteddayofMonth ->
                Toast.makeText(this,"$selecteddayofMonth/${selectedmonth+1}/$selectedyear",Toast.LENGTH_LONG).show()

                val selectedDatetxt = "$selecteddayofMonth/${selectedmonth+1}/$selectedyear"

                selectedDate?.text = selectedDatetxt

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDatetxt )

                theDate?.let {
                    val selectedDateInMin = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMin = currentDate.time / 60000

                        val diffInMinutes = currentDateInMin - selectedDateInMin
                        dateInMin?.text = diffInMinutes.toString()
                    }
                }


            },
            year,
            month,
            day

        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}
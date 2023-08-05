package com.example.datepicker

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.datepicker.ui.theme.DatePickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DatePickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DatePickerEx()
                }
            }
        }
    }
}

@Composable
fun DatePickerEx() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateState = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        {
            _, year, month, dayOfMonth ->
            dateState.value = "${year}년 ${month+1}월 ${dayOfMonth}일"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    Column {
        Button(onClick = { datePickerDialog.show() }) {
            Text(text = "open date picker")
        }
        Text(text = dateState.value)
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DatePickerTheme {
        DatePickerEx()
    }
}
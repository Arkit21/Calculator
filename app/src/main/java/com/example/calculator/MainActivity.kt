package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CalculatorAppTopBar()
                    },
                    bottomBar = {
                        CalculatorAppBottomBar()
                    }) { innerPadding ->
                    CalculatorApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            Spacer(Modifier.height(32.dp))
            Surface(
                modifier = modifier.padding(16.dp)
            ) {
                val gradientColors = listOf(Cyan, Magenta, Purple40 /*...*/)
                TextField(
                    value = "Let's Calculate", onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(82.dp),
                    readOnly = true,
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors,
                        ),
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp
                    )
                )
            }
            Spacer(Modifier.height(64.dp))
            Column {
                ButtonRow("7", "8", "9", "+")
                ButtonRow("4", "5", "6", "-")
                ButtonRow("1", "2", "3", "*")
                ButtonRow("0", ".", "C", "/")
            }
        }
    }
}

@Composable
fun ButtonRow(
              value1: String,
              value2: String,
              value3: String,
              value4: String
) {
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(48.dp),
            modifier = Modifier.padding(6.dp)
                .size(86.dp)) {
            Text(text = value1,
                fontSize = 60.sp)
        }
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(48.dp),
            modifier = Modifier.padding(6.dp)
                .size(86.dp)) {
            Text(text = value2,
                fontSize = 60.sp,
                textAlign = TextAlign.Center
                )
        }
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(48.dp),
            modifier = Modifier.padding(6.dp)
                .size(86.dp)) {
            Text(text = value3,
                fontSize = 60.sp)
        }
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(48.dp),
            modifier = Modifier.padding(6.dp)
                .size(86.dp)) {
            Text(text = value4,
                fontSize = 60.sp)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorAppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(text = "My Calculator App") },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun CalculatorAppBottomBar(modifier: Modifier = Modifier) {
    BottomAppBar {
        Text(
            text = "A dummy Project by Arkitx11",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CalculatorAppPreview() {
    CalculatorTheme {
        CalculatorApp()
    }
}
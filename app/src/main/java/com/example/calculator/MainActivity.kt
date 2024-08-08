package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacer_height_small)))
            Surface(
                modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                val gradientColors = listOf(Cyan, Magenta, Purple40 /*...*/)
                TextField(
                    shape = MaterialTheme.shapes.extraLarge,
                    value = stringResource(R.string.text_field_value), onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.text_field_height)),
                    readOnly = true,
                    singleLine = true,
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
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacer_height_medium)))
            Column {
                ButtonRow(
                    R.string.seventh_button,
                    R.string.eight_button,
                    R.string.ninth_button,
                    R.string.addition_button
                )
                ButtonRow(
                    R.string.fourth_button,
                    R.string.fifth_button,
                    R.string.sixth_button,
                    R.string.subtraction_button
                )
                ButtonRow(
                    R.string.first_button,
                    R.string.second_button,
                    R.string.third_button,
                    R.string.multiplication_button
                )
                ButtonRow(
                    R.string.zeroth_button,
                    R.string.period_button,
                    R.string.clear_button,
                    R.string.division_button
                )
            }
        }
    }
}

@Composable
fun ButtonRow(
    @StringRes value1: Int,
    @StringRes value2: Int,
    @StringRes value3: Int,
    @StringRes value4: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        AppButton(text = value1)
        AppButton(text = value2)
        AppButton(text = value3)
        AppButton(text = value4)
    }

}

@Composable
fun AppButton(
    @StringRes text: Int // Avoided modifier as it would have to be passed repeatedly as an argument during each function invocation
) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .size(dimensionResource(id = R.dimen.button_size))
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 60.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorAppTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
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
            text = stringResource(R.string.app_description),
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
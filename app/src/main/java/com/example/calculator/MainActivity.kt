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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.calculator.model.operandsList
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.Purple40
import com.gloorystudio.fook.calc.Fook

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CalculatorAppTopBar()
                    },
                ) { innerPadding ->
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
    var inputCounter by remember {
        mutableIntStateOf(0)
    }
    var input by remember {
        mutableStateOf("")
    }
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
                EditTextField(
                    value = input,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.text_field_height)),
                    inputCounter = inputCounter
                )
            }
            Spacer(modifier = Modifier.weight(3f))
            Column {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppButton(R.string.addition_button) {
                        input += '+'; inputCounter += 1
                    }
                    AppButton(R.string.subtraction_button) {
                        input += '-'; inputCounter += 1
                    }
                    AppButton(R.string.multiplication_button) {
                        input += '*'; inputCounter += 1
                    }
                    AppButton(R.string.division_button) {
                        input += '/'; inputCounter += 1
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppButton(R.string.eight_button) {
                        input += '8'; inputCounter += 1
                    }
                    AppButton(R.string.seventh_button) {
                        input += '7'; inputCounter += 1
                    }
                    AppButton(R.string.ninth_button) {
                        input += '9'; inputCounter += 1
                    }
                    AppButton(R.string.opening_bracket_button) {
//                        input += '('; inputCounter += 1
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppButton(R.string.fourth_button) {
                        input += '4'; inputCounter += 1
                    }
                    AppButton(R.string.fifth_button) {
                        input += '5'; inputCounter += 1
                    }
                    AppButton(R.string.sixth_button) {
                        input += '6'; inputCounter += 1
                    }
                    AppButton(R.string.closing_bracket_button) {
//                        input += ')'; inputCounter += 1
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppButton(R.string.first_button) {
                        input += '1'; inputCounter += 1
                    }
                    AppButton(R.string.second_button) {
                        input += '2'; inputCounter += 1
                    }
                    AppButton(R.string.third_button) {
                        input += '3'; inputCounter += 1
                    }
                    AppButton(R.string.modulus_button) {
                        input += '%'; inputCounter += 1
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AppButton(R.string.zeroth_button) {
                        input += '0'; inputCounter += 1
                    }
                    AppButton(R.string.period_button) {
                        input += '.'; inputCounter += 1
                    }
                    AppButton(R.string.equals_button) {
                        input = calculate(input); inputCounter = -1
                    }
                    AppButton(R.string.clear_button) { input = ""; inputCounter = 0 }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun AppButton(
    @StringRes text: Int, // Avoided modifier as it would have to be passed repeatedly as an argument during each function invocation
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .size(dimensionResource(id = R.dimen.button_size))
    ) {
        Text(
            text = stringResource(id = text),
            fontSize = 60.sp,
        )
    }
}

@Composable
fun EditTextField(
    value: String,
    inputCounter: Int,
    modifier: Modifier = Modifier
) {
    var display = value
    val gradientColors = listOf(Cyan, Magenta, Purple40 /*...*/)
    if (inputCounter == 0)
        display = ""
    TextField(
        shape = MaterialTheme.shapes.extraLarge,
        value = if (inputCounter == 0)
            stringResource(id = R.string.text_field_value) else
            display, onValueChange = {},
        modifier = modifier,
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

fun calculate(
    input: String
): String {
//    var value = safetyCheck(input, char)
    return Fook.calc(input).toString()
}

fun safetyCheck(input: String, char: Char): String {
    var returnValue = ""
    returnValue = if (char in operandsList && input.last() in operandsList)
        input.dropLast(1) + char
    else
        input + char
    return returnValue
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
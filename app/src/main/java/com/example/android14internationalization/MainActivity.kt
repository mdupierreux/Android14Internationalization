package com.example.android14internationalization

import android.app.LocaleConfig
import android.app.LocaleManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.LocaleList
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.android14internationalization.ui.theme.Android14InternationalizationTheme
import java.util.Locale


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            Android14InternationalizationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChangeLanguageScreen(
                        Modifier.padding(16.dp)
                    ) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        ContextCompat.startActivity(context, intent, null)
                    }
                }
            }
        }
    }
}

@Composable
fun ChangeLanguageScreen(
    modifier: Modifier = Modifier,
    onGoToSettingsClicked: () -> Unit
) {
    val localeManager = getSystemService(LocalContext.current, LocaleManager::class.java)
    val localeConfig = LocaleConfig(LocaleList(Locale.FRENCH, Locale.GERMAN, Locale.ENGLISH))
    Column(
        modifier,
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.hello),
        )
        Button(
            onClick = { localeManager?.overrideLocaleConfig = localeConfig }
        ) {
            Text(text = stringResource(R.string.load_languages))
        }
        Button(
            onClick = { onGoToSettingsClicked() }
        ) {
            Text(text = stringResource(R.string.go_to_settings))
        }
    }
}
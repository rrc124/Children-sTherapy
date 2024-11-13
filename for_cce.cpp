package com.example.childrenstherapy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri

/*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.childrenstherapy.ui.theme.ChildrensTherapyTheme
*/

/*
Similar to int main() entry point in C++, but this is specific to Android app development.
"class MainActivity" is declared in the AndroidManifest.xml file.
Bundle, imported above, allows the app to save the activity and keep your state in the
program. With bundle, if your app is moved to the background, it will return to the state
you left it.  The syntax class_name : other_class_name signifies MainActivity inherits all
methods from the other class, ComponentActivity.  Scaffold, a Jetpack Compose function,
provides UI layouts (such as having a title on top, a button in the bottom right, and a
bottom bar) and standard spacing.
https://www.geeksforgeeks.org/scaffold-in-android-using-jetpack-compose/?ref=header_outind
Placing the Scaffold function under setContent ensures it takes up the entire available
space.
 */

/*
Syntax: class MainActivity "of" (:) AppCompatActivity()
AppCompatActivity() supports modern themes while offering compatibility to older devices.
This is set when you choose backwards compatibility upon setting up the project.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // When we override a function, we can use super.method to call the superclass' version of the method
        super.onCreate(savedInstanceState)

        // To see what this is doing, go to activity_main.xml
        setContentView(R.layout.activity_main)

        // Sets up click listener for the button
        // button_open_webpage is an ID used in activity_main.xml, which is expected to be assigned to a <button>.
        // Dot notation used to call the .setOnClickListener method.
        findViewById<Button>(R.id.button_open_webpage).setOnClickListener {
            val webpageUrl = "https://www.google.com"
            openWebPage(webpageUrl)
            // Log.d sends debugging information to Logcat so we can view what's going on.
            // Other methods in Log.<method> are: Log.v (low level info); Log.i (general info); Log.w (warnings); Log.e (error messages)
            Log.d("MainActivity", "Open Webpage button clicked")
        }
    }
    // Function takes a URL to op0en a webpage.
    private fun openWebPage(url: String) {
        // parses the URL and converts it to Uri, which is needed for viewing a webpage
        val webpage: Uri = Uri.parse(url)

        // The reason our program is not pulling up the webpage probably is linked to this line of code.
        // This requests an app capable of viewing a webpage to do that job.  We may need to go back and specify a default web browser.
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        // Add logging to check if the intent resolves successfully
        // Throws a error that no webpage handling app is available
        val chooser = Intent.createChooser(intent, "Select a browser")
        if (intent.resolveActivity(packageManager) != null) {
            Log.v("MainActivity", "Intent resolved successfully.")
            startActivity(chooser)
        } else {
            Log.d("MainActivity", "No application can handle this intent.")
        }
    }
}


        /*
        // Sets up click listeners for action buttons
        findViewById<Button>(R.id.button2).setOnClickListener {
            launchVideoPlayer("android.resource://com.example.childrenstherapy/raw/video2")
        }


        ChildrensTherapyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // Scaffold
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)

         */

/*
A composable is a "nestable" function from Jetpack that allows you to create complex
UI structures. So "@Composable" tells the function "Greeting" to use the Jetpack
Compose UI framework.
 */

    /*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var helloEmoji by remember { // var is mutable variable, vs val which is not.
        mutableStateOf(false)
    }
    /*
    Function "Column" arranges items vertically.  The function "Button" is placed within this column
    that shows "Hi, my name is ...".  When this button is clicked, the state of "helloEmoji" is set to
    true and a GIF emoji is displayed.
     */
    Column( // Function imported from import androidx.compose.foundation.layout.
        // Column gives Greeting a vertical layout.
        horizontalAlignment  = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp) // Modifier function contains decorations and
        // behaviors to function Column.
        // https://www.geeksforgeeks.org/concept-of-padding-in-android/?ref=header_outind

    )
    /*
    Button is a Composable function that creates a clickable button UI.
    This function provides a string of text (Hi, my name is $name!"
    */
    {
        Button(
            onClick = { helloEmoji = true }, // Sets a boolean state to var "helloEmoji".
            // If Button is clicked, set "helloEmoji" to true.
            modifier = Modifier.padding(24.dp)
        ) {
            Text(text = "Hi, my name is $name!") // Text that shows inside the button.
        }

        // If "helloEmoji" is true, then display the GIF.
        if (helloEmoji) {
            Image(
                painter = rememberAsyncImagePainter(model = R.raw.hello_emoji),
                contentDescription = "Hello Emoji GIF",
                modifier = Modifier.size(150.dp)
            )
        }
    }
}
/*
@Preview allows us to preview the app inside of the Android Studio environment.
 */
@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    ChildrensTherapyTheme {
        Greeting("Android")
    }
}

*/
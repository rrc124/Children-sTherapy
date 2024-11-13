package com.example.childrenstherapy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri


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

@Composable
fun FolderListScreen(modifier: Modifier = Modifier) {
    val folders = listOf(
        /*
        [FIXME]: buttonGrid1 and 2 are incorrect, currently using an old version without finishedd buttons, these are placeholders
         */
        Folder("Folder 1", buttonGrid1),
        Folder("Folder 2", buttonGrid2)
/*
More folders can be added as needed
 */
    )

    LazyColumn(modifier = modifier) {
        items(folders) { folder ->
            FolderItem(folder = folder) {
                openButtonGrid(folder)
            }
        }
    }
}


@Composable
fun FolderItem(folder: Folder, onClick: () -> Unit) {
    Text(
        text = folder.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    )
}

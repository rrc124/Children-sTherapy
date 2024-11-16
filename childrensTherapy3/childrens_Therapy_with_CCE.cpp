package com.example.childrenstherapy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.widget.VideoView
import android.widget.MediaController



// # General Overview of Kotlin and Childrens Therapy App <br>
/* <br>  



## Kotlin
***class MainActivity "of" (:) AppCompatActivity()***
<br><br>
Similar to int main() entry point in C++, class MainActivity() is the entry point of the program. 
<br>*AppCompatActivity()* provides backwards compatibility for android devices. By setting *MainActivity()*<br>
as a subclass of *AppCompatActivity()*, we ensure that our main function inherits all of the backwards<br>
compatibility and adaptability (such as toolbars and themes) for older devices.

*lateinit* is a kotlin keyword that alows us to declare variables that we will use later without having to<br> 
set them to null.<br><br>
***lateinit var videoView: VideoView*** tells Kotlin that *videoView* will be initialized later.

## Children's Therapy App <br>
The Children's Therapy App currently includes two main functions: opening a webpage and playing a from <br>
the internal file system of an Android device. The *MainActivity* class is the entry point of the app. The app<br>
uses 3x3 button grid to control actions such as playing videos or showing images.
 */


/*
The hyperlinked website was used to walkthrough creating a video link. ChatGPT was used for debugging and <br>
helping to close some loose ends and used chatgpt for debugging and helping to close some loose ends.<br><br>
[Link](https://www.geeksforgeeks.org/videoview-in-android/)
<br><br>
 */
class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {

        // When we override a function, we can use super.method to call the superclass' version of the method
        super.onCreate(savedInstanceState)

        // To see what this is doing, go to activity_main.xml
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.idVideoView)

        // Sets up click listener for the button<br>
        // button_open_webpage is an ID used in activity_main.xml, which is expected to be assigned to a <button>.<br>
        // Dot notation used to call the *setOnClickListener* method.<br>

        findViewById<Button>(R.id.button_open_webpage).setOnClickListener {
            val webpageUrl = "https://www.google.com"
            openWebPage(webpageUrl)
            // Log.d sends debugging information to Logcat so we can view what's going on.
            // Other methods in Log.<method> are: Log.v (low level info); Log.i (general info); Log.w (warnings); Log.e (error messages)
            Log.d("MainActivity", "Open Webpage button clicked")
        }

        findViewById<Button>(R.id.monkey_video).setOnClickListener {
            // Log.d sends debugging information to Logcat so we can view what's going on.
            // Other methods in Log.<method> are: Log.v (low level info); Log.i (general info); Log.w (warnings); Log.e (error messages)
            Log.v("MainActivity", "Monkey Button clicked")

            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)

            videoView.setMediaController(mediaController)

            // "android.resource" is how we tell the program to look into the Android's file system.

            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.monkey_video)
            videoView.setVideoURI(uri)
            videoView.start()
        }

    }

    // Function takes a URL to op0en a webpage.
    private fun openWebPage(url: String) {
        // parses the URL and converts it to Uri, which is needed for viewing a webpage
        val webpage: Uri = Uri.parse(url)

        // The reason our program is not pulling up the webpage probably is linked to this line of code.
        // This requests an app capable of viewing a webpage to do that job.  We may need to go back and specify a default web browser.
        val intent = Intent(Intent.ACTION_VIEW, webpage)

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
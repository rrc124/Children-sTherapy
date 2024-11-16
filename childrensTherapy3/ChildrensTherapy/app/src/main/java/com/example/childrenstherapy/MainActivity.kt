package com.example.childrenstherapy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.widget.ImageView
import android.widget.VideoView
import android.widget.MediaController
/*
# Children's Therapy App and General Overview of Kotlin  <br>
 <br>

# Children's Therapy App <br>
The ***MainActivity*** class is the entry point of the app. The app uses *3x3* grid of buttons 
to control actions such as playing videos or showing images.  This class interacts with *activity_main.xml*
to set the layout on the user's screen and the behavior of the app. Each button in the 3x3 grid performs a 
specific action such as opening a webpage or playing a video.<br><br>
The Children's Therapy App currently includes three main functions: 
1. **Opening a webpage** <br><br>
Button 1 opens a web browser and begins playing a video linked in variable ***webpageUrl*** 

2. **Play Video From Directory** <br><br>
Button 2 accesses a video file from the internal file directory of an Android device. 

3. **Show Image From Directory** <br><br>
Button 3 accesses a gif image file from the internal file directory of an Android device. 
This image cannot yet play the gif, but shows as a still image only.
<br>
<br>
## Kotlin
1. ***class MainActivity "of" (:) AppCompatActivity()***
<br><br>
Similar to int main() entry point in C++, class MainActivity() is the entry point of the program.
<br>***AppCompatActivity()*** provides backwards compatibility for android devices. By setting ***MainActivity()*** 
as a subclass of ***AppCompatActivity()***, we ensure that our main function inherits all of the backwards
compatibility and adaptability (such as toolbars and themes) for older devices.

2. ***private lateinit var videoView: VideoView***<br><br>
*lateinit* is a kotlin keyword that alows us to declare variables that we will use later without having to<br>
set them to null.<br>

3. ***super.onCreate(savedInstanceState)***<br><br>
When we override a function, we can use super to call the superclass' version of the method being c alled. 
The superclass in this case is AppCompatActivity()<br>

4. ***OnClickListener*** "listens" for button presses.<br><br>
*findViewById<Button>(R.id.button_open_webpage).setOnClickListener* finds the button in *activity_main.xml* with the
id: button_open_webpage, then completes the actions between {} after the button is pressed.  <br>

5. ***CatLog for Debugging***<br><br>
Use Log with tag messages to keep track of the program processes in real time as you run the program.  This aids
in understanding if actions such as button presses were successful.<br>
Log methods are: *Log.b* (debugging); *Log.v* (shows everything, including low level actions); *Log.i* (general info); 
*Log.w* (warnings); *Log.e* (error messages)<br>

6. ***Interact with the App's File System***<br><br>
*android.resource*android.resource* is how we tell the program to look into the Android's file system for items 
such as videos or images.  By using the *Uri.parse("android.resource://" + packageName + "/" + R.raw.monkey_video)*, 
the app will find *monkey_video* in the *res/raw* folder, the path that provides access to resources within the app.<br><br> 
*/

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // videoView was already declared with lateinit, so no var/val needed here
        videoView = findViewById(R.id.idVideoView)

        // Sets up click listener for the button
        findViewById<Button>(R.id.button_open_webpage).setOnClickListener {
            val webpageUrl = "https://youtu.be/h2vxQ8Tewok?si=ZVWz5w_L_vrn1wuN"
            openWebPage(webpageUrl)
            // Track actions while the app runs for debugging.
            Log.d("MainActivity", "Open Webpage button clicked")
        }

        findViewById<Button>(R.id.monkey_video).setOnClickListener {

            Log.v("MainActivity", "Monkey Button clicked")

            // Provides video player controls such as pause and play.
            val mediaController = MediaController(this)
            // Overlays video player controls onto videoView so the controls appear at the bottom of the video
            mediaController.setAnchorView(videoView)

            videoView.setMediaController(mediaController)

            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.monkey_video)
            // Tells videoView to play the video assigned to val uri (monkey_video).
            videoView.setVideoURI(uri)
            videoView.start()
        }
        findViewById<Button>(R.id.toad).setOnClickListener {
            Log.v("MainActivity", "Toad Button clicked")

            val toadImage = findViewById<ImageView>(R.id.toadView)
            toadImage.setImageResource(R.drawable.toad)
        }
    }

    private fun openWebPage(url: String) {
        // parses the URL and converts it to Uri, which is needed for viewing a webpage.
        val webpage: Uri = Uri.parse(url)

        // Requests an app capable of viewing a webpage.
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        val chooser = Intent.createChooser(intent, "Select a browser")
        if (chooser.resolveActivity(this.packageManager) != null) { // Explicitly use `this.packageManager`
            Log.v("MainActivity", "Intent resolved successfully.")
            startActivity(chooser)
        } else {
            Log.d("MainActivity", "No application can handle this intent.")
        }
    }
}
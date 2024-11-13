/* #### This is the code for the button and the ability to press it to get a reaction

   These are the nessasary imports, described in detail below */

package com.example.childrenstherapy
/* Bundle, imported below, allows the app to save the activity and keep your
   state in the program. With bundle, if your app is moved to the background, it
   will return to the state you left it. */
import android.os.Bundle

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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/* Similar to int main() entry point in C++, but this is specific to Android app
   development."

   Class: Main

   Activity<span style="caret-color: initial;">MainActivity" is declared in the
   AndroidManifest.xml file.</span>

   The syntax class_name: other_class_name signifies MainActivity inherits all
   methods from the other class, ComponentActivity.Scaffold, a Jetpack Compose
   function, provides UI layouts (such as having a title on top, a button in the
   bottom right, and a bottom bar) and standard spacing.
   ([More Info](https://www.geeksforgeeks.org/scaffold-in-android-using-jetpack-compose/?ref=header_outind))
   Placing the Scaffold function under setContent ensures it takes up the entire
   available space. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ChildrensTherapyTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // Scaffold
                    FolderListScreen(modifier = Modifier.padding(innerPadding))
                    Greeting(
                        name = "DevTeam",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/*
[FIXME]: Explain folder class
*/
Data class  Folder(
    val name: String,
   //FIXME: ButtonData is a placeholder for the List of buttons
    val buttonGrid: List<ButtonData>
)

/*
[FIXME]: Explain what this does
*/
class FolderAdapter(
    private val folders: List<Folder>,
    private val onClick: (Folder) -> Unit
) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    inner class FolderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val folderName: TextView = view.findViewById(R.id.folderName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.folder_item, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val folder = folders[position]
        holder.folderName.text = folder.name
        holder.itemView.setOnClickListener { onClick(folder) }
    }
/*
 getItemCount() is a part of RecyclerView, it returns the number of folders
 */
    override fun getItemCount() = folders.size
}




/* A composable is a "nestable" function from Jetpack that allows you to create
   complex UI structures. So "@Composable" tells the function "Greeting" to use
   the Jetpack Compose UI framework. */
@Composable
/*
var is mutable variable, vs val which is not.
*/
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var helloEmoji by remember {
        mutableStateOf(false)
    }
/* Function "Column" arranges items vertically. The function "Button" is placed
   within this column that shows "Hi, my name is ...". When this button is
   clicked, the state of "helloEmoji" is set to true and a GIF emoji is
   displayed.
   
    Function imported from import androidx.compose.foundation.layout.
    Column gives Greeting a vertical layout.
*/
    Column(
        horizontalAlignment  = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
/*
Modifier function contains decorations and ()
*/
        modifier = Modifier.padding(24.dp)

    )
/*
Button is a Composable function that creates a clickable button UI.
This function provides a string of text (Hi, my name is $name!"
 */
    {
        Button(
/*
 Sets a boolean state to var "helloEmoji".
*/
            onClick = { helloEmoji = true },
/*
If Button is clicked, set "helloEmoji" to true.
*/
            modifier = Modifier.padding(24.dp)
        ) {
/*
Text that shows inside the button.
*/
            Text(text = "Hi, my name is $name!")
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
/* @Preview allows us to preview the app inside of the Android Studio
   environment. */
@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    ChildrensTherapyTheme() {
        Greeting("Android")
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

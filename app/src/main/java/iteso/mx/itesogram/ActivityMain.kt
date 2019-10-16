package iteso.mx.itesogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import iteso.mx.itesogram.adapters.AdapterPhoto
import kotlinx.android.synthetic.main.item_photo.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import java.util.ArrayList

class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view_activity_main)

        doAsync {
            val query = ParseQuery.getQuery<ParseObject>("Feed")

            query.findInBackground ( object: FindCallback<ParseObject>{
                var photos: List<ParseObject> = arrayListOf()

                override fun done(PhotoList: List<ParseObject>, e : ParseException?) {
                    if (e==null) {
                        photos = PhotoList
                        activityUiThread {
                            recyclerView.adapter = AdapterPhoto(photos)
                            recyclerView.adapter?.notifyDataSetChanged()
                            recyclerView.layoutManager = LinearLayoutManager(parent)

                        }
                    }
                }


            })
        }

    }

    class Feed(val username: String, val photo: File, val commentsNumber: Int){
    }
}

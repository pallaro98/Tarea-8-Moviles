package iteso.mx.itesogram.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseObject
import iteso.mx.itesogram.ActivityMain
import iteso.mx.itesogram.R
import java.io.File

class AdapterPhoto(private val photos: List<ParseObject>): RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        holder.bind(photos[position])
    }
}

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var rm = Glide.with(view)
    private val userNamePhoto: TextView = view.findViewById(R.id.item_photo_username)
    private val imagePhoto: ImageView = view.findViewById(R.id.item_photo_image)
    private val likescountPhoto: TextView = view.findViewById(R.id.item_photo_likes_count)

    fun bind(photo: ParseObject) {
        val name = photo.getString("username") + ""
        val imagenParse = photo.getParseFile("photo")
        val comentarios = photo.getInt("commentsNumber")

        userNamePhoto.text = name
        rm.load(imagenParse!!.url).into(imagePhoto)
        likescountPhoto.text = comentarios.toString()
    }
}
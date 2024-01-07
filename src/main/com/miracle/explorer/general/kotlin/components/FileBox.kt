package components

import com.miracle.explorer.Main
import javafx.geometry.Pos.*
import model.FileModel
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment


class FileBox : VBox() {

    val label:Text= Text()
    val imageView:ImageView = ImageView()

    lateinit var file: FileModel

    init {
        isMouseTransparent = false
        alignment = CENTER
        label.textAlignment = TextAlignment.CENTER
        label.font = Font.font(12.0)
        label.style = "-fx-pref-width: 75; -fx-pref-height: 5; -fx-padding:8 0 0 0; -fx-fill:white; -fx-stroke:black; -fx-stroke-width:0.05px;"
        label.wrappingWidth = 75.0
        //label.maxWidth = 75.0
        //label.maxHeight = 25.0
        //label.wrapTextProperty().set(true)

        imageView.isPreserveRatio = true
        imageView.fitHeight = 60.0
        imageView.fitWidth = 60.0

        children.addAll(imageView,label)
    }

    fun setValues(file: FileModel){
        this.file = file
        label.text = if (file.name.length>16) {file.name.substring(0,13)+"..."} else {file.name}
        imageView.image = Image(Main::class.java.getResourceAsStream(file.iconUrl))
    }

}

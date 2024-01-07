package com.miracle.explorer
import com.miracle.explorer.general.kotlin.util.actions.*
import components.FileBox
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.ContextMenu
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.image.Image
import javafx.scene.input.ContextMenuEvent
import javafx.scene.layout.*
import javafx.stage.Stage
import model.DirectoryModel
import java.awt.Desktop
import java.io.File

class Main : Application() {
    override fun start(stage: Stage) {


        val fxmlLoader = FXMLLoader(Main::class.java.getResource("/fxml/hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        val sceneController: MainController = fxmlLoader.getController()

        val desktop = File(System.getProperty("user.home"), "Desktop")

        val flowPane:FlowPane = sceneController.desktopPane
        flowPane.padding = Insets(10.0)
        flowPane.vgap = 20.0
        flowPane.hgap = 20.0
        flowPane.isCache = true

        loadDesktop(desktop, flowPane, stage)

        sceneController.root.background = Background(BackgroundImage(Image("C:\\Users\\boris\\Pictures\\обои\\Windows+11+Pantone+Dark.jpg"),
            BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT))

        //System.out.println(System.getProperties().keys)

        stage.title = "Explorer"
        stage.scene = scene

        stage.show()
    }

    fun loadDesktop(desktop: File, container:FlowPane, stage: Stage){

        container.children.clear()

        val directoryModel = DirectoryModel(desktop)

        directoryModel.files.forEach { fileModel ->
            if(!fileModel.file.isHidden) {
                val fileBox = FileBox()
                fileBox.setValues(fileModel)
                fileBox.setOnContextMenuRequested { event ->
                    showFileContextMenu(stage, event, { actionEvent->
                        if (actionEvent is CopyActionEvent){

                        } else if (actionEvent is CutActionEvent) {

                        } else if (actionEvent is RenameActionEvent) {

                        } else if(actionEvent is DeleteActionEvent){
                            Desktop.getDesktop().moveToTrash(fileModel.file)
                        }
                    })
                    event.consume()
                }
                fileBox.setOnMouseClicked { event ->
                    if (event.clickCount == 2) {
                        Desktop.getDesktop().open(fileModel.file)
                        event.consume()
                    }
                }
                container.children.add(fileBox)
            }
        }

        container.setOnContextMenuRequested { event->
            showDirectoryContextMenu(stage, event , { actionEvent->
                if (actionEvent is RefreshActionEvent){
                    loadDesktop(desktop, container, stage)
                } else if (actionEvent is CreateFolderActionEvent) {

                } else if (actionEvent is CreateTextFileActionEvent) {

                } else if (actionEvent is CreateFileActionEvent) {

                }
            })
            event.consume()
        }
    }

}

fun showDirectoryContextMenu(stage: Stage, event: ContextMenuEvent, eventHandler: EventHandler<ActionEvent>) {
    val contextMenu = ContextMenu()

    val createSubMenu = Menu("Create")
    val folderMenuItem = MenuItem("Folder")
    folderMenuItem.onAction = EventHandler {eventHandler.handle(CreateFolderActionEvent())}
    val textFileMenuItem = MenuItem("Text file")
    textFileMenuItem.onAction = EventHandler {eventHandler.handle(CreateTextFileActionEvent())}
    val fileMenuItem = MenuItem("File")
    fileMenuItem.onAction = EventHandler {eventHandler.handle(CreateFileActionEvent())}

    createSubMenu.items.addAll(
        folderMenuItem,
        textFileMenuItem,
        fileMenuItem)

    val refreshMenuItem = MenuItem("Refresh")
    refreshMenuItem.onAction = EventHandler {eventHandler.handle(RefreshActionEvent())}

    contextMenu.items.addAll(
        refreshMenuItem,
        createSubMenu)

    contextMenu.show(stage, event.screenX, event.screenY)
}

fun showFileContextMenu(stage: Stage, event: ContextMenuEvent, eventHandler: EventHandler<ActionEvent>) {
    val contextMenu = ContextMenu()

    val copyMenuItem = MenuItem("Copy")
    copyMenuItem.onAction = EventHandler {eventHandler.handle(CopyActionEvent())}
    val cutMenuItem = MenuItem("Cut")
    cutMenuItem.onAction = EventHandler {eventHandler.handle(CutActionEvent())}
    val renameMenuItem = MenuItem("Rename")
    renameMenuItem.onAction = EventHandler {eventHandler.handle(RenameActionEvent())}
    val deleteMenuItem = MenuItem("Delete")
    deleteMenuItem.onAction = EventHandler {eventHandler.handle(DeleteActionEvent())}

    contextMenu.items.addAll(
        copyMenuItem,
        cutMenuItem,
        renameMenuItem,
        deleteMenuItem)

    contextMenu.show(stage, event.screenX, event.screenY)
}

fun main() {
    Application.launch(Main::class.java)
}

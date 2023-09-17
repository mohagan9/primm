import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.awt.Dimension
import java.awt.Label
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JTextField


internal class DialogBox(private val frame: JFrame, private val textField: JTextField) : JFrame(), ActionListener {
    private val kotlinXmlMapper: ObjectMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .enable(SerializationFeature.INDENT_OUTPUT)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)


    private inline fun <reified T : Any> parseAs(fileName: String): T {
        return kotlinXmlMapper.readValue(File(fileName))
    }

    override fun actionPerformed(e: ActionEvent) {
        val s = e.actionCommand
        if (s == "Run") {
            val dialog = JDialog(frame)
            dialog.add(Label("Please wait..."))
            dialog.size = Dimension(240, 160)
            dialog.isVisible = true
            val x: Int = (toolkit.screenSize.width - dialog.width) / 2
            val y: Int = (toolkit.screenSize.height - dialog.height) / 2
            dialog.setLocation(x, y)
            frame.isVisible = false

            //Do split and convert
            val rdf = parseAs<ResourceDescriptionFramework>(textField.text)
            RdfToOpexSplitter().writeOpexFiles(rdf, kotlinXmlMapper, dialog, frame)
            println("Complete!")

            dialog.dispatchEvent(WindowEvent(dialog, WindowEvent.WINDOW_CLOSING))
            frame.dispatchEvent(WindowEvent(frame, WindowEvent.WINDOW_CLOSING))
        }
    }
}
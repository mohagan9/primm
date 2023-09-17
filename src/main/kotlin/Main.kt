import java.awt.Dimension
import java.awt.Label
import java.awt.Toolkit
import javax.swing.*
import javax.swing.WindowConstants.EXIT_ON_CLOSE


fun main(args: Array<String>) {
    val toolkit: Toolkit = Toolkit.getDefaultToolkit()
    val frame = JFrame("Split one XML into many Opex").apply {
        defaultCloseOperation = EXIT_ON_CLOSE
    }

    val field = JTextField(1).apply {
        text = "test.xml"
        maximumSize = Dimension(160, 32)
    }
    val box = DialogBox(frame, field)
    val panel = JPanel()
    val button = JButton("Run")
    button.addActionListener(box)
    val label = Label("Please enter an XML filename to read").apply {
        maximumSize = Dimension(240, 32)
    }
    panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
    panel.add(label)
    panel.add(field)
    panel.add(button)

    frame.add(panel)
    frame.setSize(360, 160)
    val x: Int = (toolkit.screenSize.width - frame.width) / 2
    val y: Int = (toolkit.screenSize.height - frame.height) / 2
    frame.setLocation(x, y)
    frame.isVisible = true
}
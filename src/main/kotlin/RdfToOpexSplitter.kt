import com.fasterxml.jackson.databind.ObjectMapper
import java.awt.event.WindowEvent
import java.io.File
import java.io.FileWriter
import javax.swing.JDialog
import javax.swing.JFrame
import javax.xml.stream.XMLOutputFactory
import javax.xml.stream.XMLStreamWriter


class RdfToOpexSplitter {
    private val illegalCharacters: Regex = Regex("[\\:*?/\"]")

    private fun generateOpexMetadata(rdf: ResourceDescriptionFramework): List<OpexMetadata> {
        var opexMetadata: MutableList<OpexMetadata> = ArrayList()
        for (dublinCoreDescription in rdf.dublinCoreDescriptions) {
            opexMetadata.add(
                OpexMetadata(
                    DescriptiveMetadata(dublinCoreDescription = dublinCoreDescription)
                ))
        }
        return opexMetadata
    }

    private fun createXmlWriter(title: String): XMLStreamWriter {
        val xmlOutputFactory = XMLOutputFactory.newFactory()
        return xmlOutputFactory.createXMLStreamWriter(FileWriter("${title}.xml")).apply {
            setDefaultNamespace("http://www.openpreservationexchange.org/opex/v1.2")
        }
    }

    fun getLastFilename(rdf: ResourceDescriptionFramework): String {
        val last = rdf.dublinCoreDescriptions.last()
        return illegalCharacters.replace(last.title ?: "", "")
    }

    fun writeOpexFiles(rdf: ResourceDescriptionFramework, xmlMapper: ObjectMapper, dialog: JDialog, frame: JFrame) {
        val opexMetadata = generateOpexMetadata(rdf)
        for (opex in opexMetadata) {
            var title = opex.descriptiveMetadata.dublinCoreDescription.title ?: ""
            title = illegalCharacters.replace(title, "")
            if (!title.isNullOrBlank()) {
                var output = xmlMapper.writeValueAsString(opex)
                output = output.replace("<OPEXMetadata>","<OPEXMetadata xmlns=\"http://www.openpreservationexchange.org/opex/v1.2\">")
                output = output.replace(
                    "<wstxns1:dc xmlns:wstxns1=\"oai_dc\">",
                    "<oai_dc:dc xmlns:oai_dc=\"http://www.openarchives.org/OAI/2.0/oai_dc/\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.openarchives.org/OAI/2.0/oai_dc/ oai_dc.xsd\">"
                ).replace("</wstxns1:dc>", "</oai_dc:dc>")

                for (i in 2..16) {
                    output = output.replace("wstxns${i}", "dc")
                }
                output = output.replace("<dc:c:", "<dc:").replace(" xmlns:dc=\"dc\"", "")

                File("${title}.opex").writeText("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n$output")
            }
        }
    }
}
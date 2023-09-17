import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(namespace = "rdf", localName = "RDF")
data class ResourceDescriptionFramework(
    @JacksonXmlProperty(isAttribute = false, namespace = "rdf", localName = "Description")
    @JacksonXmlElementWrapper(useWrapping = false)
    var dublinCoreDescriptions: List<DublinCoreDescription> = ArrayList()
)

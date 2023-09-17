import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "DescriptiveMetadata")
data class DescriptiveMetadata(
    @JacksonXmlProperty(isAttribute = false, namespace = "oai_dc", localName = "dc")
    @JacksonXmlElementWrapper(useWrapping = false)
    var dublinCoreDescription: DublinCoreDescription
)

@JacksonXmlRootElement(localName = "OPEXMetadata")
data class OpexMetadata(
    @JacksonXmlProperty(isAttribute = false, localName = "DescriptiveMetadata")
    @JacksonXmlElementWrapper(useWrapping = false)
    var descriptiveMetadata: DescriptiveMetadata
)

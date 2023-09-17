import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class DublinCoreDescription(
    @JacksonXmlProperty(isAttribute=false, namespace = "dc", localName="title")
    var title: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "c:creator")
    var creator: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "subject")
    var subject: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "description")
    var description: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "publisher")
    var publisher: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "contributor")
    var contributor: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "date")
    var date: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "type")
    var type: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "format")
    var format: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "identifier")
    var identifier: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "source")
    var source: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "language")
    var language: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "relation")
    var relation: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "coverage")
    var coverage: String? = null,

    @JacksonXmlProperty(isAttribute = false, namespace = "dc", localName = "rights")
    var rights: String? = null
)
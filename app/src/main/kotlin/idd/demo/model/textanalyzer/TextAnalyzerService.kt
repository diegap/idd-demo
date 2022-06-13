package idd.demo.model.textanalyzer

interface TextAnalyzerService {
    fun isAllowed(text: String): Boolean
}

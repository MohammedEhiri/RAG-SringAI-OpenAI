package Med.EHIRI.Rag_SpringAI_OpenAI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.pdf.ParagraphPdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class VectorPDF implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(VectorPDF.class);
    private final VectorStore vecStore;

    @Value("classpath:/files/kag.pdf")
    private Resource pdf;

    public VectorPDF(VectorStore vectorStore) {
        this.vecStore = vectorStore;
    }

    @Override
    public void run(String... args) throws Exception {
        var pdfReader = new ParagraphPdfDocumentReader(pdf);
        TextSplitter textSplitter = new TokenTextSplitter();
        vecStore.accept(textSplitter.apply(pdfReader.get()));
    }
}

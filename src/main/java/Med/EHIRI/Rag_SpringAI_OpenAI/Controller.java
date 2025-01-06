package Med.EHIRI.Rag_SpringAI_OpenAI;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final ChatClient chatClient;

    public Controller(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
                .build();
    }

    @GetMapping("/")
    public String chat( String prompt) {

        //example of prompt : " explain the concept of Kag and the difference between Rag and Kag "
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
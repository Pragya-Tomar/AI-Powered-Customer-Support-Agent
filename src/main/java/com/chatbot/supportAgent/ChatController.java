package com.chatbot.supportAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // Main endpoint — send a message and get a response
    @PostMapping("/message")
    public Map<String, String> sendMessage(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");

        if (userMessage == null || userMessage.trim().isEmpty()) {
            return Map.of("response", "Please type a message.");
        }

        String response = chatService.handleMessage(userMessage);
        return Map.of("response", response);
    }

    // Get full conversation history
    @GetMapping("/history")
    public List<ChatMessage> getHistory() {
        return chatService.getHistory();
    }

    // Health check endpoint
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "status", "running",
            "service", "AI Customer Support Agent",
            "version", "1.0"
        );
    }
}
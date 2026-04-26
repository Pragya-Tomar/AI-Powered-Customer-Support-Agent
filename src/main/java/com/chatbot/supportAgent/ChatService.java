package com.chatbot.supportAgent;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ChatService {

    private final Map<String, String> knownIssues = new HashMap<>();
    private final List<ChatMessage> conversationHistory = new ArrayList<>();
    private int unresolvedCount = 0;

    public ChatService() {
        // Layer 1 — Known problems & solutions
        knownIssues.put("course not loading", "Please check your internet connection and try refreshing the page. If the issue persists, try clearing your browser cache.");
        knownIssues.put("video buffering", "Try lowering the video quality settings. Also ensure no other apps are using your bandwidth.");
        knownIssues.put("unable to login", "Please try resetting your password at support.edtech.com/reset. If that doesn't work, check if Caps Lock is on.");
        knownIssues.put("payment failed", "Please check your card details and ensure you have sufficient balance. Try a different browser or payment method.");
        knownIssues.put("certificate not received", "Certificates are issued within 7 days of course completion. Check your registered email including spam folder.");
        knownIssues.put("app crashing", "Please uninstall and reinstall the app. Make sure your device OS is up to date.");
        knownIssues.put("refund", "Refund requests are processed within 5-7 business days. Please email billing@edtech.com with your order ID.");
        knownIssues.put("download", "To download course content, click the download icon next to each lesson. Downloads require a premium subscription.");
        knownIssues.put("certificate", "Certificates are issued within 7 days of course completion. Check your registered email including spam folder.");
        knownIssues.put("discount", "We offer student discounts of up to 30%. Visit edtech.com/offers or use code STUDENT30 at checkout.");
        knownIssues.put("subscription", "You can manage your subscription from Account Settings → Billing. Cancel anytime with no hidden fees.");
        knownIssues.put("forgot password", "Click 'Forgot Password' on the login page. A reset link will be sent to your registered email within 2 minutes.");
        knownIssues.put("change email", "To change your email, go to Account Settings → Profile → Edit Email. Verify the new email to confirm.");
        knownIssues.put("slow", "If the platform feels slow, try clearing browser cache, switching browsers, or checking your internet speed at fast.com.");

    }

    public String handleMessage(String userMessage) {
        String lowerMessage = userMessage.toLowerCase();

        // Save user message to history
        conversationHistory.add(new ChatMessage("user", userMessage));

        // Check if user wants human support
        if (lowerMessage.contains("human") || lowerMessage.contains("agent") || lowerMessage.contains("representative")) {
            return escalateToHuman();
        }

        // Layer 1 — Check known issues
        for (Map.Entry<String, String> entry : knownIssues.entrySet()) {
            if (lowerMessage.contains(entry.getKey())) {
                unresolvedCount = 0;
                String response = "🤖 " + entry.getValue() + "\n\nDid this solve your problem? If not, type 'still not working' and I'll escalate.";
                conversationHistory.add(new ChatMessage("assistant", response));
                return response;
            }
        }

        // Check if user says still not resolved
        if (lowerMessage.contains("still not working") || lowerMessage.contains("not resolved") || lowerMessage.contains("didn't work")) {
            unresolvedCount++;
            if (unresolvedCount >= 1) {
                return escalateToHuman();
            }
        }

        // Layer 2 — AI fallback (mocked Gemini response)
        unresolvedCount++;
        String aiResponse = mockGeminiResponse(userMessage);
        conversationHistory.add(new ChatMessage("assistant", aiResponse));
        return aiResponse;
    }

    private String mockGeminiResponse(String userMessage) {
        // This is where real Gemini API call will go later
        return "🤖 I understand you're facing an issue with: \"" + userMessage + "\". " +
               "Let me help you with that. Please try the following steps:\n" +
               "1. Refresh your browser or restart the app\n" +
               "2. Clear your cache and cookies\n" +
               "3. Try on a different device or browser\n\n" +
               "If the issue persists, type 'still not working' and I'll connect you to a human agent.";
    }

    private String escalateToHuman() {
        unresolvedCount = 0;
        return "👨‍💼 I'm connecting you to our human support team.\n\n" +
               "📞 Call us: 1800-123-4567 (Mon-Sat, 9AM-6PM)\n" +
               "📧 Email: support@edtech.com\n" +
               "💬 Live Chat: support.edtech.com/live\n\n" +
               "Average response time: 5 minutes. Sorry for the inconvenience!";
    }

    public List<ChatMessage> getHistory() {
        return conversationHistory;
    }
}
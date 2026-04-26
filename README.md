# 🤖 AI-Powered Customer Support Agent

A Spring Boot REST API that simulates an intelligent customer support chatbot for EdTech platforms. Features a 3-layer conversation orchestration system with a built-in chat UI.

---

## 🚀 Features

- **Layer 1 — Instant Answers**: Resolves common issues instantly from a knowledge base (course loading, login, payments, certificates, etc.)
- **Layer 2 — AI Fallback**: Routes unrecognized queries to a Generative AI service (Gemini API ready)
- **Layer 3 — Human Escalation**: Automatically escalates unresolved issues to human support with contact details
- **Chat UI**: Clean, responsive chat interface accessible from the browser
- **Conversation History**: Full session-based conversation tracking via REST API
- **Dockerized**: Fully containerized for consistent deployment anywhere

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3.5 |
| REST API | Spring Web (MVC) |
| AI Integration | Gemini API (mocked — plug in your key to enable) |
| Frontend | HTML5, CSS3, Vanilla JS |
| Containerization | Docker |
| Version Control | Git, GitHub |

---

## 📁 Project Structure
src/
├── main/
│   ├── java/com/chatbot/supportAgent/
│   │   ├── SupportAgentApplication.java   # Entry point
│   │   ├── ChatController.java            # REST endpoints
│   │   ├── ChatService.java               # 3-layer logic + AI integration
│   │   └── ChatMessage.java               # Message model
│   └── resources/
│       └── static/
│           └── index.html                 # Chat UI
Dockerfile

---

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/chat/message` | Send a message, get AI response |
| GET | `/api/chat/history` | Get full conversation history |
| GET | `/api/chat/health` | Health check |

### Example Request
```bash
curl -X POST http://localhost:8080/api/chat/message \
  -H "Content-Type: application/json" \
  -d '{"message": "Course not loading"}'
```

### Example Response
```json
{
  "response": "🤖 Please check your internet connection and try refreshing the page..."
}
```

---

## ⚙️ Running Locally

### Option 1 — Run with Maven
```bash
./mvnw spring-boot:run
```
Then open: `http://localhost:8080`

### Option 2 — Run with Docker
```bash
docker build -t support-agent .
docker run -p 8080:8080 support-agent
```

---

## 🔑 Enabling Live AI (Gemini)

This project is pre-wired for Gemini API integration. To enable live AI responses:

1. Get a free API key at [aistudio.google.com](https://aistudio.google.com)
2. Set the environment variable:
```bash
export GEMINI_API_KEY=your_key_here
```
3. Replace the `mockGeminiResponse()` method in `ChatService.java` with a real API call

---

## 📞 Human Escalation

When issues are unresolved, the bot automatically provides:
- 📞 Phone support
- 📧 Email support  
- 💬 Live chat link

---

## 👩‍💻 Author

Pragya Tomar— Java Full Stack Developer  
[LinkedIn](https://linkedin.com/in/pragya-tomar-640523348)  
[GitHub](https://github.com/Pragya-Tomar)
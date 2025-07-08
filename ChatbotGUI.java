import javax.swing.*;

public class ChatbotGUI {
    public static void main(String[] args) {
        String input;
        do {
            input = JOptionPane.showInputDialog(null, "Ask something (type 'exit' to quit):");
            if (input == null || input.equalsIgnoreCase("exit")) break;

            String response = getResponse(input.toLowerCase());
            JOptionPane.showMessageDialog(null, response);
        } while (true);
    }

    static String getResponse(String input) {
        switch (input) {
            case "hi": return "Hello! How can I assist you?";
            case "how are you": return "I'm just a bot, but I'm doing great!";
            case "what is your name": return "I am a Java AI Chatbot.";
            case "bye": return "Goodbye!";
            default: return "I'm sorry, I don't understand that.";
        }
    }
}
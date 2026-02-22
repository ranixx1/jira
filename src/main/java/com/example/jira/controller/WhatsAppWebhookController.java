package com.example.jira.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/webhook")
public class WhatsAppWebhookController {

    private final String MY_TOKEN = "meu_token_seguro_123"; // Coloque o mesmo que no Meta
    private final String PHONE_NUMBER_ID = "1062617386925680"; // Meta phone ID
    private final String ACCESS_TOKEN = "EAAbzoyQlvlwBQw5unzW8wEWMNDXllKLtVEMy28LbEn4fuPdO6v2fgIsHKJQiTaebkRDeIm33YZAzu1FZAPX9vggIK0w4ddw9VjBZAeEun4oaeOXtTpU2VD8etV5stFja5T8mxcEy7vNLtFYm3yUToO5tquwKIPJiWGMa3QHRJtcn4dASkjpehrACY0k1HXKlPvrTncYJT7Mf4JKLhZCnibDZB2J7wOZBxkjXtEWPeu2iGQvP8AEEAomdvhbmIiQs3iAq8Te7uZCfTAbLSavDzs3zoQA"; // Meta token

    // --------------------------
    // VERIFICAÇÃO DO WEBHOOK
    // --------------------------
    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String verifyToken,
            @RequestParam("hub.challenge") String challenge) {

        if ("subscribe".equals(mode) && MY_TOKEN.equals(verifyToken)) {
            return ResponseEntity.ok(challenge);
        }

        return ResponseEntity.status(403).body("Verification failed");
    }

    // --------------------------
    // RECEBIMENTO DE MENSAGENS
    // --------------------------
    @PostMapping
    public ResponseEntity<String> receiveMessage(@RequestBody Map<String, Object> payload) {
        List<Map<String, Object>> entries = (List<Map<String, Object>>) payload.get("entry");

        for (Map<String, Object> entry : entries) {
            List<Map<String, Object>> changes = (List<Map<String, Object>>) entry.get("changes");

            for (Map<String, Object> change : changes) {
                Map<String, Object> value = (Map<String, Object>) change.get("value");
                List<Map<String, Object>> messages = (List<Map<String, Object>>) value.get("messages");

                if (messages != null) {
                    for (Map<String, Object> message : messages) {
                        String from = (String) message.get("from");
                        Map<String, String> text = (Map<String, String>) message.get("text");
                        String body = text.get("body").trim().toLowerCase();

                        switch (body) {
                            case "!listar chamados":
                                String resposta = listarChamados();
                                sendMessageWhatsApp(from, resposta);
                                break;

                            default:
                                sendMessageWhatsApp(from, "Comando não reconhecido. Use !listar chamados");
                                break;
                        }
                    }
                }
            }
        }

        return ResponseEntity.ok("EVENT_RECEIVED");
    }

    // --------------------------
    // LISTAR CHAMADOS DA API INTERNA
    // --------------------------
    private String listarChamados() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/chamados";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        List<Map<String, Object>> chamados = response.getBody();

        if (chamados == null || chamados.isEmpty()) {
            return "Nenhum chamado encontrado.";
        }

        StringBuilder sb = new StringBuilder("📋 Lista de Chamados:\n");
        for (Map<String, Object> c : chamados) {
            sb.append("ID: ").append(c.get("id"))
              .append(" | Título: ").append(c.get("titulo"))
              .append(" | Status: ").append(c.get("status"))
              .append("\n");
        }

        return sb.toString();
    }

    // --------------------------
    // ENVIAR MENSAGEM PARA O WHATSAPP
    // --------------------------
    private void sendMessageWhatsApp(String to, String message) {
        String url = "https://graph.facebook.com/v17.0/" + PHONE_NUMBER_ID + "/messages";

        Map<String, Object> payload = new HashMap<>();
        payload.put("messaging_product", "whatsapp");
        payload.put("to", to);
        payload.put("text", Map.of("body", message));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(ACCESS_TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        restTemplate.postForEntity(url, request, String.class);
    }
}
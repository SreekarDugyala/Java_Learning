
@Test
    public void testEmailAttributeListener_success() throws JsonProcessingException, IEmailDeliveryException {
        String message = "{\"correlationId\": \"12345\", \"preferredFileName\": \"test.pdf\"}";
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put(KafkaHeaders.RECEIVED_MESSAGE_KEY, "key");
        MessageHeaders messageHeaders = new MessageHeaders(headersMap);

        ObjectMapper mapper = new ObjectMapper();
        EmailAttribute emailAttributes = mapper.readValue(message, EmailAttribute.class);

        when(imEmailDAO.create(any(EmailAttribute.class), anyString())).thenReturn(1);
        when(imEmailMetadataDAO.createBatch(any(EmailAttribute.class), anyString())).thenReturn(new int[]{1});
        doNothing().when(emailDelivery).processEmailDeliveryWithRunEmailAttributes(any(EmailAttribute.class));

        kafkaMessageListener.emailAttributeListener(message, messageHeaders);

        verify(imEmailDAO, times(1)).create(any(EmailAttribute.class), anyString());
        verify(imEmailMetadataDAO, times(1)).createBatch(any(EmailAttribute.class), anyString());
        verify(emailDelivery, times(1)).processEmailDeliveryWithRunEmailAttributes(any(EmailAttribute.class));
    }

    @Test
    public void testEmailAttributeListener_jsonProcessingException() {
        String invalidMessage = "invalid json";
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put(KafkaHeaders.RECEIVED_MESSAGE_KEY, "key");
        MessageHeaders messageHeaders = new MessageHeaders(headersMap);

        assertThrows(JsonProcessingException.class, () -> {
            kafkaMessageListener.emailAttributeListener(invalidMessage, messageHeaders);
        });
    }

    @Test
    public void testEmailAttributeListener_iEmailDeliveryException() throws JsonProcessingException, IEmailDeliveryException {
        String message = "{\"correlationId\": \"12345\", \"preferredFileName\": \"test.pdf\"}";
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put(KafkaHeaders.RECEIVED_MESSAGE_KEY, "key");
        MessageHeaders messageHeaders = new MessageHeaders(headersMap);

        ObjectMapper mapper = new ObjectMapper();
        EmailAttribute emailAttributes = mapper.readValue(message, EmailAttribute.class);

        when(imEmailDAO.create(any(EmailAttribute.class), anyString())).thenReturn(1);
        when(imEmailMetadataDAO.createBatch(any(EmailAttribute.class), anyString())).thenReturn(new int[]{1});
        doThrow(IEmailDeliveryException.class).when(emailDelivery).processEmailDeliveryWithRunEmailAttributes(any(EmailAttribute.class));

        kafkaMessageListener.emailAttributeListener(message, messageHeaders);

        verify(imEmailDAO, times(1)).create(any(EmailAttribute.class), anyString());
        verify(imEmailMetadataDAO, times(1)).createBatch(any(EmailAttribute.class), anyString());
        verify(emailDelivery, times(1)).processEmailDeliveryWithRunEmailAttributes(any(EmailAttribute.class));
    }
}

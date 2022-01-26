package com.assessment.payment.controller;

import com.assessment.payment.util.PaymentDataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Aaima Bashir on 1/26/2022
 */

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PaymentControllerTest {
  @Autowired private MockMvc mockMvc;
  private PaymentDataProvider paymentDataProvider;
  private ObjectMapper objectMapper;
  private ObjectWriter objectWriter;
  private static String URL = "/payment/topupwallet";

  @BeforeEach
  void initUseCase() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    paymentDataProvider = new PaymentDataProvider();
  }

  @Test
  public void testTopUpWallet_ValidRequest() throws Exception {

    String requestJson =
        objectWriter.writeValueAsString(paymentDataProvider.getValidPaymentRequest());

    MvcResult result =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post(URL)
                    .content(requestJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String paymentResponse = result.getResponse().getContentAsString();
    assertNotNull(paymentResponse);
  }

  @Test
  public void testTopUpWallet_BadRequest() throws Exception {

    String requestJson =
        objectWriter.writeValueAsString(paymentDataProvider.getBadPaymentRequest());

    mockMvc
        .perform(
            MockMvcRequestBuilders.post(URL)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andReturn();
  }
}

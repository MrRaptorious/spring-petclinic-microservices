package org.springframework.samples.petclinic.invoices.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.invoices.model.Invoice;
import org.springframework.samples.petclinic.invoices.model.InvoiceRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceResource.class)
@ActiveProfiles("test")
class InvoiceResourceTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  InvoiceRepository invoiceRepository;

  @Test
  void shouldFetchVisits() throws Exception {

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = formatter.format(date);

    given(invoiceRepository.findByVisitId(111))
        .willReturn(
            Invoice.InvoiceBuilder.anInvoice()
                .id(1)
                .amount(10.00)
                .dueDate(date)
                .visitId(111)
                .build()
        );

    mvc.perform(get("/invoice?visitId=111"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.amount").value(10.00))
        .andExpect(jsonPath("$.dueDate").value(formattedDate))
        .andExpect(jsonPath("$.visitId").value(111));
  }
}

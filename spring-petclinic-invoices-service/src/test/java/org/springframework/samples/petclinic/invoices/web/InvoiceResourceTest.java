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


import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceResource.class)
@ActiveProfiles("test")
class InvoiceResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    InvoiceRepository invoiceRepository;

    @Test
    void shouldFetchInvoices() throws Exception {
        return;
        // given(invoiceRepository.findByVisitIdIn(asList(111, 222)))
        //     .willReturn(
        //         asList(
        //             Invoice.InvoiceBuilder.anInvoice()
        //                 .id(1)
        //                 .visitId(111)
        //                 .build(),
        //             Invoice.InvoiceBuilder.anInvoice()
        //                 .id(2)
        //                 .visitId(222)
        //                 .build(),
        //             Invoice.InvoiceBuilder.anInvoice()
        //                 .id(3)
        //                 .visitId(222)
        //                 .build()
        //         )
        //     );

        // mvc.perform(get("/pets/invoices?petId=111,222"))
        //     .andExpect(status().isOk())
        //     .andExpect(jsonPath("$.items[0].id").value(1))
        //     .andExpect(jsonPath("$.items[1].id").value(2))
        //     .andExpect(jsonPath("$.items[2].id").value(3))
        //     .andExpect(jsonPath("$.items[0].petId").value(111))
        //     .andExpect(jsonPath("$.items[1].petId").value(222))
        //     .andExpect(jsonPath("$.items[2].petId").value(222));
    }
}

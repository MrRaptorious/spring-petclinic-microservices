/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.invoices.web;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.invoices.model.Invoice;
import org.springframework.samples.petclinic.invoices.model.InvoiceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 * @author Maciej Szarlinski
 * @author Ramazan Sakin
 */
@RestController
@Timed("petclinic.invoice")
class InvoiceResource {

    private static final Logger log = LoggerFactory.getLogger(InvoiceResource.class);

    private final InvoiceRepository invoiceRepository;

    InvoiceResource(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @PostMapping("owners/*/pets/{petId}/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice create(
        @Valid @RequestBody Invoice invoice,
        @PathVariable("petId") @Min(1) int petId) {

        invoice.setPetId(petId);
        log.info("Saving invoice {}", invoice);
        return invoiceRepository.save(invoice);
    }

    @GetMapping("owners/*/pets/{petId}/invoices")
    public List<Invoice> read(@PathVariable("petId") @Min(1) int petId) {
        return invoiceRepository.findByPetId(petId);
    }

    @GetMapping("pets/invoices")
    public Invoices read(@RequestParam("petId") List<Integer> petIds) {
        final List<Invoice> byPetIdIn = invoiceRepository.findByPetIdIn(petIds);
        return new Invoices(byPetIdIn);
    }

    record Invoices(
        List<Invoice> items
    ) {
    }
}

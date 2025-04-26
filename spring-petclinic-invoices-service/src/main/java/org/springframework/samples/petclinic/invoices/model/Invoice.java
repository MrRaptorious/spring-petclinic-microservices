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
package org.springframework.samples.petclinic.invoices.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Simple JavaBean domain object representing a invoice.
 *
 * @author Ken Krebs
 * @author Maciej Szarlinski
 * @author Ramazan Sakin
 */
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "invoice_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date = new Date();

    @Size(max = 8192)
    @Column(name = "description")
    private String description;

    @Column(name = "pet_id")
    private int petId;

    public Integer getId() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }


    public static final class InvoiceBuilder {
        private Integer id;
        private Date date;
        private @Size(max = 8192) String description;
        private int petId;

        private InvoiceBuilder() {
        }

        public static InvoiceBuilder aInvoice() {
            return new InvoiceBuilder();
        }

        public InvoiceBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public InvoiceBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public InvoiceBuilder description(String description) {
            this.description = description;
            return this;
        }

        public InvoiceBuilder petId(int petId) {
            this.petId = petId;
            return this;
        }

        public Invoice build() {
            Invoice invoice = new Invoice();
            invoice.setId(id);
            invoice.setDate(date);
            invoice.setDescription(description);
            invoice.setPetId(petId);
            return invoice;
        }
    }
}

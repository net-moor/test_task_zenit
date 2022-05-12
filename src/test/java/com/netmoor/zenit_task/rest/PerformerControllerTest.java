package com.netmoor.zenit_task.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.netmoor.zenit_task.IntegrationTest;
import com.netmoor.zenit_task.api.dto.CreatePerformerDto;
import com.netmoor.zenit_task.api.dto.PerformerDto;
import com.netmoor.zenit_task.api.dto.UpdatePerformerDto;
import com.netmoor.zenit_task.api.dto.pageble.PerformerDtoPage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author Nikolay_Batov on 11.05.2022
 */
public class PerformerControllerTest extends IntegrationTest {

    @Test
    void whenCreatePerformer_thenSuccess() {

        var reqBody = new CreatePerformerDto()
                .setFistName("FistName")
                .setLastName("LastName")
                .setMiddleName("MiddleName");

        var exchange = testRestTemplate.exchange(
                "/api/v0/performer",
                HttpMethod.POST,
                new HttpEntity<>(reqBody),
                PerformerDto.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertNotNull(exchange.getBody());

        assertNotNull(exchange.getBody().getId());

        var performers = performerRepository.findAll();
        assertEquals(1, performers.size());
    }

    @Test
    void whenDeletePerformer_thenSuccess() {
        var performer = createPerformer(true);

        testRestTemplate.delete("/api/v0/performer/{performerId}", Map.of("performerId", performer.getId().toString()));

        var performers = performerRepository.findAll();
        assertEquals(0, performers.size());
    }

    @Test
    void whenGetPerformerById_thenSuccess() {
        var performer = createPerformer(true);

        var response = testRestTemplate.getForEntity(
                "/api/v0/performer/{performerId}",
                PerformerDto.class,
                Map.of("performerId", performer.getId().toString()));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals(new PerformerDto()
                .setId(performer.getId())
                .setFistName(performer.getFistName())
                .setLastName(performer.getLastName())
                .setMiddleName(performer.getMiddleName()), response.getBody());
    }

    @Test
    void whenUpdatePerformer_thenSuccess() {
        var performer = createPerformer(true);

        var expected = new PerformerDto()
                .setId(performer.getId())
                .setFistName("UpdateFistName")
                .setLastName("UpdateLastName")
                .setMiddleName("UpdateMiddleName");

        var body = new UpdatePerformerDto()
                .setFistName(expected.getFistName())
                .setLastName(expected.getLastName())
                .setMiddleName(expected.getMiddleName());

        var response = testRestTemplate.exchange(
                "/api/v0/performer/{performerId}",
                HttpMethod.PUT,
                new HttpEntity<>(body),
                PerformerDto.class,
                Map.of("performerId", performer.getId())
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expected, response.getBody());
    }

    @Test
    void whenGetPerformerByFilter_thenSuccess() {
        createPerformer(true, "name1");
        createPerformer(true, "name2", "lastName2");
        createPerformer(true, "fistName3");

        var response = testRestTemplate.exchange(
                "/api/v0/performer?fistName={fistName}&lastName={lastName}",
                HttpMethod.GET,
                null,
                PerformerDtoPage.class,
                Map.of("fistName", "name", "lastName", "lastName2")
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
    }
}

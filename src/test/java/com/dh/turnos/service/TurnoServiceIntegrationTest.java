package com.dh.turnos.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TurnoServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void crear_paciente_odontologo_turno() throws Exception {
        String jsonPostOdontologo = "{\n" +
                "    \"nombre\" : \"Salva\",\n" +
                "    \"apellido\" : \"Diaz\",\n" +
                "    \"matricula\" : 1234\n" +
                "}";

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPostOdontologo))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());

        String jsonPostPaciente = "{\n" +
                "\t\"nombre\": \"salvador\",\n" +
                "\t\"apellido\": \"diaz\",\n" +
                "\t\"dni\": \"40750\",\n" +
                "\t\"domicilio\": {\n" +
                "\t\t\"calle\": \"algo\",\n" +
                "\t\t\"numero\": 5,\n" +
                "\t\t\"localidad\": \"cba\",\n" +
                "\t\t\"provincia\": \"cba\"\n" +
                "\t}\n" +
                "}";

        MvcResult response1 = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPostPaciente))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Assertions.assertFalse(response1.getResponse().getContentAsString().isEmpty());

        String jsonPostTurno = "{\n" +
                "\t\"pacienteId\": 1,\n" +
                "\t\"odontologoId\": 1,\n" +
                "\t\"fechaHora\": \"2017-05-14T10:34\"\n" +
                "}";

        MvcResult response2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPostTurno))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        Assertions.assertFalse(response2.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void buscar_turno_generado() throws Exception {
        String jsonPostOdontologo = "{\n" +
                "    \"nombre\" : \"Salva\",\n" +
                "    \"apellido\" : \"Diaz\",\n" +
                "    \"matricula\" : 1234\n" +
                "}";

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPostOdontologo))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String jsonPostPaciente = "{\n" +
                "\t\"nombre\": \"salvador\",\n" +
                "\t\"apellido\": \"diaz\",\n" +
                "\t\"dni\": \"40750\",\n" +
                "\t\"domicilio\": {\n" +
                "\t\t\"calle\": \"algo\",\n" +
                "\t\t\"numero\": 5,\n" +
                "\t\t\"localidad\": \"cba\",\n" +
                "\t\t\"provincia\": \"cba\"\n" +
                "\t}\n" +
                "}";

        MvcResult response1 = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPostPaciente))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String jsonPostTurno = "{\n" +
                "\t\"pacienteId\": 1,\n" +
                "\t\"odontologoId\": 1,\n" +
                "\t\"fechaHora\": \"2017-05-14T10:34\"\n" +
                "}";

        MvcResult response2 = this.mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPostTurno))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        MvcResult response3 = this.mockMvc.perform(MockMvcRequestBuilders.get("/turnos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        String expectedResponse = "{\"id\":1,\"paciente\":{\"nombre\":\"salvador\",\"apellido\":\"diaz\",\"dni\":\"40750\",\"domicilio\":{\"id\":1,\"calle\":\"algo\",\"numero\":5,\"localidad\":\"cba\",\"provincia\":\"cba\"},\"id\":1,\"fechaIngreso\":\"2022-07-11\"},\"odontologo\":{\"nombre\":\"Salva\",\"apellido\":\"Diaz\",\"matricula\":1234,\"id\":1},\"fechaHora\":\"2017-05-14T10:34:00\"}";

        Assertions.assertEquals(response3.getResponse().getContentAsString(), expectedResponse);
    }
}
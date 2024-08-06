package com.teste.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.livraria.service.RelatorioService;

@RestController
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        try {
        	String reportType = "rel_autores";
            byte[] report = relatorioService.gerarRelatorio(reportType);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", reportType + ".pdf");

            return ResponseEntity.ok().headers(headers).body(report);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}

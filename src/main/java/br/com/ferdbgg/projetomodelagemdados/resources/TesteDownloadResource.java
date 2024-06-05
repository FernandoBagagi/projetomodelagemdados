package br.com.ferdbgg.projetomodelagemdados.resources;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/testedownload")
public class TesteDownloadResource {
    
    @GetMapping("/txt")
    public ResponseEntity<byte[]> downloadFile() throws IOException {
        // Localização do arquivo
        Resource resource = new ClassPathResource("static/testedownload.txt");

        // Lendo o arquivo como bytes
        byte[] fileContent = Files.readAllBytes(resource.getFile().toPath());

        // Configurando o cabeçalho da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=testedownload.txt");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");

        // Retornando o arquivo como uma resposta HTTP
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }

}

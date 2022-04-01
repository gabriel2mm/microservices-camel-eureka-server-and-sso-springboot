package br.com.spring.batch.exemple.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.*;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomTextFileReader implements ItemReader<String>  {

    private static boolean state = false;
    private final Resource resource;

    @Override
    public String read() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()));
        if(!state){
            state = true;
            StringBuilder stringBuilder = new StringBuilder();
            String linha = "";
            while((linha = bufferedReader.readLine()) != null){
                stringBuilder.append(linha + "\n");
            }
            return stringBuilder.toString();
        }

        return null;
    }
}

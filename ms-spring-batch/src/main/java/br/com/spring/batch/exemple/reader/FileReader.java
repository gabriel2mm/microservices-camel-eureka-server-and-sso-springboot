package br.com.spring.batch.exemple.reader;

import br.com.spring.batch.exemple.domain.Client;
import br.com.spring.batch.exemple.mapper.ClientMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
public class FileReader {

    @Bean(name="myFileReader")
    public FlatFileItemReader<Client> streamReader(){
        return new FlatFileItemReaderBuilder<Client>()
                .resource(new PathResource("ms-spring-batch/src/main/resources/dados/pessoas.csv"))
                .name("FilePessoaReader")
                .delimited()
                .names("nome", "email", "data_nascimento", "idade", "id")
                .addComment("--")
                .fieldSetMapper(new ClientMapper())
                .build();
    }

}

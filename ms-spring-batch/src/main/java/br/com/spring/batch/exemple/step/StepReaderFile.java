package br.com.spring.batch.exemple.step;

import br.com.spring.batch.exemple.configuration.TaskExecutorConfiguration;
import br.com.spring.batch.exemple.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.task.TaskExecutor;

@Configuration
@RequiredArgsConstructor
@Import(TaskExecutorConfiguration.class)
public class StepReaderFile {

    private boolean batchJobState = false;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step simpleStepReaderFile(
            @Qualifier("myTaskExecutor") TaskExecutor taskExecutor,
            @Qualifier("myFileReader") FlatFileItemReader<Client> flatFileItemReader,
            @Qualifier("myJdbcWriter")  JdbcBatchItemWriter<Client> clientJdbcBatchItemWriter)  {
        return stepBuilderFactory
                .get("simpleStepReaderFile")
                .<Client, Client>chunk(30)
                .reader(flatFileItemReader)
                .writer(clientJdbcBatchItemWriter)
                .taskExecutor(taskExecutor)
                .build();
    }
}

package br.com.spring.batch.exemple.writer;

import br.com.spring.batch.exemple.domain.Client;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class JdbClientWriter {

    @Bean(name="myJdbcWriter")
    public JdbcBatchItemWriter<Client> clientJdbcBatchItemWriter(@Qualifier("appDatasource") DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Client>()
                .dataSource(dataSource)
                .sql("INSERT INTO cliente (nome, email, data_nascimento, idade, id) VALUES (?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(clientItemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<Client> clientItemPreparedStatementSetter(){
        return new ItemPreparedStatementSetter<Client>() {
            @Override
            public void setValues(Client client, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getEmail());
                preparedStatement.setDate(3, new java.sql.Date(client.getBirthDate().getTime()));
                preparedStatement.setInt(4, client.getIdade());
                preparedStatement.setInt(5, client.getId());
            }
        };
    }
}

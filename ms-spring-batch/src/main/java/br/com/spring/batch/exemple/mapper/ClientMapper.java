package br.com.spring.batch.exemple.mapper;

import br.com.spring.batch.exemple.domain.Client;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ClientMapper implements FieldSetMapper<Client> {
    @Override
    public Client mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Client(fieldSet.readString("nome"),
                fieldSet.readString("email"),
                fieldSet.readDate("data_nascimento"),
                fieldSet.readInt("idade"),
                fieldSet.readInt("id"));
    }
}

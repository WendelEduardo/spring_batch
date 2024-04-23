package br.com.wemd.springbatchv5;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PessoaRowMapper implements RowMapper<Pessoa> {

    @Override
    public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(rs.getInt("ID"));
        pessoa.setNome(rs.getString("NOME"));
        pessoa.setEmail(rs.getString("EMAIL"));
        pessoa.setDataNascimento(rs.getString("DATA_NASCIMENTO"));
        pessoa.setIdade(rs.getInt("IDADE"));

        System.out.println(pessoa);
        return pessoa;
    }
}

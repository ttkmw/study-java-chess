package chess.dao.jdbc;

import chess.dao.StatementStrategy;
import chess.dao.error.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcContext<T> {
    private final DataSource dataSource;
    private final RowMapper<T> rowMapper;

    public JdbcContext(DataSource dataSource, RowMapper<T> rowMapper) {
        this.dataSource = dataSource;
        this.rowMapper = rowMapper;
    }

    public void updateWithStatementStrategy(StatementStrategy statement) {
        try (
                Connection c = dataSource.getConnection();
                PreparedStatement ps = statement.makePreparedStatement(c);
        ) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(DatabaseException.QUERY_FAILED_MESSAGE);
        }
    }

    public T queryObject(StatementStrategy statement) {
        try (
                Connection c = dataSource.getConnection();
                PreparedStatement ps = statement.makePreparedStatement(c);
                ResultSet rs = ps.executeQuery();
        ) {
            return rowMapper.mapRow(rs);
        } catch (SQLException e) {
            throw new DatabaseException(DatabaseException.QUERY_FAILED_MESSAGE);
        }
    }

    public List<T> queryObjects(StatementStrategy statementStrategy) {
        try (
                Connection c = dataSource.getConnection();
                PreparedStatement ps = statementStrategy.makePreparedStatement(c);
                ResultSet rs = ps.executeQuery();
        ) {
            return rowMapper.mapRows(rs);
        } catch (SQLException e) {
            throw new DatabaseException(DatabaseException.QUERY_FAILED_MESSAGE);
        }

    }
}

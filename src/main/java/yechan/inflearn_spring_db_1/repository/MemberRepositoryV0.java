package yechan.inflearn_spring_db_1.repository;

import lombok.extern.slf4j.Slf4j;
import yechan.inflearn_spring_db_1.connection.DBConnectionUtil;
import yechan.inflearn_spring_db_1.domain.Member;

import java.sql.*;

@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();

            return member;
        } catch (SQLException e) {
            log.error("sql error", e);
            throw new RuntimeException();
        } finally {
            close(conn, pstmt, null);
        }
    }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("resultset close error", e);
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.error("sql statement close error", e);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("db connection close error", e);
            }
        }
    }
}

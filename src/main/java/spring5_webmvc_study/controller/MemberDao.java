package spring5_webmvc_study.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
@Component
public class MemberDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	//RowMapper필드뺴기
	private RowMapper<Member> memberRowMapper = new RowMapper<Member>() {
		
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member(rs.getString("email"), 
					rs.getString("name"), rs.getString("password"),  rs.getTimestamp("regdate").toLocalDateTime());
			member.setId(rs.getLong("id"));
			return member;
		}
	};
	
	
	// selectByEmail
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select id, email, password, name, regdate from member where email = ?",
				memberRowMapper, email);
		return results.isEmpty() ? null : results.get(0);
	}
	
	// selectByDate
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to){
		String sql = "select * " + 
				"	from member " + 
				" where regdate between ? and ? " + 
				" order by regdate desc ";
		return jdbcTemplate.query(sql, memberRowMapper, from, to);
	}

	public List<Member> selectAll() {
		return jdbcTemplate.query("select id,email,password,name,regdate from member", memberRowMapper);
	}

	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from member", Integer.class);
	}

	// 추가.
	public void insert(Member member) {
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into member(email, password, name, regdate)" + " values(?,?,?,?)",
						new String[] { "id" });
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	// 수정.
	public void update(Member member) {
		jdbcTemplate.update("update member set name=?, password=? where email = ?", member.getName(),
				member.getPassword(), member.getEmail());

	}

	// 삭제.
	public void delete(Member member) {
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("delete from member where email=?");
				pstmt.setString(1, member.getEmail());
				return pstmt;
			}
		};
		jdbcTemplate.update(psc);
	}
	
}

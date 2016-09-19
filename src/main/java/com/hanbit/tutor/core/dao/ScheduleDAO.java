package com.hanbit.tutor.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.tutor.core.vo.ScheduleVO;

@Repository
public class ScheduleDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleDAO.class);

	@Autowired
	private SqlSession sqlSession;

	public int insertSchedule(ScheduleVO schedule) {
		LOGGER.debug("인서트 스케줄");

//		Connection connection = getConnection();

//		String sql = "INSERT INTO SCHEDULE (SCHEDULE_ID, TITLE, MEMO, START_DT, END_DT) "
//				+ " VALUES(?, ?, ?, ?, ?)";

//		List params = new ArrayList();
//		params.add(schedule.getScheduleId());
//		params.add(schedule.getTitle());
//		params.add(schedule.getMemo());
//		params.add(schedule.getStartDt());
//		params.add(schedule.getEndDt());

		int result = sqlSession.insert("schedule.insertSchedule" , schedule);

//		int result = executeSql(connection, sql, params);

//		closeConnection(connection);

		return result;
	}

	public int updateSchedule(ScheduleVO schedule) {
//		Connection connection = getConnection();
//
//		String sql = "UPDATE SCHEDULE SET TITLE = ?, MEMO = ?, "
//				+ "START_DT = ?, END_DT = ? "
//				+ "WHERE SCHEDULE_ID = ?";
//
//		List params = new ArrayList();
//		params.add(schedule.getTitle());
//		params.add(schedule.getMemo());
//		params.add(schedule.getStartDt());
//		params.add(schedule.getEndDt());
//		params.add(schedule.getScheduleId());

		int result = sqlSession.update("schedule.updateSchedule" , schedule);

		return result;
//		closeConnection(connection);

	}

	public int deleteSchedule(String scheduleId) {
//		Connection connection = getConnection();
//
//		String sql = "DELETE FROM SCHEDULE WHERE SCHEDULE_ID = ?";
//
//		List params = new ArrayList();
//		params.add(scheduleId);
//
//		int result = executeSql(connection, sql, params);
//
//		closeConnection(connection);

		int result = sqlSession.delete("schedule.deleteSchedule", scheduleId);


		return result;
	}

	public List<ScheduleVO> selectSchedules(String startDt, String endDt) {

		Map params = new HashMap();
		params.put("startDt", startDt);
		params.put("endDt", endDt);

		List<ScheduleVO> result = sqlSession.selectList("schedule.selectSchedules" , params);

		return result;
	}

	public ScheduleVO selectSchedule(String scheduleId) {
//		Connection connection = getConnection();
//
//		String sql = "SELECT SCHEDULE_ID, TITLE, MEMO, "
//				+ "START_DT, END_DT FROM SCHEDULE "
//				+ "WHERE SCHEDULE_ID = ?";
//
//		List params = new ArrayList();
//		params.add(scheduleId);
//
//		ScheduleVO schedule = null;
//
//		try {
//			PreparedStatement statement = connection.prepareStatement(sql);
//
//			for (int i=0;i<params.size();i++) {
//				statement.setObject(i + 1, params.get(i));
//			}
//
//			ResultSet resultSet = statement.executeQuery();
//
//			if (resultSet.next()) {
//				schedule = new ScheduleVO();
//
//				schedule.setScheduleId(resultSet.getString("SCHEDULE_ID"));
//				schedule.setTitle(resultSet.getString("TITLE"));
//				schedule.setMemo(resultSet.getString("MEMO"));
//				schedule.setStartDt(resultSet.getString("START_DT"));
//				schedule.setEndDt(resultSet.getString("END_DT"));
//			}
//
//			resultSet.close();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		closeConnection(connection);

		ScheduleVO schedule = sqlSession.selectOne("selectSchedule" , scheduleId);


		return schedule;
	}


}

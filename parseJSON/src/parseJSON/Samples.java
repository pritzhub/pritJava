package parseJSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Samples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		private static Map<String, List<Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
	        ResultSetMetaData md = rs.getMetaData();
	        int columns = md.getColumnCount();
	        Map<String, List<Object>> map = new HashMap<>(columns);
	        for (int i = 1; i <= columns; ++i) {
	            map.put(md.getColumnName(i), new ArrayList<>());
	        }
	        while (rs.next()) {
	            for (int i = 1; i <= columns; ++i) {
	                map.get(md.getColumnName(i)).add(rs.getObject(i));
	            }
	        }

	        return map;
	    }
*/
		//Map<String, List<Object>> map = null;
		
		Map<String,String> map1 = null;
		
		//List<Object> l1 = null;
		//l1.add("One");
		//l1.add("Ek");
		
		//map.put("1", l1);
		System.out.println(map1.values());
				
		
		
	}

}

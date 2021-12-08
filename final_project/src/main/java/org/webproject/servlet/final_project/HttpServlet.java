package org.webproject.servlet.final_project;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class HttpServlet
 */
@WebServlet("/HttpServlet")
public class HttpServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public HttpServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String tab_id = request.getParameter("tab_id");

        // create a report
        if (tab_id.equals("0")) {
            System.out.println("A report is submitted!");
            try {
                createReport(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // query reports
//        else if (tab_id.equals("1")) {
//            try {
////                queryReport(request, response);
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
    }

    private void createReport(HttpServletRequest request, HttpServletResponse
            response) throws SQLException, IOException {
        DBUtility dbutil = new DBUtility();
        String sql;

        // 2. create user
        int user_id = 0;
        String ufo_shape = request.getParameter("ufo_shape");
        String lon = request.getParameter("longitude");
        String lat = request.getParameter("latitude");
        String characteristics = request.getParameter("characteristics");
        String duration = request.getParameter("duration");
        String time = request.getParameter("time");
        String weekday = request.getParameter("weekday");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");

        if (ufo_shape != null) {ufo_shape = "'" + ufo_shape + "'";}
        if (characteristics != null) {characteristics = "'" + characteristics + "'";}
        if (duration != null) {duration = "'" + duration + "'";}
        if (time != null) {time = "'" + time + "'";}
        if (weekday != null) {weekday = "'" + weekday + "'";}
        if (day != null) {day = "'" + day + "'";}
        if (month != null) {month = "'" + month + "'";}
        if (year != null) {year = "'" + year + "'";}

// HERE i AM ONLY USING A COUPLE OF THE FIELDS TO JUST TEST THAT IS WORKS - WE CAN ADD ALL THE FIELDS LATER
        sql = "insert into Sightings (ufo_shape, geom, characteristics, duration, time, weekday, day, month, year) values ( "+ufo_shape + ", ST_GeomFromText('POINT(" + lon + " " + lat + ")', 4326)" + "," + characteristics +
                "," + duration + "," + time + "," + weekday + ","+ day+ "," + month +"," + year+ ")"
        ; // EVERYTHING NEEDS TO BE A STRING SINCE sql VARIABLE IS A STRING STATEMENT

        dbutil.modifyDB(sql);



        System.out.println("Success! User created.");


        System.out.println("Success! Report created.");

        // 4. create specific report


        // response that the report submission is successful
        JSONObject data = new JSONObject();
        try {
            data.put("status", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.getWriter().write(data.toString());

    }


    public void queryReport(HttpServletRequest request, HttpServletResponse
            response) throws JSONException, SQLException, IOException {
        JSONArray list = new JSONArray();

        String disaster_type = request.getParameter("disaster_type");
        String report_type = request.getParameter("report_type");
        // resource_or_damage will be null if report_type is null
        String resource_or_damage = request.getParameter("resource_or_damage");

        //adding sql for UFOReports below
        String sql = "select ufo_shape, year, resource_type, " +
                " ST_X(geom) as " +
                "longitude, ST_Y(geom) as latitude from Sightings " ;
        queryReportHelper(sql,list);
//        // request report
//        if (report_type == null || report_type.equalsIgnoreCase("request")) {
//            String sql = "select report.id, report_type, resource_type, " +
//                    "disaster_type, first_name, last_name, time_stamp, ST_X(geom) as " +
//                    "longitude, ST_Y(geom) as latitude, message from report, person, " +
//                    "request_report where reporter_id = person.id and report.id = " +
//                    "report_id";
//            queryReportHelper(sql,list,"request",disaster_type,resource_or_damage);
//        }

//        // donation report
//        if (report_type == null || report_type.equalsIgnoreCase("donation")) {
//            String sql = "select report.id, report_type, resource_type, " +
//                    "disaster_type, first_name, last_name, time_stamp, ST_X(geom) as " +
//                    "longitude, ST_Y(geom) as latitude, message from report, person, " +
//                    "donation_report where reporter_id = person.id and report.id = " +
//                    "report_id";
//            queryReportHelper(sql,list,"donation",disaster_type,resource_or_damage);
//        }
//
//        // damage report
//        if (report_type == null || report_type.equalsIgnoreCase("damage")) {
//            String sql = "select report.id, report_type, damage_type, " +
//                    "disaster_type, first_name, last_name, time_stamp, ST_X(geom) as " +
//                    "longitude, ST_Y(geom) as latitude, message from report, person, " +
//                    "damage_report where reporter_id = person.id and report.id = " +
//                    "report_id";
//            queryReportHelper(sql,list,"damage",disaster_type,resource_or_damage);
//        }

        response.getWriter().write(list.toString());
    }

    public void queryReportHelper(String sql, JSONArray list) throws SQLException {
        DBUtility dbutil = new DBUtility();

        ResultSet res = dbutil.queryDB(sql);
        while (res.next()) {
            // add to response
            HashMap<String, String> m = new HashMap<String,String>();
            ;
            m.put("longitude", res.getString("longitude"));
            m.put("latitude", res.getString("latitude"));
            m.put("ufo_shape", res.getString("ufo_shape"));
            list.put(m);
        }
    }


    public void main() throws JSONException {
    }
}

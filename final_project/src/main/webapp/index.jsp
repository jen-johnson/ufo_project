<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Web Project</title>

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">

    <!-- jQuery -->
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script src="https://maps.googleapis.com/maps/api/js?&key=APIKEY&libraries=places,visualization"></script>
 
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <a class="navbar-brand">Worldwide UFO Reporting System</a>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="sidebar col-xs-3">

            <!-- Tab Navis-->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#create_report" data-toggle="tab">Report Sighting</a></li>
                <li><a href="#query_report" data-toggle="tab">Query Database</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content ">
                <!-- Create Report Tab Panel -->
                <div class="tab-pane active" id="create_report">
                    <form id = "create_report_form">
                        <div><label>UFO Shape:</label>
                            <select name="ufo_shape">
                                <option value="">Select shape</option>
                                <option value="Triangle">Triangle</option>
                                <option value="Sphere">Sphere</option>
                                <option value="Diamond">Diamond</option>
                                <option value="Disk">Disk</option>
                                <option value="Chevron">Chevron</option>
                                <option value="Egg">Egg</option>
                                <option value="Cross">Cross</option>
                                <option value="Fireball">Fireball</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                        <div><label>Characteristics:</label>
                            <select name="characteristics">
                                <option value="">Select</option>
                                <option value="Flashing">Triangle</option>
                                <option value="Changing">Sphere</option>
                                <option value="Formation">Diamond</option>
                            </select>
                        </div>
                        <div><label>Time of Day:</label>
                            <select name="time">
                                <option value="">Select Time</option>
                                <option value="Morning">Morning</option>
                                <option value="Day">Day</option>
                                <option value="Evening">Evening</option>
                                <option value="Night">Night</option>
                            </select>
                        </div>

                        <div><label>Day of Week:</label>
                            <select name="weekday">
                                <option value="">Select Day</option>
                                <option value="Monday">Monday</option>
                                <option value="Tuesday">Tuesday</option>
                                <option value="Wednesday">Wednesday</option>
                                <option value="Thursday">Thursday</option>
                                <option value="Friday">Friday</option>
                                <option value="Saturday">Saturday</option>
                                <option value="Sunday">Sunday</option>

                            </select>
                        </div>

                        <div><label>Day (of Month):&nbsp</label><input placeholder="Enter number" name="day"> </div>
                        <div><label>Month:</label>
                            <select name="time">
                                <option value="">Select Month</option>
                                <option value="January">January</option>
                                <option value="February">February</option>

                                <option value="March">March</option>
                                <option value="April">April</option>
                                <option value="May">May</option>
                                <option value="June">June</option>

                                <option value="July">July</option>
                                <option value="August">August</option>
                            </select>
                        </div>
                        <div><label>Year:&nbsp</label><input placeholder="Enter Year of Sighting" name="year"> </div>


                        <div><label>Location of Sighting:</label>
                            <input id="autocomplete" placeholder="Address" >
                        </div>
                        <div><label>More Details:&nbsp</label><input placeholder="Additional Details" name="details"></div>
                        <button type="submit" class="btn btn-default" id="report_submit_btn">
                            <span class="glyphicon glyphicon-star"></span> Submit
                        </button>
                    </form>
                </div>

                <!-- Query Report Tab Panel -->
                <div class="tab-pane" id="query_report">
                    <form id = "query_report_form">
                        <div><label>Report Type:</label>
                            <select onchange="onSelectReportType(this)" name="report_type">
                                <option value="">Choose the report type</option>
                                <option value="donation">Donation</option>
                                <option value="request">Request</option>
                                <option value="damage">Damage Report</option>
                            </select>
                        </div>
                        <div class="additional_msg_div" style="visibility: hidden"><label class="additional_msg"></label>
                            <select class="additional_msg_select" name="resource_or_damage"></select>
                        </div>
                        <div><label>Disaster Type:</label>
                            <select name="disaster_type">
                                <option value="">Choose the disaster type</option>
                                <option value="flood">flood</option>
                                <option value="wildfire">wildfire</option>
                                <option value="earthquake">earthquake</option>
                                <option value="tornado">tornado</option>
                                <option value="hurricane">hurricane</option>
                                <option value="other">other</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-star"></span> Submit the query
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <div id="map-canvas" class="col-xs-9"></div>

    </div>
</div>

<script src="js/loadform.js"></script>
<script src="js/loadmap.js"></script>

</body>
</html>
